package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 次要业务域服务
 * 处理本地事务+最终一致性的业务逻辑
 * 包括订单详细信息完善、优惠处理等
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SecondaryTransactionService {
    
    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;

    /**
     * 处理次要业务逻辑
     * 使用本地事务保证数据一致性，通过事件保证最终一致性
     */
    @Transactional(rollbackFor = Exception.class)
    public void executeSecondary(Order order, OrderCreateRequest request) {
        
        log.info("Executing secondary transaction for order: {}", order.getOrderNo());
        
        try {
            // 1. 应用优惠策略
            applyDiscounts(order, request);
            
            // 2. 更新订单详细信息
            order = orderRepository.save(order);
            
            // 3. 在事务提交后发布领域事件（保证最终一致性）
            publishEventsAfterCommit(order);
            
            log.info("Secondary transaction completed for order: {}", order.getOrderNo());
            
        } catch (Exception e) {
            log.error("Secondary transaction failed for order: {}", order.getOrderNo(), e);
            throw e;
        }
    }
    
    /**
     * 应用优惠策略
     */
    private void applyDiscounts(Order order, OrderCreateRequest request) {
        log.debug("Applying discounts for order: {}", order.getOrderNo());
        
        // 应用优惠券
        if (request.getCouponId() != null) {
            applyCouponDiscount(order, request.getCouponId());
        }
        
        // 使用积分抵扣
        if (request.getUsePoints() != null && request.getUsePoints() > 0) {
            applyPointsDiscount(order, request.getUsePoints());
        }
    }
    
    /**
     * 应用优惠券折扣
     */
    private void applyCouponDiscount(Order order, Long couponId) {
        log.debug("Applying coupon discount for order: {}, couponId: {}", 
                 order.getOrderNo(), couponId);
        // TODO: 实现优惠券折扣逻辑
        // 1. 验证优惠券有效性
        // 2. 计算折扣金额
        // 3. 更新订单金额
        // 4. 记录优惠券使用记录
    }
    
    /**
     * 应用积分抵扣
     */
    private void applyPointsDiscount(Order order, Integer usePoints) {
        log.debug("Applying points discount for order: {}, points: {}", 
                 order.getOrderNo(), usePoints);
        // TODO: 实现积分抵扣逻辑
        // 1. 验证用户积分余额
        // 2. 计算抵扣金额
        // 3. 更新订单金额
        // 4. 记录积分使用记录
    }
    
    /**
     * 在事务提交后发布领域事件
     * 确保事件发布时数据已经持久化
     */
    private void publishEventsAfterCommit(final Order order) {
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        try {
                            // 发布订单创建完成事件
                            domainEventPublisher.publishEvents(order);
                            
                            // 发布购物车清理事件
                            publishCartCleanupEvent(order);
                            
                        } catch (Exception e) {
                            log.error("Failed to publish events after commit for order: {}", 
                                    order.getOrderNo(), e);
                            // 事件发布失败不影响主业务流程
                            // 可以考虑将失败的事件存储起来，后续重试
                        }
                    }
                }
            );
        } else {
            // 如果没有事务，直接发布
            domainEventPublisher.publishEvents(order);
            publishCartCleanupEvent(order);
        }
    }
    
    /**
     * 发布购物车清理事件
     */
    private void publishCartCleanupEvent(Order order) {
        log.debug("Publishing cart cleanup event for order: {}", order.getOrderNo());
        // TODO: 发布购物车清理事件，由购物车服务异步处理
        // 这里可以通过RabbitMQ发送消息给购物车服务
    }
    
    /**
     * 补偿操作：回滚次要业务
     * 当主流程失败时调用
     */
    @Transactional(rollbackFor = Exception.class)
    public void compensateSecondary(Order order) {
        log.warn("Compensating secondary transaction for order: {}", order.getOrderNo());
        
        try {
            // 回滚优惠券使用
            rollbackCouponUsage(order);
            
            // 回滚积分使用
            rollbackPointsUsage(order);
            
            log.info("Secondary transaction compensation completed for order: {}", order.getOrderNo());
            
        } catch (Exception e) {
            log.error("Secondary transaction compensation failed for order: {}", order.getOrderNo(), e);
            throw e;
        }
    }
    
    private void rollbackCouponUsage(Order order) {
        // TODO: 实现优惠券使用回滚逻辑
        log.debug("Rolling back coupon usage for order: {}", order.getOrderNo());
    }
    
    private void rollbackPointsUsage(Order order) {
        // TODO: 实现积分使用回滚逻辑  
        log.debug("Rolling back points usage for order: {}", order.getOrderNo());
    }
}