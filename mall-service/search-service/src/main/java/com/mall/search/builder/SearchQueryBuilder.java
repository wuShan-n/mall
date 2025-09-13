package com.mall.search.builder;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;

/**
 * 搜索查询构建器 负责构建ES查询请求的所有组件
 */
public interface SearchQueryBuilder {

    /**
     * 构建完整的搜索请求
     */
    SearchRequest buildSearchRequest(ProductSearchRequest request, SearchStrategy strategy);

    /**
     * 构建主查询
     */
    Query buildMainQuery(ProductSearchRequest request, SearchStrategy strategy);

    /**
     * 构建过滤查询
     */
    Query buildFilterQuery(ProductSearchRequest request);

    /**
     * 构建Function Score查询
     */
    Query buildFunctionScoreQuery(Query baseQuery, ProductSearchRequest request, SearchStrategy strategy);
}
