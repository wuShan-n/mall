package com.mall.search.provider;

import com.mall.api.search.dto.request.IndexSyncRequest;
import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.request.SearchHistoryRequest;
import com.mall.api.search.dto.request.SuggestRequest;
import com.mall.api.search.dto.response.*;
import com.mall.api.search.dubbo.SearchDubboService;
import com.mall.common.result.Result;
import com.mall.search.service.IndexSyncService;
import com.mall.search.service.SearchService;
import com.mall.search.service.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Search Dubbo service provider implementation
 * 
 * @author mall
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 5000)
@RequiredArgsConstructor
public class SearchDubboServiceImpl implements SearchDubboService {

    private final SearchService searchService;
    private final SuggestService suggestService;
    private final IndexSyncService indexSyncService;

    @Override
    public Result<SearchResultVO<ProductSearchVO>> searchProducts(ProductSearchQuery request) {
        log.debug("Dubbo call: searchProducts, keyword={}", request.getKeyword());
        return searchService.searchProducts(request);
    }

    @Override
    public Result<List<SuggestVO>> getSuggestions(SuggestRequest request) {
        log.debug("Dubbo call: getSuggestions, keyword={}", request.getKeyword());
        return suggestService.getSuggestions(request);
    }

    @Override
    public Result<List<HotKeywordVO>> getHotKeywords(Integer size) {
        log.debug("Dubbo call: getHotKeywords, size={}", size);
        return suggestService.getHotKeywords(size);
    }

    @Override
    public Result<List<HotKeywordVO>> getHotKeywordsByCategory(Long categoryId, Integer size) {
        log.debug("Dubbo call: getHotKeywordsByCategory, categoryId={}, size={}", categoryId, size);
        return suggestService.getHotKeywordsByCategory(categoryId, size);
    }

    @Override
    public Result<Void> saveSearchHistory(Long userId, String keyword, String searchType) {
        log.debug("Dubbo call: saveSearchHistory, userId={}, keyword={}", userId, keyword);
        return searchService.saveSearchHistory(userId, keyword, searchType);
    }

    @Override
    public Result<List<SearchHistoryVO>> getUserSearchHistory(SearchHistoryRequest request) {
        log.debug("Dubbo call: getUserSearchHistory, userId={}", request.getUserId());
        return searchService.getUserSearchHistory(request);
    }

    @Override
    public Result<Void> clearUserSearchHistory(Long userId) {
        log.debug("Dubbo call: clearUserSearchHistory, userId={}", userId);
        return searchService.clearUserSearchHistory(userId);
    }

    @Override
    public Result<Void> deleteSearchHistory(Long userId, Long historyId) {
        log.debug("Dubbo call: deleteSearchHistory, userId={}, historyId={}", userId, historyId);
        return searchService.deleteSearchHistory(userId, historyId);
    }

    @Override
    public Result<Void> syncProductToIndex(IndexSyncRequest request) {
        log.debug("Dubbo call: syncProductToIndex, operation={}", request.getOperationType());
        return indexSyncService.syncProductToIndex(request);
    }

    @Override
    public Result<Void> batchSyncProductsToIndex(List<IndexSyncRequest> requests) {
        log.debug("Dubbo call: batchSyncProductsToIndex, count={}", requests.size());
        return indexSyncService.batchSyncProductsToIndex(requests);
    }

    @Override
    public Result<Void> removeProductFromIndex(Long productId) {
        log.debug("Dubbo call: removeProductFromIndex, productId={}", productId);
        IndexSyncRequest request = new IndexSyncRequest();
        request.setOperationType(IndexSyncRequest.OperationType.DELETE);
        request.setDocumentType(IndexSyncRequest.DocumentType.PRODUCT);
        request.setDocumentId(String.valueOf(productId));
        return indexSyncService.syncProductToIndex(request);
    }

    @Override
    public Result<Void> batchRemoveProductsFromIndex(List<Long> productIds) {
        log.debug("Dubbo call: batchRemoveProductsFromIndex, count={}", productIds.size());
        IndexSyncRequest request = new IndexSyncRequest();
        request.setOperationType(IndexSyncRequest.OperationType.BULK_DELETE);
        request.setDocumentType(IndexSyncRequest.DocumentType.PRODUCT);
        request.setDocumentIds(productIds.stream().map(String::valueOf).toList());
        return indexSyncService.syncProductToIndex(request);
    }

    @Override
    public Result<Void> rebuildIndex(String indexType) {
        log.info("Dubbo call: rebuildIndex, indexType={}", indexType);
        return indexSyncService.rebuildIndex(indexType);
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatistics() {
        log.debug("Dubbo call: getSearchStatistics");
        return searchService.getSearchStatistics();
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatisticsByDateRange(String startDate, String endDate) {
        log.debug("Dubbo call: getSearchStatisticsByDateRange, startDate={}, endDate={}", startDate, endDate);
        return searchService.getSearchStatisticsByDateRange(startDate, endDate);
    }

    @Override
    public Result<Void> recordSearchClick(String trackingId, Long productId, Integer position) {
        log.debug("Dubbo call: recordSearchClick, trackingId={}, productId={}, position={}", 
                trackingId, productId, position);
        return searchService.recordSearchClick(trackingId, productId, position);
    }

    @Override
    public Result<Void> recordSearchConversion(String trackingId, Long orderId) {
        log.debug("Dubbo call: recordSearchConversion, trackingId={}, orderId={}", trackingId, orderId);
        return searchService.recordSearchConversion(trackingId, orderId);
    }
}