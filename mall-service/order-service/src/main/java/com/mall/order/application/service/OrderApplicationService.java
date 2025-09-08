// ========== OrderApplicationService.java - 订单应用服务 ==========
package com.mall.order.application.service;

import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.common.result.PageResult;

import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.service.OrderDomainService;
import com.mall.order.domain.order.service.OrderFactory;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.OrderStatus;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.infrastructure.external.InventoryServiceClient;
import com.mall.order.infrastructure.external.PaymentServiceClient;
import com.mall.order.infrastructure.external.ProductServiceClient;
import com.mall.order.infrastructure.external.UserServiceClient;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import com.mall.order.interfaces.assembler.OrderAssembler;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单应用服务
 * DDD核心：应用服务编排领域对象，处理用例逻辑
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderApplicationService {
    
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderFactory orderFactory;
    private final OrderAssembler orderAssembler;
    private final DomainEventPublisher domainEventPublisher;
    
    // 外部服务客户端
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    
    /**
     * 创建订单
     */
    @GlobalTransactional
    public OrderVO createOrder(Long userId, OrderCreateRequest request) {
        log.info("Creating order for user: {}, request: {}", userId, request);
        
        // 1. 获取用户信息
        var userInfo = userServiceClient.getUserById(userId);
        
        // 2. 获取收货地址
        var address = userServiceClient.getAddressById(request.getAddressId());
        OrderAddress orderAddress = new OrderAddress(
            address.getReceiverName(),
            address.getReceiverPhone(),
            address.getProvince(),
            address.getCity(),
            address.getDistrict(),
            address.getDetailAddress(),
            address.getPostalCode()
        );
        
        // 3. 构建订单项
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : request.getOrderItems()) {
            // 获取SKU信息
            SkuVO sku = productServiceClient.getSkuById(itemRequest.getSkuId());
            
            // 检查库存
            boolean hasStock = inventoryServiceClient.checkStock(
                itemRequest.getSkuId(), itemRequest.getQuantity()
            );
            if (!hasStock) {
                throw new IllegalStateException("商品库存不足: " + sku.getSkuName());
            }
            
            // 创建订单项
            OrderItem orderItem = OrderItem.builder()
                .skuId(sku.getId())
                .skuName(sku.getSkuName())
                .skuImage(sku.getImage())
                .skuSpecs(sku.getSpecs().toString())
                .price(sku.getPrice())
                .quantity(itemRequest.getQuantity())
                .totalAmount(sku.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())))
                .build();
            
            orderItems.add(orderItem);
        }
        
        // 4. 创建订单聚合根
        Order order = orderFactory.createOrder(
            userId,
            orderItems,
            orderAddress,
            request.getPaymentType(),
            request.getRemark()
        );
        
        // 5. 应用优惠（如果有）
        if (request.getCouponId() != null || request.getUsePoints() != null) {
            // TODO: 计算优惠金额
            // order.applyDiscount(discountAmount);
        }

        // 7. 保存订单
        order = orderRepository.save(order);
        
        // 8. 发布领域事件
        domainEventPublisher.publishEvents(order);
        
        // 10. 返回DTO
        return orderAssembler.toOrderVO(order);
    }
    
    /**
     * 支付订单
     */
    @Transactional
    public OrderPaymentVO payOrder(OrderPaymentRequest request) {
        log.info("Paying order: {}", request.getOrderNo());
        
        // 1. 查找订单
        Order order = orderRepository.findByOrderNo(new OrderNo(request.getOrderNo()))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        // 2. 创建支付请求
        var paymentResult = paymentServiceClient.createPayment(
            request.getOrderNo(),
            order.getPayAmount().getAmount(),
            request.getPaymentType()
        );
        
        // 3. 返回支付信息
        return OrderPaymentVO.builder()
            .orderNo(request.getOrderNo())
            .paymentNo(paymentResult.getPaymentNo())
            .paymentUrl(paymentResult.getPaymentUrl())
            .paymentAmount(order.getPayAmount().getAmount())
            .build();
    }
    
    /**
     * 支付回调
     */
    @Transactional
    public void paymentCallback(String orderNo, String transactionId) {
        log.info("Payment callback for order: {}, transaction: {}", orderNo, transactionId);
        
        // 1. 查找订单
        Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        // 2. 更新订单状态为已支付
        order.pay(transactionId);
        
        // 3. 扣减库存
        orderDomainService.deductStock(order.getOrderNo());
        
        // 4. 更新订单
        orderRepository.update(order);
        
        // 5. 发布领域事件
        domainEventPublisher.publishEvents(order);
    }
    
    /**
     * 取消订单
     */
    @Transactional
    public void cancelOrder(Long userId, OrderCancelRequest request) {
        log.info("Cancelling order: {} for user: {}", request.getOrderNo(), userId);
        
        // 1. 查找订单
        Order order = orderRepository.findByOrderNo(new OrderNo(request.getOrderNo()))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        // 2. 验证用户
        if (!order.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("无权操作此订单");
        }
        
        // 3. 取消订单
        order.cancel(request.getReason());
        
        // 4. 释放库存
        orderDomainService.releaseStock(order.getOrderNo());
        
        // 5. 更新订单
        orderRepository.update(order);
        
        // 6. 发布领域事件
        domainEventPublisher.publishEvents(order);
    }
    
    /**
     * 发货
     */
    @Transactional
    public void shipOrder(OrderShipRequest request) {
        log.info("Shipping order: {}", request.getOrderNo());
        
        // 1. 查找订单
        Order order = orderRepository.findByOrderNo(new OrderNo(request.getOrderNo()))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        // 2. 发货
        order.ship(request.getDeliveryCompany(), request.getDeliveryNo());
        
        // 3. 更新订单
        orderRepository.update(order);
        
        // 4. 发布发货事件
        // eventPublisher.publishOrderShipped(event);
    }
    
    /**
     * 确认收货
     */
    @Transactional
    public void confirmReceipt(Long userId, String orderNo) {
        log.info("Confirming receipt for order: {} by user: {}", orderNo, userId);
        
        // 1. 查找订单
        Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        // 2. 验证用户
        if (!order.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("无权操作此订单");
        }
        
        // 3. 确认收货
        order.confirmReceipt();
        
        // 4. 更新订单
        orderRepository.update(order);
    }
    
    /**
     * 获取订单详情
     */
    public OrderDetailVO getOrderDetail(String orderNo) {
        Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
            .orElseThrow(() -> new IllegalArgumentException("订单不存在"));
        
        return orderAssembler.toOrderDetailVO(order);
    }
    
    /**
     * 获取用户订单
     */
    public PageResult<OrderVO> getUserOrders(Long userId, Integer status, Integer page, Integer size) {
        List<Order> orders;
        if (status != null) {
            orders = orderRepository.findByUserIdAndStatus(
                new UserId(userId), 
                OrderStatus.fromCode(status)
            );
        } else {
            orders = orderRepository.findByUserId(new UserId(userId));
        }
        
        // 简单分页处理
        int start = (page - 1) * size;
        int end = Math.min(start + size, orders.size());
        List<Order> pageOrders = orders.subList(start, end);
        
        List<OrderVO> orderVOs = pageOrders.stream()
            .map(orderAssembler::toOrderVO)
            .collect(Collectors.toList());
        
        return PageResult.of((long)page, (long)size, (long)orders.size(), orderVOs);
    }
    
    /**
     * 获取订单状态统计
     */
    public Map<Integer, Long> getOrderCountByStatus(Long userId) {
        Map<Integer, Long> countMap = new HashMap<>();
        UserId userIdVO = new UserId(userId);
        
        for (OrderStatus status : OrderStatus.values()) {
            Long count = orderRepository.countByUserIdAndStatus(userIdVO, status);
            countMap.put(status.getCode(), count);
        }
        
        return countMap;
    }
    
    /**
     * 检查超时订单
     */
    @Transactional
    public void checkOrderTimeout() {
        log.info("Checking timeout orders");
        
        // 查找超时未支付的订单（30分钟）
        LocalDateTime timeoutTime = LocalDateTime.now().minusMinutes(30);
        List<Order> timeoutOrders = orderRepository.findTimeoutOrders(timeoutTime);
        
        for (Order order : timeoutOrders) {
            try {
                // 取消订单
                order.cancel("支付超时自动取消");
                
                // 释放库存
                orderDomainService.releaseStock(order.getOrderNo());
                
                // 更新订单
                orderRepository.update(order);
                
                // 发布领域事件
                domainEventPublisher.publishEvents(order);
                
            } catch (Exception e) {
                log.error("Cancel timeout order failed: {}", order.getOrderNo(), e);
            }
        }
    }
    
}