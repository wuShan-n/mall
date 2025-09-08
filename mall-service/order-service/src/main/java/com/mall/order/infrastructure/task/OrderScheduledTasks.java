package com.mall.order.infrastructure.task;

import com.mall.order.application.service.OrderScheduleApplicationService;
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
public class OrderScheduledTasks {
    
    private final OrderScheduleApplicationService orderScheduleApplicationService;
    
    /**
     * 检查超时订单
     * 每5分钟执行一次
     */
    @Scheduled(fixedDelay = 300000)
    public void checkOrderTimeout() {
        log.info("Starting check order timeout task");
        try {
            orderScheduleApplicationService.checkAndCancelTimeoutOrders();
        } catch (Exception e) {
            log.error("Check order timeout task failed", e);
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
            orderScheduleApplicationService.autoConfirmReceipt();
        } catch (Exception e) {
            log.error("Auto confirm receipt task failed", e);
        }
    }
    
    /**
     * 清理老订单
     * 每天凌晨3点执行
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void cleanupOldOrders() {
        log.info("Starting cleanup old orders task");
        try {
            orderScheduleApplicationService.cleanupOldOrders();
        } catch (Exception e) {
            log.error("Cleanup old orders task failed", e);
        }
    }
}