package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCancelRequest;
import com.mall.api.order.dto.request.OrderShipRequest;
import com.mall.order.application.validation.OrderValidationService;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单生命周期应用服务
 * 专门处理订单生命周期管理相关的用例（取消、发货、确认收货等）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderLifecycleApplicationService {
    
    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final OrderValidationService validationService;
    
    /**
     * 取消订单
     */
    @Transactional
    public void cancelOrder(Long userId, OrderCancelRequest request) {
        log.info("Cancelling order: {} for user: {}, reason: {}", 
                request.getOrderNo(), userId, request.getReason());
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(request.getOrderNo());
            
            // 2. 验证用户权限
            validationService.validateUserOrderOwnership(order, userId);
            
            // 3. 验证订单是否可以取消
            validationService.validateOrderCanBeCancelled(order);
            
            // 4. 取消订单
            order.cancel(request.getReason());
            
            // 5. 保存订单状态变更
            orderRepository.update(order);
            
            // 6. 发布订单取消领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Order cancelled successfully: {}", request.getOrderNo());
            
        } catch (Exception e) {
            log.error("Failed to cancel order: {} for user: {}", request.getOrderNo(), userId, e);
            throw e;
        }
    }
    
    /**
     * 系统取消订单（如超时取消）
     */
    @Transactional
    public void cancelOrderBySystem(String orderNo, String reason) {
        log.info("System cancelling order: {}, reason: {}", orderNo, reason);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 验证订单是否可以取消
            validationService.validateOrderCanBeCancelled(order);
            
            // 3. 取消订单
            order.cancel(reason);
            
            // 4. 保存订单状态变更
            orderRepository.update(order);
            
            // 5. 发布订单取消领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Order cancelled by system successfully: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to cancel order by system: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 发货
     */
    @Transactional
    public void shipOrder(OrderShipRequest request) {
        log.info("Shipping order: {}, delivery company: {}, delivery no: {}", 
                request.getOrderNo(), request.getDeliveryCompany(), request.getDeliveryNo());
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(request.getOrderNo());
            
            // 2. 验证订单是否可以发货
            validationService.validateOrderCanBeShipped(order);
            
            // 3. 发货
            order.ship(request.getDeliveryCompany(), request.getDeliveryNo());
            
            // 4. 保存订单状态变更
            orderRepository.update(order);
            
            // 5. 发布发货领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Order shipped successfully: {}", request.getOrderNo());
            
        } catch (Exception e) {
            log.error("Failed to ship order: {}", request.getOrderNo(), e);
            throw e;
        }
    }
    
    /**
     * 确认收货
     */
    @Transactional
    public void confirmReceipt(Long userId, String orderNo) {
        log.info("Confirming receipt for order: {} by user: {}", orderNo, userId);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 验证用户权限
            validationService.validateUserOrderOwnership(order, userId);
            
            // 3. 验证订单是否可以确认收货
            validationService.validateOrderCanBeReceived(order);
            
            // 4. 确认收货
            order.confirmReceipt();
            
            // 5. 保存订单状态变更
            orderRepository.update(order);
            
            // 6. 发布确认收货领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Receipt confirmed successfully for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to confirm receipt for order: {} by user: {}", orderNo, userId, e);
            throw e;
        }
    }
    
    /**
     * 系统自动确认收货（如超过7天自动确认）
     */
    @Transactional
    public void autoConfirmReceipt(String orderNo) {
        log.info("Auto confirming receipt for order: {}", orderNo);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 验证订单是否可以确认收货
            validationService.validateOrderCanBeReceived(order);
            
            // 3. 自动确认收货
            order.confirmReceipt();
            
            // 4. 保存订单状态变更
            orderRepository.update(order);
            
            // 5. 发布自动确认收货领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Receipt auto confirmed successfully for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to auto confirm receipt for order: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 完成订单
     */
    @Transactional
    public void completeOrder(String orderNo) {
        log.info("Completing order: {}", orderNo);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 完成订单
            order.complete();
            
            // 3. 保存订单状态变更
            orderRepository.update(order);
            
            // 4. 发布订单完成领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Order completed successfully: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to complete order: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 根据订单号查找订单
     */
    private Order getOrderByNo(String orderNo) {
        return orderRepository.findByOrderNo(new OrderNo(orderNo))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));
    }
}