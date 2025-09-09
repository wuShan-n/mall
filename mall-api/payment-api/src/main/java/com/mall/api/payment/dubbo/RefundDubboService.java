package com.mall.api.payment.dubbo;

import com.mall.api.payment.dto.request.RefundApplyRequest;
import com.mall.api.payment.dto.request.RefundCallbackRequest;
import com.mall.api.payment.dto.request.RefundQueryRequest;
import com.mall.api.payment.dto.response.RefundDetailVO;
import com.mall.api.payment.dto.response.RefundResultVO;
import com.mall.api.payment.dto.response.RefundVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Refund Dubbo RPC service interface
 */
public interface RefundDubboService {
    
    /**
     * Apply for refund
     */
    Result<RefundResultVO> applyRefund(RefundApplyRequest request);
    
    /**
     * Process refund callback
     */
    Result<Void> processRefundCallback(RefundCallbackRequest request);
    
    /**
     * Query refund by refund number
     */
    Result<RefundVO> queryRefundByNo(String refundNo);
    
    /**
     * Query refunds by payment number
     */
    Result<List<RefundVO>> queryRefundsByPaymentNo(String paymentNo);
    
    /**
     * Query refunds by order number
     */
    Result<List<RefundVO>> queryRefundsByOrderNo(String orderNo);
    
    /**
     * Query refunds with pagination
     */
    Result<PageResult<RefundVO>> queryRefunds(RefundQueryRequest request);
    
    /**
     * Cancel refund
     */
    Result<Void> cancelRefund(String refundNo);
    
    /**
     * Retry refund
     */
    Result<RefundResultVO> retryRefund(String refundNo);
    
    /**
     * Check refund status
     */
    Result<RefundVO> checkRefundStatus(String refundNo);
    
    /**
     * Get refund detail
     */
    Result<RefundDetailVO> getRefundDetail(String refundNo);
}
