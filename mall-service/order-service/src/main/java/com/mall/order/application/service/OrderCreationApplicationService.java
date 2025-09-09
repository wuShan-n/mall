package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.api.order.dto.request.OrderItemRequest;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.order.application.validation.OrderValidationService;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.interfaces.assembler.OrderAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 订单创建应用服务
 * 实现分层事务架构：
 * 1. 核心事务域：使用Seata保证强一致性（资金、库存等）
 * 2. 次要业务域：使用本地事务+最终一致性（订单详情、购物车等）
 * 3. 外围功能域：使用异步消息处理（通知、日志、积分等）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreationApplicationService {
    
    private final OrderAssembler orderAssembler;
    private final OrderValidationService validationService;
    
    // 分层事务服务
    private final CoreTransactionService coreTransactionService;
    private final SecondaryTransactionService secondaryTransactionService;

    /**
     * 创建订单 - 分层事务架构
     * 不使用@Transactional，由各层服务管理自己的事务
     */
    public OrderVO createOrder(Long userId, OrderCreateRequest request) {
        log.info("Creating order with layered transaction for user: {}, request: {}", userId, request);
        
        Order order = null;
        boolean coreTransactionCompleted = false;
        
        try {
            // 1. 验证订单创建请求
            OrderValidationService.OrderCreationValidationResult validationResult = 
                    validationService.validateOrderCreation(userId, request);
            
            // 2. 构建订单基础数据
            OrderAddress orderAddress = buildOrderAddress(validationResult.getAddress());
            List<OrderItem> orderItems = buildOrderItems(request.getOrderItems(), validationResult.getSkuMap());
            
            // 3. 执行核心事务域（强一致性）
            order = coreTransactionService.executeCore(userId, orderItems, orderAddress, request);
            coreTransactionCompleted = true;
            
            // 4. 执行次要业务域（最终一致性）
            secondaryTransactionService.executeSecondary(order, request);
            
            // 5. 外围功能域通过异步事件自动处理（通知、日志、积分等）
            
            log.info("Order created successfully with layered transactions: {}", order.getOrderNo());
            
            // 6. 返回订单视图对象
            return orderAssembler.toOrderVO(order);
            
        } catch (Exception e) {
            log.error("Failed to create order for user: {}, request: {}", userId, request, e);
            
            // 补偿逻辑：如果次要业务失败，但核心事务已完成，需要进行补偿
            if (coreTransactionCompleted && order != null) {
                handleFailureCompensation(order, e);
            }
            
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
     * 处理失败补偿
     * 当次要业务失败时，需要对已完成的核心事务进行补偿
     */
    private void handleFailureCompensation(Order order, Exception originalException) {
        log.warn("Handling failure compensation for order: {}", order.getOrderNo());
        
        try {
            // 尝试进行次要业务补偿
            secondaryTransactionService.compensateSecondary(order);
            
        } catch (Exception compensationException) {
            log.error("Compensation failed for order: {}", order.getOrderNo(), compensationException);
            
            // 补偿失败，记录错误，可能需要人工干预
            // 这里可以发送告警或将失败信息存储到补偿表中
            recordCompensationFailure(order, originalException, compensationException);
        }
    }
    
    /**
     * 记录补偿失败信息
     */
    private void recordCompensationFailure(Order order, Exception originalException, Exception compensationException) {
        log.error("Recording compensation failure for order: {}, original: {}, compensation: {}", 
                 order.getOrderNo(), originalException.getMessage(), compensationException.getMessage());
        
        // TODO: 实现补偿失败记录逻辑
        // 1. 存储到补偿失败表
        // 2. 发送告警通知
        // 3. 可能需要人工干预处理
    }
}