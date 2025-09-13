package com.mall.search.processor;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.ProductSearchResponse;

/**
 * 搜索结果处理器 负责处理和转换搜索结果
 */
public interface SearchResultProcessor {

    /**
     * 处理搜索响应
     */
    ProductSearchResponse processSearchResponse(
            SearchResponse<ProductSearchDocument> response,
            ProductSearchRequest request,
            long searchTime
    );
}
