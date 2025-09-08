package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.api.order.dto.request.OrderItemRequest;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.order.application.dto.OrderPreparationData;
import com.mall.order.application.facade.OrderExternalServiceFacade;
import com.mall.order.application.validation.OrderValidationService;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.service.OrderDomainService;
import com.mall.order.domain.order.service.OrderFactory;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import com.mall.order.interfaces.assembler.OrderAssembler;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单创建应用服务
 * 专门处理订单创建相关的用例
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreationApplicationService {
    
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderFactory orderFactory;
    private final OrderAssembler orderAssembler;
    private final DomainEventPublisher domainEventPublisher;
    
    private final OrderValidationService validationService;

    /**
     * 创建订单（主入口）
     */
    public OrderVO createOrder(Long userId, OrderCreateRequest request) {
        log.info("Creating order for user: {}, request: {}", userId, request);
        
        try {
            // 第1阶段：预处理（无事务）
            OrderPreparationData preparationData = prepareOrderCreation(userId, request);
            
            // 第2阶段：核心事务（最小化事务）
            Order order = executeOrderCreation(preparationData);
            
            // 第3阶段：后处理（异步，最终一致性）
            completeOrderCreation(order);
            
            log.info("Order created successfully: {}", order.getOrderNo());
            
            return orderAssembler.toOrderVO(order);
            
        } catch (Exception e) {
            log.error("Failed to create order for user: {}, request: {}", userId, request, e);
            throw e;
        }
    }
    
    /**
     * 第1阶段：预处理订单创建（无事务）
     * 包含所有验证和数据准备工作
     */
    public OrderPreparationData prepareOrderCreation(Long userId, OrderCreateRequest request) {
        log.debug("Preparing order creation for user: {}", userId);
        
        // 1. 验证订单创建请求（包含批量库存检查）
        OrderValidationService.OrderCreationValidationResult validationResult = 
                validationService.validateOrderCreation(userId, request);
        
        // 2. 构建订单地址
        OrderAddress orderAddress = buildOrderAddress(validationResult.getAddress());
        
        // 3. 构建订单项
        List<OrderItem> orderItems = buildOrderItems(request.getOrderItems(), validationResult.getSkuMap());
        
        // 4. 返回准备数据
        return OrderPreparationData.builder()
                .userId(userId)
                .userInfo(validationResult.getUserInfo())
                .addressInfo(validationResult.getAddress())
                .orderAddress(orderAddress)
                .skuMap(validationResult.getSkuMap())
                .orderItems(orderItems)
                .paymentType(request.getPaymentType())
                .remark(request.getRemark())
                .couponId(request.getCouponId())
                .usePoints(request.getUsePoints())
                .build();
    }
    
    /**
     * 第2阶段：执行订单创建（核心事务，最小化）
     * 只包含写操作：库存锁定 + 订单保存
     */
    @GlobalTransactional
    public Order executeOrderCreation(OrderPreparationData preparationData) {
        log.debug("Executing order creation for user: {}", preparationData.getUserId());
        
        // 1. 创建订单聚合根
        Order order = orderFactory.createOrder(
                preparationData.getUserId(),
                preparationData.getOrderItems(),
                preparationData.getOrderAddress(),
                preparationData.getPaymentType(),
                preparationData.getRemark()
        );
        
        // 2. 应用优惠（如果有）
        applyDiscounts(order, preparationData);
        
        // 3. 批量锁定库存
        orderDomainService.lockStock(order.getOrderNo(), preparationData.getOrderItems());
        
        // 4. 保存订单
        order = orderRepository.save(order);
        
        return order;
    }
    
    /**
     * 第3阶段：完成订单创建（后处理，异步）
     * 发布事件等非关键操作
     */
    public void completeOrderCreation(Order order) {
        try {
            // 发布领域事件
            domainEventPublisher.publishEvents(order);
        } catch (Exception e) {
            // 事件发布失败不影响订单创建，记录日志即可
            log.error("Failed to publish events for order: {}", order.getOrderNo(), e);
        }
    }
    
    /**
     * 构建订单地址
     */
    private OrderAddress buildOrderAddress(com.mall.api.user.dto.response.AddressVO address) {
        return new OrderAddress(
                address.getReceiverName(),
                address.getReceiverPhone(),
                address.getProvince(),
                address.getCity(),
                address.getDistrict(),
                address.getDetailAddress(),
                address.getPostalCode()
        );
    }
    
    /**
     * 构建订单项列表
     */
    private List<OrderItem> buildOrderItems(List<OrderItemRequest> itemRequests, Map<Long, SkuVO> skuMap) {
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (OrderItemRequest itemRequest : itemRequests) {
            SkuVO sku = skuMap.get(itemRequest.getSkuId());
            
            OrderItem orderItem = OrderItem.builder()
                    .skuId(sku.getId())
                    .skuName(sku.getSkuName())
                    .skuImage(sku.getImage())
                    .skuSpecs(sku.getSpecs() != null ? sku.getSpecs().toString() : "")
                    .price(sku.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .totalAmount(sku.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())))
                    .build();
            
            orderItems.add(orderItem);
        }
        
        return orderItems;
    }
    
    /**
     * 应用优惠
     */
    private void applyDiscounts(Order order, OrderPreparationData preparationData) {
        if (preparationData.getCouponId() != null) {
            // TODO: 实现优惠券折扣逻辑
            log.debug("Applying coupon discount for order: {}, couponId: {}", 
                     order.getOrderNo(), preparationData.getCouponId());
        }
        
        if (preparationData.getUsePoints() != null && preparationData.getUsePoints() > 0) {
            // TODO: 实现积分抵扣逻辑
            log.debug("Applying points discount for order: {}, points: {}", 
                     order.getOrderNo(), preparationData.getUsePoints());
        }
    }
}