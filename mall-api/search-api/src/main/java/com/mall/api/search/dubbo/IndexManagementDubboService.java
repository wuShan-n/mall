package com.mall.api.search.dubbo;

import com.mall.common.result.Result;

import java.util.Map;

/**
 * Index management Dubbo RPC service interface
 */
public interface IndexManagementDubboService {
    
    /**
     * Create index
     */
    Result<Boolean> createIndex(String indexName, Map<String, Object> mappings, Map<String, Object> settings);
    
    /**
     * Delete index
     */
    Result<Boolean> deleteIndex(String indexName);
    
    /**
     * Check if index exists
     */
    Result<Boolean> indexExists(String indexName);
    
    /**
     * Update index mapping
     */
    Result<Boolean> updateIndexMapping(String indexName, Map<String, Object> mappings);
    
    /**
     * Update index settings
     */
    Result<Boolean> updateIndexSettings(String indexName, Map<String, Object> settings);
    
    /**
     * Get index mapping
     */
    Result<Map<String, Object>> getIndexMapping(String indexName);
    
    /**
     * Get index settings
     */
    Result<Map<String, Object>> getIndexSettings(String indexName);
    
    /**
     * Get index statistics
     */
    Result<Map<String, Object>> getIndexStats(String indexName);
    
    /**
     * Refresh index
     */
    Result<Boolean> refreshIndex(String indexName);
    
    /**
     * Flush index
     */
    Result<Boolean> flushIndex(String indexName);
    
    /**
     * Optimize index
     */
    Result<Boolean> optimizeIndex(String indexName);
    
    /**
     * Create index alias
     */
    Result<Boolean> createIndexAlias(String indexName, String aliasName);
    
    /**
     * Delete index alias
     */
    Result<Boolean> deleteIndexAlias(String indexName, String aliasName);
    
    /**
     * Reindex data
     */
    Result<Boolean> reindex(String sourceIndex, String targetIndex);
    
    /**
     * Get cluster health
     */
    Result<Map<String, Object>> getClusterHealth();
}