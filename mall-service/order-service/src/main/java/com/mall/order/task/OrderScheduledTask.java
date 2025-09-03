package com.mall.order.task;

import com.mall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单定时任务
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderScheduledTask {
    
    private final OrderService orderService;
    
    /**
     * 检查订单超时
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void checkOrderTimeout() {
        log.info("Starting order timeout check task");
        try {
            orderService.checkOrderTimeout();
            log.info("Order timeout check task completed");
        } catch (Exception e) {
            log.error("Failed to check order timeout", e);
        }
    }
    
    /**
     * 自动确认收货
     * 每天凌晨2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void autoConfirmReceipt() {
        log.info("Starting auto confirm receipt task");
        try {
            orderService.autoConfirmReceipt();
            log.info("Auto confirm receipt task completed");
        } catch (Exception e) {
            log.error("Failed to auto confirm receipt", e);
        }
    }
    
    /**
     * 自动完成订单
     * 每天凌晨3点执行
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void autoCompleteOrder() {
        log.info("Starting auto complete order task");
        try {
            orderService.autoCompleteOrder();
            log.info("Auto complete order task completed");
        } catch (Exception e) {
            log.error("Failed to auto complete order", e);
        }
    }
}