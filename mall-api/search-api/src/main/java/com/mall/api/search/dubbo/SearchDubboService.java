package com.mall.api.search.dubbo;

import com.mall.api.search.dto.request.*;
import com.mall.api.search.dto.response.*;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Search Dubbo RPC service interface
 */
public interface SearchDubboService {
    
    /**
     * Search products
     */
    Result<SearchResultVO<ProductSearchVO>> searchProducts(SearchRequest request);
    
    /**
     * Get search suggestions
     */
    Result<List<SuggestVO>> getSuggestions(SuggestRequest request);
    
    /**
     * Get hot keywords
     */
    Result<List<HotKeywordVO>> getHotKeywords(Integer size);
    
    /**
     * Get hot keywords by category
     */
    Result<List<HotKeywordVO>> getHotKeywordsByCategory(Long categoryId, Integer size);
    
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
     * Sync product to index
     */
    Result<Void> syncProductToIndex(IndexSyncRequest request);
    
    /**
     * Batch sync products to index
     */
    Result<Void> batchSyncProductsToIndex(List<IndexSyncRequest> requests);
    
    /**
     * Remove product from index
     */
    Result<Void> removeProductFromIndex(Long productId);
    
    /**
     * Batch remove products from index
     */
    Result<Void> batchRemoveProductsFromIndex(List<Long> productIds);
    
    /**
     * Rebuild index
     */
    Result<Void> rebuildIndex(String indexType);
    
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