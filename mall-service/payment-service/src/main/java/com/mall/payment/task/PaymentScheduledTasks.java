package com.mall.payment.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.payment.dto.response.RefundResultVO;
import com.mall.api.payment.enums.PaymentStatusEnum;
import com.mall.api.payment.enums.RefundStatusEnum;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.entity.RefundOrder;
import com.mall.payment.handler.PaymentHandler;
import com.mall.payment.handler.PaymentHandlerFactory;
import com.mall.payment.service.PaymentOrderService;
import com.mall.payment.service.RefundOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Payment scheduled tasks
 */
@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class PaymentScheduledTasks {
    
    private final PaymentOrderService paymentOrderService;
    private final RefundOrderService refundOrderService;
    private final PaymentHandlerFactory paymentHandlerFactory;
    
    /**
     * Check expired payment orders (every 5 minutes)
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void checkExpiredPayments() {
        log.info("Starting expired payment check task");
        
        try {
            LocalDateTime now = LocalDateTime.now();
            
            // Query expired pending payments
            LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaymentOrder::getStatus, PaymentStatusEnum.PENDING.getCode())
                       .lt(PaymentOrder::getExpireTime, now);
            
            List<PaymentOrder> expiredOrders = paymentOrderService.list(queryWrapper);
            
            for (PaymentOrder order : expiredOrders) {
                try {
                    // Close payment with third party
                    PaymentHandler handler = paymentHandlerFactory.getHandler(order.getPaymentType());
                    handler.closePayment(order);
                    
                    // Update status to expired
                    order.setStatus(PaymentStatusEnum.EXPIRED.getCode());
                    order.setUpdateTime(LocalDateTime.now());
                    paymentOrderService.updateById(order);
                    
                    log.info("Closed expired payment: {}", order.getPaymentNo());
                    
                } catch (Exception e) {
                    log.error("Failed to close expired payment: {}", order.getPaymentNo(), e);
                }
            }
            
            log.info("Expired payment check task completed. Processed {} orders", expiredOrders.size());
            
        } catch (Exception e) {
            log.error("Error in expired payment check task", e);
        }
    }
    
    /**
     * Query pending payment status from third party (every 10 minutes)
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void queryPendingPayments() {
        log.info("Starting pending payment query task");
        
        try {
            // Query recent pending payments (created within last hour)
            LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
            
            LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaymentOrder::getStatus, PaymentStatusEnum.PENDING.getCode())
                       .gt(PaymentOrder::getCreateTime, oneHourAgo);
            
            List<PaymentOrder> pendingOrders = paymentOrderService.list(queryWrapper);
            
            for (PaymentOrder order : pendingOrders) {
                try {
                    // Query payment status from third party
                    PaymentHandler handler = paymentHandlerFactory.getHandler(order.getPaymentType());
                    PaymentStatusEnum status = handler.queryPaymentStatus(order);
                    
                    if (status != null && !status.equals(PaymentStatusEnum.PENDING)) {
                        // Update local status
                        order.setStatus(status.getCode());
                        order.setUpdateTime(LocalDateTime.now());
                        
                        if (status.equals(PaymentStatusEnum.PAID)) {
                            order.setPaymentTime(LocalDateTime.now());
                        }
                        
                        paymentOrderService.updateById(order);
                        
                        log.info("Updated payment status: {} -> {}", order.getPaymentNo(), status);
                    }
                    
                } catch (Exception e) {
                    log.error("Failed to query payment status: {}", order.getPaymentNo(), e);
                }
            }
            
            log.info("Pending payment query task completed. Processed {} orders", pendingOrders.size());
            
        } catch (Exception e) {
            log.error("Error in pending payment query task", e);
        }
    }
    
    /**
     * Query processing refund status from third party (every 15 minutes)
     */
    @Scheduled(cron = "0 */15 * * * ?")
    public void queryProcessingRefunds() {
        log.info("Starting processing refund query task");
        
        try {
            // Query processing refunds
            LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(RefundOrder::getStatus, RefundStatusEnum.PROCESSING.getCode());
            
            List<RefundOrder> processingRefunds = refundOrderService.list(queryWrapper);
            
            for (RefundOrder refund : processingRefunds) {
                try {
                    // Get payment order to determine payment type
                    LambdaQueryWrapper<PaymentOrder> paymentQuery = new LambdaQueryWrapper<>();
                    paymentQuery.eq(PaymentOrder::getPaymentNo, refund.getPaymentNo());
                    PaymentOrder paymentOrder = paymentOrderService.getOne(paymentQuery);
                    
                    if (paymentOrder != null) {
                        // Query refund status from third party
                        PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
                        RefundResultVO result = handler.queryRefundStatus(refund);
                        
                        if (result.getSuccess()) {
                            // Update refund status
                            refund.setStatus(RefundStatusEnum.SUCCESS.getCode());
                            refund.setRefundTime(LocalDateTime.now());
                            refund.setUpdateTime(LocalDateTime.now());
                            refundOrderService.updateById(refund);
                            
                            log.info("Updated refund status to success: {}", refund.getRefundNo());
                        }
                    }
                    
                } catch (Exception e) {
                    log.error("Failed to query refund status: {}", refund.getRefundNo(), e);
                }
            }
            
            log.info("Processing refund query task completed. Processed {} refunds", processingRefunds.size());
            
        } catch (Exception e) {
            log.error("Error in processing refund query task", e);
        }
    }
    
    /**
     * Clean up old payment records (daily at 2:00 AM)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanupOldRecords() {
        log.info("Starting old records cleanup task");
        
        try {
            // Delete expired and cancelled payments older than 30 days
            LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
            
            LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(PaymentOrder::getStatus, 
                           PaymentStatusEnum.EXPIRED.getCode(), 
                           PaymentStatusEnum.CANCELLED.getCode())
                       .lt(PaymentOrder::getCreateTime, thirtyDaysAgo);
            
            int deletedCount = paymentOrderService.remove(queryWrapper) ? 1 : 0;
            
            log.info("Old records cleanup task completed. Deleted {} records", deletedCount);
            
        } catch (Exception e) {
            log.error("Error in old records cleanup task", e);
        }
    }
}