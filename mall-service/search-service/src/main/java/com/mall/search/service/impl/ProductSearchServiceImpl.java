package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mall.search.builder.SearchQueryBuilder;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSimpleVO;
import com.mall.search.dto.response.SearchFilters;
import com.mall.search.processor.SearchResultProcessor;
import com.mall.search.service.FilterService;
import com.mall.search.service.ProductSearchService;
import com.mall.search.service.RelatedProductService;
import com.mall.search.strategy.SearchStrategy;
import com.mall.search.strategy.SearchStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品搜索服务实现 重构后的简化版本，职责清晰，委托给各个专门服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final SearchStrategyFactory searchStrategyFactory;
    private final SearchQueryBuilder searchQueryBuilder;
    private final SearchResultProcessor searchResultProcessor;
    private final RelatedProductService relatedProductService;
    private final FilterService filterService;

    @Override
    public ProductSearchResponse search(ProductSearchRequest request) {
        try {
            long startTime = System.currentTimeMillis();

            // 1. 获取搜索策略
            SearchStrategy strategy = searchStrategyFactory.getStrategy(request.getScene());
            if (strategy == null) {
                throw new IllegalArgumentException("Unsupported search scene: " + request.getScene());
            }

            // 2. 构建搜索请求
            var searchRequest = searchQueryBuilder.buildSearchRequest(request, strategy);

            // 3. 执行搜索
            SearchResponse<ProductSearchDocument> response = elasticsearchClient.search(
                    searchRequest,
                    ProductSearchDocument.class
            );

            // 4. 处理搜索结果
            long searchTime = System.currentTimeMillis() - startTime;
            ProductSearchResponse searchResponse = searchResultProcessor.processSearchResponse(
                    response,
                    request,
                    searchTime
            );

            log.info("Search completed. Keyword: {}, Total: {}, Took: {}ms",
                    request.getKeyword(), searchResponse.getTotal(), searchResponse.getTook());

            return searchResponse;

        } catch (Exception e) {
            log.error("Search error. Request: {}", request, e);
            throw new RuntimeException("搜索服务异常", e);
        }
    }

    @Override
    public SearchFilters getFilters(FilterRequest request) {
        // 委托给专门的筛选服务
        return filterService.getFilters(request);
    }

    @Override
    public List<ProductSimpleVO> getRelatedProducts(Long spuId, Integer size) {
        // 委托给相关商品服务
        return relatedProductService.getRelatedProducts(spuId, size);
    }
}
