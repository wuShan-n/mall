package com.mall.api.payment.dubbo;

import com.mall.api.payment.dto.request.PaymentCallbackRequest;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.request.PaymentNotifyRequest;
import com.mall.api.payment.dto.request.PaymentQueryRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dto.response.PaymentVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.math.BigDecimal;

/**
 * Payment Dubbo RPC service interface
 */
public interface PaymentDubboService {
    
    /**
     * Create payment order
     */
    Result<PaymentResultVO> createPayment(PaymentCreateRequest request);
    
    /**
     * Process payment callback
     */
    Result<Void> processCallback(PaymentCallbackRequest request);
    
    /**
     * Process payment notify
     */
    Result<Void> processNotify(PaymentNotifyRequest request);
    
    /**
     * Query payment by payment number
     */
    Result<PaymentVO> queryPaymentByNo(String paymentNo);
    
    /**
     * Query payment by order number
     */
    Result<PaymentVO> queryPaymentByOrderNo(String orderNo);
    
    /**
     * Query payments with pagination
     */
    Result<PageResult<PaymentVO>> queryPayments(PaymentQueryRequest request);
    
    /**
     * Cancel payment
     */
    Result<Void> cancelPayment(String paymentNo);
    
    /**
     * Close payment
     */
    Result<Void> closePayment(String paymentNo);
    
    /**
     * Check payment status
     */
    Result<PaymentVO> checkPaymentStatus(String paymentNo);
    
    /**
     * Get payment URL
     */
    Result<String> getPaymentUrl(String paymentNo);
    
    /**
     * Calculate payment fee
     */
    Result<BigDecimal> calculatePaymentFee(Integer paymentType, BigDecimal amount);
    
    /**
     * Verify payment signature
     */
    Result<Boolean> verifySignature(PaymentCallbackRequest request);
}