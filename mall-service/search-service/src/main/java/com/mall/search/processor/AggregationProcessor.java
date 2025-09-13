package com.mall.search.processor;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.SearchAggregations;
import com.mall.search.dto.response.SearchFilters;

import java.util.Map;

/**
 * 聚合处理器 负责处理各种聚合结果
 */
public interface AggregationProcessor {

    /**
     * 处理搜索聚合结果
     */
    SearchAggregations processSearchAggregations(
            Map<String, Aggregate> aggregates,
            ProductSearchRequest request
    );

    /**
     * 处理筛选项聚合结果
     */
    SearchFilters processFilterAggregations(
            Map<String, Aggregate> aggregates,
            FilterRequest request
    );
}
