package com.mall.search.service;

import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.request.SearchHistoryRequest;
import com.mall.api.search.dto.response.ProductSearchVO;
import com.mall.api.search.dto.response.SearchHistoryVO;
import com.mall.api.search.dto.response.SearchResultVO;
import com.mall.api.search.dto.response.SearchStatisticsVO;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Search service interface
 */
public interface SearchService {
    
    /**
     * Search products
     */
    Result<SearchResultVO<ProductSearchVO>> searchProducts(ProductSearchQuery request);
    
    /**
     * Save search history
     */
    Result<Void> saveSearchHistory(Long userId, String keyword, String searchType);
    
    /**
     * Get user search history
     */
    Result<List<SearchHistoryVO>> getUserSearchHistory(SearchHistoryRequest request);
    
    /**
     * Clear user search history
     */
    Result<Void> clearUserSearchHistory(Long userId);
    
    /**
     * Delete specific search history
     */
    Result<Void> deleteSearchHistory(Long userId, Long historyId);
    
    /**
     * Get search statistics
     */
    Result<SearchStatisticsVO> getSearchStatistics();
    
    /**
     * Get search statistics by date range
     */
    Result<SearchStatisticsVO> getSearchStatisticsByDateRange(String startDate, String endDate);
    
    /**
     * Record search click
     */
    Result<Void> recordSearchClick(String trackingId, Long productId, Integer position);
    
    /**
     * Record search conversion
     */
    Result<Void> recordSearchConversion(String trackingId, Long orderId);
}
