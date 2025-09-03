package com.mall.payment.handler;

import com.mall.api.payment.dto.request.PaymentCallbackRequest;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.request.RefundApplyRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dto.response.RefundResultVO;
import com.mall.api.payment.enums.PaymentStatusEnum;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.entity.RefundOrder;

import java.math.BigDecimal;

/**
 * Payment handler interface
 */
public interface PaymentHandler {
    
    /**
     * Get payment type
     */
    Integer getPaymentType();
    
    /**
     * Create payment
     */
    PaymentResultVO createPayment(PaymentOrder paymentOrder, PaymentCreateRequest request);
    
    /**
     * Get payment URL
     */
    PaymentResultVO getPaymentUrl(PaymentOrder paymentOrder);
    
    /**
     * Query payment status
     */
    PaymentStatusEnum queryPaymentStatus(PaymentOrder paymentOrder);
    
    /**
     * Close payment
     */
    void closePayment(PaymentOrder paymentOrder);
    
    /**
     * Apply refund
     */
    RefundResultVO applyRefund(RefundOrder refundOrder, RefundApplyRequest request);
    
    /**
     * Query refund status
     */
    RefundResultVO queryRefundStatus(RefundOrder refundOrder);
    
    /**
     * Calculate payment fee
     */
    BigDecimal calculateFee(BigDecimal amount);
    
    /**
     * Verify callback signature
     */
    boolean verifySignature(PaymentCallbackRequest request);
}