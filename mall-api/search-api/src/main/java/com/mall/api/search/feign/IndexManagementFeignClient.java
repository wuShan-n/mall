package com.mall.api.search.feign;

import com.mall.api.search.constant.SearchConstants;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Index management Feign client for HTTP calls
 */
@FeignClient(name = SearchConstants.SERVICE_NAME, path = SearchConstants.API_PREFIX + "/index")
public interface IndexManagementFeignClient {

    @PostMapping("/create")
    Result<Boolean> createIndex(@RequestParam("indexName") String indexName,
                                @RequestBody Map<String, Object> config);

    @DeleteMapping("/{indexName}")
    Result<Boolean> deleteIndex(@PathVariable("indexName") String indexName);

    @GetMapping("/{indexName}/exists")
    Result<Boolean> indexExists(@PathVariable("indexName") String indexName);

    @PutMapping("/{indexName}/mapping")
    Result<Boolean> updateIndexMapping(@PathVariable("indexName") String indexName,
                                       @RequestBody Map<String, Object> mappings);

    @PutMapping("/{indexName}/settings")
    Result<Boolean> updateIndexSettings(@PathVariable("indexName") String indexName,
                                        @RequestBody Map<String, Object> settings);

    @GetMapping("/{indexName}/mapping")
    Result<Map<String, Object>> getIndexMapping(@PathVariable("indexName") String indexName);

    @GetMapping("/{indexName}/settings")
    Result<Map<String, Object>> getIndexSettings(@PathVariable("indexName") String indexName);

    @GetMapping("/{indexName}/stats")
    Result<Map<String, Object>> getIndexStats(@PathVariable("indexName") String indexName);

    @PostMapping("/{indexName}/refresh")
    Result<Boolean> refreshIndex(@PathVariable("indexName") String indexName);

    @PostMapping("/{indexName}/flush")
    Result<Boolean> flushIndex(@PathVariable("indexName") String indexName);

    @PostMapping("/{indexName}/optimize")
    Result<Boolean> optimizeIndex(@PathVariable("indexName") String indexName);

    @PostMapping("/{indexName}/alias/{aliasName}")
    Result<Boolean> createIndexAlias(@PathVariable("indexName") String indexName,
                                     @PathVariable("aliasName") String aliasName);

    @DeleteMapping("/{indexName}/alias/{aliasName}")
    Result<Boolean> deleteIndexAlias(@PathVariable("indexName") String indexName,
                                     @PathVariable("aliasName") String aliasName);

    @PostMapping("/reindex")
    Result<Boolean> reindex(@RequestParam("sourceIndex") String sourceIndex,
                           @RequestParam("targetIndex") String targetIndex);

    @GetMapping("/cluster/health")
    Result<Map<String, Object>> getClusterHealth();
}