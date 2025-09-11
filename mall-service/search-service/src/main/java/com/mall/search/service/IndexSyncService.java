package com.mall.search.service;

import com.mall.api.search.dto.request.IndexSyncRequest;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Index sync service interface
 */
public interface IndexSyncService {
    
    /**
     * Sync product to index
     */
    Result<Void> syncProductToIndex(IndexSyncRequest request);
    
    /**
     * Batch sync products to index
     */
    default Result<Void> batchSyncProductsToIndex(List<IndexSyncRequest> requests) {
        for (IndexSyncRequest request : requests) {
            Result<Void> result = syncProductToIndex(request);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return Result.success();
    }
    
    /**
     * Async sync product to index
     */
    void asyncSyncProductToIndex(Long spuId);
    
    /**
     * Async remove product from index
     */
    void asyncRemoveProductFromIndex(Long spuId);
    
    /**
     * Rebuild index
     */
    Result<Void> rebuildIndex(String indexType);
}