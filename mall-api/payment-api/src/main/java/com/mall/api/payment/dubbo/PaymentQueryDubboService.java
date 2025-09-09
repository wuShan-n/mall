package com.mall.api.payment.dubbo;

import com.mall.api.payment.dto.response.PaymentStatisticsVO;
import com.mall.api.payment.dto.response.TransactionVO;
import com.mall.common.result.Result;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Payment query Dubbo RPC service interface
 */
public interface PaymentQueryDubboService {
    
    /**
     * Get payment statistics
     */
    Result<PaymentStatisticsVO> getPaymentStatistics();
    
    /**
     * Get payment statistics by date range
     */
    Result<PaymentStatisticsVO> getPaymentStatisticsByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Get user payment statistics
     */
    Result<PaymentStatisticsVO> getUserPaymentStatistics(Long userId);
    
    /**
     * Get daily payment statistics
     */
    Result<Map<String, BigDecimal>> getDailyPaymentStatistics(LocalDate date);
    
    /**
     * Get monthly payment statistics
     */
    Result<Map<String, BigDecimal>> getMonthlyPaymentStatistics(Integer year, Integer month);
    
    /**
     * Get payment type distribution
     */
    Result<Map<String, Long>> getPaymentTypeDistribution();
    
    /**
     * Get top payment users
     */
    Result<List<Map<String, Object>>> getTopPaymentUsers(Integer limit);
    
    /**
     * Get transaction records
     */
    Result<List<TransactionVO>> getTransactionRecords(Long userId, Integer type);
    
    /**
     * Get transaction detail
     */
    Result<TransactionVO> getTransactionDetail(String transactionNo);
}