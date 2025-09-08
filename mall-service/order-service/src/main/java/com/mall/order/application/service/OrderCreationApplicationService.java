package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.api.order.dto.request.OrderItemRequest;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.api.product.dto.response.SkuVO;
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
    private final OrderExternalServiceFacade externalServiceFacade;
    
    /**
     * 创建订单
     */
    @GlobalTransactional
    public OrderVO createOrder(Long userId, OrderCreateRequest request) {
        log.info("Creating order for user: {}, request: {}", userId, request);
        
        try {
            // 1. 验证订单创建请求
            OrderValidationService.OrderCreationValidationResult validationResult = 
                    validationService.validateOrderCreation(userId, request);
            
            // 2. 构建订单地址
            OrderAddress orderAddress = buildOrderAddress(validationResult.getAddress());
            
            // 3. 构建订单项
            List<OrderItem> orderItems = buildOrderItems(request.getOrderItems(), validationResult.getSkuMap());
            
            // 4. 创建订单聚合根
            Order order = orderFactory.createOrder(
                    userId,
                    orderItems,
                    orderAddress,
                    request.getPaymentType(),
                    request.getRemark()
            );
            
            // 5. 应用优惠（如果有）
            applyDiscounts(order, request);
            
            // 6. 锁定库存
            orderDomainService.lockStock(order.getOrderNo(), orderItems);
            
            // 7. 保存订单
            order = orderRepository.save(order);
            
            // 8. 发布领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Order created successfully: {}", order.getOrderNo());
            
            // 9. 返回订单视图对象
            return orderAssembler.toOrderVO(order);
            
        } catch (Exception e) {
            log.error("Failed to create order for user: {}, request: {}", userId, request, e);
            throw e;
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
    private void applyDiscounts(Order order, OrderCreateRequest request) {
        if (request.getCouponId() != null) {
            // TODO: 实现优惠券折扣逻辑
            log.debug("Applying coupon discount for order: {}, couponId: {}", 
                     order.getOrderNo(), request.getCouponId());
        }
        
        if (request.getUsePoints() != null && request.getUsePoints() > 0) {
            // TODO: 实现积分抵扣逻辑
            log.debug("Applying points discount for order: {}, points: {}", 
                     order.getOrderNo(), request.getUsePoints());
        }
    }
}