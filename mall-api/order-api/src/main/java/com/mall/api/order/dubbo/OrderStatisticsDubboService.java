package com.mall.api.order.dubbo;


import com.mall.api.order.dto.response.CustomerOrderVO;
import com.mall.api.order.dto.response.OrderStatisticsVO;
import com.mall.api.order.dto.response.ProductSalesVO;
import com.mall.api.order.dto.response.RefundStatisticsVO;
import com.mall.common.result.Result;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Order statistics Dubbo RPC service interface
 */
public interface OrderStatisticsDubboService {
    
    /**
     * Get overall statistics
     */
    Result<OrderStatisticsVO> getOverallStatistics();
    
    /**
     * Get statistics by date range
     */
    Result<OrderStatisticsVO> getStatisticsByDateRange(LocalDate startDate, LocalDate endDate);
    
    /**
     * Get daily statistics
     */
    Result<Map<String, OrderStatisticsVO>> getDailyStatistics(LocalDate startDate, LocalDate endDate);
    
    /**
     * Get monthly statistics
     */
    Result<Map<String, OrderStatisticsVO>> getMonthlyStatistics(Integer year);
    
    /**
     * Get top selling products
     */
    Result<List<ProductSalesVO>> getTopSellingProducts(Integer limit);
    
    /**
     * Get top customers
     */
    Result<List<CustomerOrderVO>> getTopCustomers(Integer limit);
    
    /**
     * Get sales by category
     */
    Result<Map<String, BigDecimal>> getSalesByCategory();
    
    /**
     * Get order source distribution
     */
    Result<Map<String, Long>> getOrderSourceDistribution();
    
    /**
     * Get payment type distribution
     */
    Result<Map<String, Long>> getPaymentTypeDistribution();
    
    /**
     * Get refund statistics
     */
    Result<RefundStatisticsVO> getRefundStatistics();
}