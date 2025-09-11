package com.mall.api.search.feign;

import com.mall.api.search.constant.SearchConstants;
import com.mall.api.search.dto.request.*;
import com.mall.api.search.dto.response.*;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Search Feign client for HTTP calls
 */
@FeignClient(name = SearchConstants.SERVICE_NAME, path = SearchConstants.API_PREFIX)
public interface SearchFeignClient {

    @PostMapping("/products")
    Result<SearchResultVO<ProductSearchVO>> searchProducts(@RequestBody ProductSearchQuery request);

    @GetMapping("/suggest")
    Result<List<SuggestVO>> getSuggestions(@RequestParam("keyword") String keyword,
                                           @RequestParam(value = "type", defaultValue = "product") String type,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size);

    @PostMapping("/suggest/advanced")
    Result<List<SuggestVO>> getAdvancedSuggestions(@RequestBody SuggestRequest request);

    @GetMapping("/hot-keywords")
    Result<List<HotKeywordVO>> getHotKeywords(@RequestParam(value = "size", defaultValue = "20") Integer size);

    @GetMapping("/hot-keywords/category/{categoryId}")
    Result<List<HotKeywordVO>> getHotKeywordsByCategory(@PathVariable("categoryId") Long categoryId,
                                                        @RequestParam(value = "size", defaultValue = "20") Integer size);

    @PostMapping("/history/save")
    Result<Void> saveSearchHistory(@RequestParam("userId") Long userId,
                                   @RequestParam("keyword") String keyword,
                                   @RequestParam(value = "searchType", defaultValue = "product") String searchType);

    @GetMapping("/history/user/{userId}")
    Result<List<SearchHistoryVO>> getUserSearchHistory(@PathVariable("userId") Long userId,
                                                       @RequestParam(value = "size", defaultValue = "20") Integer size);

    @PostMapping("/history/query")
    Result<List<SearchHistoryVO>> querySearchHistory(@RequestBody SearchHistoryRequest request);

    @DeleteMapping("/history/user/{userId}")
    Result<Void> clearUserSearchHistory(@PathVariable("userId") Long userId);

    @DeleteMapping("/history/user/{userId}/item/{historyId}")
    Result<Void> deleteSearchHistory(@PathVariable("userId") Long userId,
                                     @PathVariable("historyId") Long historyId);

    @PostMapping("/index/sync")
    Result<Void> syncToIndex(@RequestBody IndexSyncRequest request);

    @PostMapping("/index/sync/batch")
    Result<Void> batchSyncToIndex(@RequestBody List<IndexSyncRequest> requests);

    @DeleteMapping("/index/product/{productId}")
    Result<Void> removeProductFromIndex(@PathVariable("productId") Long productId);

    @DeleteMapping("/index/products")
    Result<Void> batchRemoveProductsFromIndex(@RequestBody List<Long> productIds);

    @PostMapping("/index/rebuild/{indexType}")
    Result<Void> rebuildIndex(@PathVariable("indexType") String indexType);

    @GetMapping("/statistics")
    Result<SearchStatisticsVO> getSearchStatistics();

    @GetMapping("/statistics/range")
    Result<SearchStatisticsVO> getSearchStatisticsByDateRange(@RequestParam("startDate") String startDate,
                                                              @RequestParam("endDate") String endDate);

    @PostMapping("/tracking/click")
    Result<Void> recordSearchClick(@RequestParam("trackingId") String trackingId,
                                  @RequestParam("productId") Long productId,
                                  @RequestParam("position") Integer position);

    @PostMapping("/tracking/conversion")
    Result<Void> recordSearchConversion(@RequestParam("trackingId") String trackingId,
                                       @RequestParam("orderId") Long orderId);
}