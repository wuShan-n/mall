package com.mall.search.controller;

import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.request.SearchHistoryRequest;
import com.mall.api.search.dto.request.SuggestRequest;
import com.mall.api.search.dto.response.*;
import com.mall.common.result.Result;
import com.mall.search.service.SearchService;
import com.mall.search.service.SuggestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Search controller
 * 
 * @author mall
 */
@Tag(name = "Search API", description = "Product search endpoints")
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final SuggestService suggestService;

    @Operation(summary = "Search products")
    @PostMapping("/products")
    public Result<SearchResultVO<ProductSearchVO>> searchProducts(@Valid @RequestBody ProductSearchQuery request) {
        return searchService.searchProducts(request);
    }

    @Operation(summary = "Get search suggestions")
    @GetMapping("/suggest")
    public Result<List<SuggestVO>> getSuggestions(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "product") String type,
            @RequestParam(defaultValue = "10") Integer size) {
        SuggestRequest request = new SuggestRequest();
        request.setKeyword(keyword);
        request.setSuggestType(type);
        request.setSize(size);
        return suggestService.getSuggestions(request);
    }

    @Operation(summary = "Get advanced suggestions")
    @PostMapping("/suggest/advanced")
    public Result<List<SuggestVO>> getAdvancedSuggestions(@Valid @RequestBody SuggestRequest request) {
        return suggestService.getSuggestions(request);
    }

    @Operation(summary = "Get hot keywords")
    @GetMapping("/hot-keywords")
    public Result<List<HotKeywordVO>> getHotKeywords(@RequestParam(defaultValue = "20") Integer size) {
        return suggestService.getHotKeywords(size);
    }

    @Operation(summary = "Get hot keywords by category")
    @GetMapping("/hot-keywords/category/{categoryId}")
    public Result<List<HotKeywordVO>> getHotKeywordsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "20") Integer size) {
        return suggestService.getHotKeywordsByCategory(categoryId, size);
    }

    @Operation(summary = "Save search history")
    @PostMapping("/history/save")
    public Result<Void> saveSearchHistory(
            @RequestParam Long userId,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "product") String searchType) {
        return searchService.saveSearchHistory(userId, keyword, searchType);
    }

    @Operation(summary = "Get user search history")
    @GetMapping("/history/user/{userId}")
    public Result<List<SearchHistoryVO>> getUserSearchHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "20") Integer size) {
        SearchHistoryRequest request = new SearchHistoryRequest();
        request.setUserId(userId);
        request.setSize(size);
        return searchService.getUserSearchHistory(request);
    }

    @Operation(summary = "Query search history")
    @PostMapping("/history/query")
    public Result<List<SearchHistoryVO>> querySearchHistory(@Valid @RequestBody SearchHistoryRequest request) {
        return searchService.getUserSearchHistory(request);
    }

    @Operation(summary = "Clear user search history")
    @DeleteMapping("/history/user/{userId}")
    public Result<Void> clearUserSearchHistory(@PathVariable Long userId) {
        return searchService.clearUserSearchHistory(userId);
    }

    @Operation(summary = "Delete specific search history")
    @DeleteMapping("/history/user/{userId}/item/{historyId}")
    public Result<Void> deleteSearchHistory(@PathVariable Long userId, @PathVariable Long historyId) {
        return searchService.deleteSearchHistory(userId, historyId);
    }

    @Operation(summary = "Get search statistics")
    @GetMapping("/statistics")
    public Result<SearchStatisticsVO> getSearchStatistics() {
        return searchService.getSearchStatistics();
    }

    @Operation(summary = "Get search statistics by date range")
    @GetMapping("/statistics/range")
    public Result<SearchStatisticsVO> getSearchStatisticsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return searchService.getSearchStatisticsByDateRange(startDate, endDate);
    }

    @Operation(summary = "Record search click")
    @PostMapping("/tracking/click")
    public Result<Void> recordSearchClick(
            @RequestParam String trackingId,
            @RequestParam Long productId,
            @RequestParam Integer position) {
        return searchService.recordSearchClick(trackingId, productId, position);
    }

    @Operation(summary = "Record search conversion")
    @PostMapping("/tracking/conversion")
    public Result<Void> recordSearchConversion(
            @RequestParam String trackingId,
            @RequestParam Long orderId) {
        return searchService.recordSearchConversion(trackingId, orderId);
    }
}