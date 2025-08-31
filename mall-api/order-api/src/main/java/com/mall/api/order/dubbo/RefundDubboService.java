package com.mall.api.order.dubbo;

import com.mall.api.order.dto.request.RefundCreateRequest;
import com.mall.api.order.dto.request.RefundDetailVO;
import com.mall.api.order.dto.request.RefundProcessRequest;
import com.mall.api.order.dto.request.RefundQueryRequest;
import com.mall.api.order.dto.response.RefundVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

/**
 * Refund Dubbo RPC service interface
 */
public interface RefundDubboService {
    
    /**
     * Create refund
     */
    Result<RefundVO> createRefund(Long userId, RefundCreateRequest request);
    
    /**
     * Cancel refund
     */
    Result<Void> cancelRefund(Long userId, String refundNo);
    
    /**
     * Process refund
     */
    Result<Void> processRefund(RefundProcessRequest request);
    
    /**
     * Approve refund
     */
    Result<Void> approveRefund(String refundNo, String handleNote);
    
    /**
     * Reject refund
     */
    Result<Void> rejectRefund(String refundNo, String handleNote);
    
    /**
     * Complete refund
     */
    Result<Void> completeRefund(String refundNo);
    
    /**
     * Get refund by refund number
     */
    Result<RefundVO> getRefundByNo(String refundNo);
    
    /**
     * Get refund detail
     */
    Result<RefundDetailVO> getRefundDetail(String refundNo);
    
    /**
     * Query refunds with pagination
     */
    Result<PageResult<RefundVO>> queryRefunds(RefundQueryRequest request);
    
    /**
     * Get user refunds
     */
    Result<PageResult<RefundVO>> getUserRefunds(Long userId, Integer status, PageRequest pageRequest);
    
    /**
     * Get order refunds
     */
    Result<List<RefundVO>> getOrderRefunds(String orderNo);
    
    /**
     * Check refund status
     */
    Result<RefundVO> checkRefundStatus(String refundNo);
}