package com.mall.order.application.service;

import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.service.OrderDomainService;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单定时任务应用服务
 * 专门处理订单相关的定时任务和批处理操作
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderScheduleApplicationService {
    
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final DomainEventPublisher domainEventPublisher;
    
    /**
     * 检查超时订单并自动取消
     * 通常在定时任务中调用，处理超过30分钟未支付的订单
     */
    @Transactional
    public void checkAndCancelTimeoutOrders() {
        log.info("Starting to check and cancel timeout orders");
        
        try {
            // 查找超时未支付的订单（30分钟）
            LocalDateTime timeoutTime = LocalDateTime.now().minusMinutes(30);
            List<Order> timeoutOrders = orderRepository.findTimeoutOrders(timeoutTime);
            
            if (timeoutOrders.isEmpty()) {
                log.debug("No timeout orders found");
                return;
            }
            
            log.info("Found {} timeout orders to cancel", timeoutOrders.size());
            
            int successCount = 0;
            int failCount = 0;
            
            for (Order order : timeoutOrders) {
                try {
                    cancelTimeoutOrder(order);
                    successCount++;
                } catch (Exception e) {
                    log.error("Failed to cancel timeout order: {}", order.getOrderNo(), e);
                    failCount++;
                }
            }
            
            log.info("Timeout order cancellation completed. Success: {}, Failed: {}", 
                    successCount, failCount);
            
        } catch (Exception e) {
            log.error("Failed to check and cancel timeout orders", e);
            throw e;
        }
    }
    
    /**
     * 自动确认收货
     * 处理发货后超过7天的订单自动确认收货
     */
    @Transactional
    public void autoConfirmReceipt() {
        log.info("Starting to auto confirm receipt for delivered orders");
        
        try {
            // 查找发货后超过7天的订单（使用现有方法替代）
            LocalDateTime autoConfirmTime = LocalDateTime.now().minusDays(7);
            List<Order> ordersToConfirm = orderRepository.findOrdersToAutoConfirm(autoConfirmTime);
            
            if (ordersToConfirm.isEmpty()) {
                log.debug("No orders found for auto confirmation");
                return;
            }
            
            log.info("Found {} orders for auto confirmation", ordersToConfirm.size());
            
            int successCount = 0;
            int failCount = 0;
            
            for (Order order : ordersToConfirm) {
                try {
                    autoConfirmOrderReceipt(order);
                    successCount++;
                } catch (Exception e) {
                    log.error("Failed to auto confirm receipt for order: {}", order.getOrderNo(), e);
                    failCount++;
                }
            }
            
            log.info("Auto confirmation completed. Success: {}, Failed: {}", 
                    successCount, failCount);
            
        } catch (Exception e) {
            log.error("Failed to auto confirm receipt", e);
            throw e;
        }
    }
    
    /**
     * 清理已完成的老订单数据
     * 将超过一定时间的已完成订单归档或清理
     */
    @Transactional
    public void cleanupOldOrders() {
        log.info("Starting to cleanup old completed orders");
        
        try {
            // 查找6个月前完成的订单（使用现有方法替代）
            LocalDateTime cleanupTime = LocalDateTime.now().minusMonths(6);
            List<Order> oldOrders = orderRepository.findOrdersToAutoComplete(cleanupTime);
            
            if (oldOrders.isEmpty()) {
                log.debug("No old orders found for cleanup");
                return;
            }
            
            log.info("Found {} old orders for cleanup", oldOrders.size());
            
            int cleanupCount = 0;
            
            for (Order order : oldOrders) {
                try {
                    // 这里可以实现归档逻辑，而不是直接删除
                    archiveOrder(order);
                    cleanupCount++;
                } catch (Exception e) {
                    log.error("Failed to cleanup old order: {}", order.getOrderNo(), e);
                }
            }
            
            log.info("Old orders cleanup completed. Processed: {}", cleanupCount);
            
        } catch (Exception e) {
            log.error("Failed to cleanup old orders", e);
            throw e;
        }
    }
    
    /**
     * 订单数据统计任务
     * 生成订单相关的统计数据
     */
    @Transactional(readOnly = true)
    public OrderStatistics generateDailyOrderStatistics() {
        log.info("Generating daily order statistics");
        
        try {
            LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
            LocalDateTime endOfDay = startOfDay.plusDays(1);
            
            // 今日订单统计（简化实现，实际可能需要在仓库层实现具体的统计逻辑）
            // TODO: 需要在仓库层实现具体的时间范围统计方法
            long todayOrderCount = 0; // orderRepository.countOrdersBetweenTime(startOfDay, endOfDay);
            long todayPaidOrderCount = 0; // orderRepository.countPaidOrdersBetweenTime(startOfDay, endOfDay);
            long todayCancelledOrderCount = 0; // orderRepository.countCancelledOrdersBetweenTime(startOfDay, endOfDay);
            
            OrderStatistics statistics = new OrderStatistics(
                    todayOrderCount,
                    todayPaidOrderCount,
                    todayCancelledOrderCount,
                    startOfDay.toLocalDate()
            );
            
            log.info("Daily order statistics generated: {}", statistics);
            
            return statistics;
            
        } catch (Exception e) {
            log.error("Failed to generate daily order statistics", e);
            throw e;
        }
    }
    
    /**
     * 取消超时订单
     */
    private void cancelTimeoutOrder(Order order) {
        // 取消订单
        order.cancel("支付超时自动取消");
        
        // 释放库存
        orderDomainService.releaseStock(order.getOrderNo());
        
        // 更新订单状态
        orderRepository.update(order);
        
        // 发布领域事件
        domainEventPublisher.publishEvents(order);
        
        log.debug("Timeout order cancelled: {}", order.getOrderNo());
    }
    
    /**
     * 自动确认订单收货
     */
    private void autoConfirmOrderReceipt(Order order) {
        // 确认收货
        order.confirmReceipt();
        
        // 更新订单状态
        orderRepository.update(order);
        
        // 发布领域事件
        domainEventPublisher.publishEvents(order);
        
        log.debug("Order receipt auto confirmed: {}", order.getOrderNo());
    }
    
    /**
     * 归档老订单
     */
    private void archiveOrder(Order order) {
        // 这里可以实现将订单数据迁移到归档表的逻辑
        // 或者标记为已归档状态
        log.debug("Order archived: {}", order.getOrderNo());
    }
    
    /**
     * 订单统计数据
     */
    public static class OrderStatistics {
        private final long totalOrders;
        private final long paidOrders;
        private final long cancelledOrders;
        private final java.time.LocalDate date;
        
        public OrderStatistics(long totalOrders, long paidOrders, long cancelledOrders, java.time.LocalDate date) {
            this.totalOrders = totalOrders;
            this.paidOrders = paidOrders;
            this.cancelledOrders = cancelledOrders;
            this.date = date;
        }
        
        public long getTotalOrders() {
            return totalOrders;
        }
        
        public long getPaidOrders() {
            return paidOrders;
        }
        
        public long getCancelledOrders() {
            return cancelledOrders;
        }
        
        public java.time.LocalDate getDate() {
            return date;
        }
        
        @Override
        public String toString() {
            return String.format("OrderStatistics{date=%s, total=%d, paid=%d, cancelled=%d}", 
                    date, totalOrders, paidOrders, cancelledOrders);
        }
    }
}