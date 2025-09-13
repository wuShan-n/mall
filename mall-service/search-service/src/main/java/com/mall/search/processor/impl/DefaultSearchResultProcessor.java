package com.mall.search.processor.impl;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.*;
import com.mall.search.processor.AggregationProcessor;
import com.mall.search.processor.SearchResultProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 默认搜索结果处理器实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultSearchResultProcessor implements SearchResultProcessor {

    private final AggregationProcessor aggregationProcessor;

    @Override
    public ProductSearchResponse processSearchResponse(
            SearchResponse<ProductSearchDocument> response,
            ProductSearchRequest request,
            long searchTime) {

        // 处理搜索结果
        List<ProductSearchVO> products = processHits(response.hits());

        // 处理聚合结果
        SearchAggregations aggregations = null;
        if (Boolean.TRUE.equals(request.getNeedAggregation()) && response.aggregations() != null) {
            aggregations = aggregationProcessor.processSearchAggregations(response.aggregations(), request);
        }

        // 构建搜索建议
        SearchSuggestion suggestion = null;
        if (products.isEmpty() && StringUtils.hasText(request.getKeyword())) {
            suggestion = buildSearchSuggestion(request.getKeyword());
        }

        // 计算分页信息
        long total = response.hits().total() != null ? response.hits().total().value() : 0;
        int totalPages = (int) Math.ceil((double) total / request.getPageSize());

        return ProductSearchResponse.builder()
                .products(products)
                .total(total)
                .totalPages(totalPages)
                .pageNum(request.getPageNum())
                .pageSize(request.getPageSize())
                .took(searchTime)
                .aggregations(aggregations)
                .suggestion(suggestion)
                .build();
    }

    /**
     * 处理命中文档
     */
    private List<ProductSearchVO> processHits(HitsMetadata<ProductSearchDocument> hits) {
        if (hits.hits() == null || hits.hits().isEmpty()) {
            return Collections.emptyList();
        }

        return hits.hits().stream()
                .map(this::convertToProductVO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 转换文档为VO
     */
    private ProductSearchVO convertToProductVO(Hit<ProductSearchDocument> hit) {
        ProductSearchDocument doc = hit.source();
        if (doc == null) {
            return null;
        }

        // 处理高亮
        String highlightName = doc.getProductName();
        if (hit.highlight() != null && hit.highlight().containsKey("productName")) {
            highlightName = hit.highlight().get("productName").get(0);
        }

        // 转换SKU信息
        List<SkuSimpleVO> skus = Optional.ofNullable(doc.getSkus())
                .orElse(Collections.emptyList())
                .stream()
                .limit(3) // 只返回前3个SKU
                .map(this::convertToSkuVO)
                .collect(Collectors.toList());

        return ProductSearchVO.builder()
                .spuId(doc.getSpuId())
                .productName(doc.getProductName())
                .productCode(doc.getProductCode())
                .mainImage(doc.getMainImage())
                .minPrice(doc.getMinPrice())
                .maxPrice(doc.getMaxPrice())
                .totalStock(doc.getTotalStock())
                .salesCount(doc.getSalesCount())
                .commentCount(doc.getCommentCount())
                .averageScore(doc.getAverageScore())
                .tags(doc.getTags())
                .brandName(doc.getBrandName())
                .categoryName(doc.getCategoryName())
                .isNew(doc.getIsNew())
                .isHot(doc.getIsHot())
                .highlightScore(hit.score() != null ? hit.score().floatValue() : 0f)
                .highlightName(highlightName)
                .skus(skus)
                .build();
    }

    /**
     * 转换SKU信息
     */
    private SkuSimpleVO convertToSkuVO(ProductSearchDocument.SkuInfo sku) {
        return SkuSimpleVO.builder()
                .skuId(sku.getSkuId())
                .skuCode(sku.getSkuCode())
                .price(sku.getPrice())
                .stock(sku.getStock())
                .specs(sku.getSpecs())
                .image(sku.getImage())
                .available(sku.getStock() != null && sku.getStock() > 0)
                .build();
    }

    /**
     * 构建搜索建议
     */
    private SearchSuggestion buildSearchSuggestion(String keyword) {
        // 这里可以调用搜索建议服务获取更多建议
        return SearchSuggestion.builder()
                .hasResults(false)
                .originalKeyword(keyword)
                .relatedKeywords(Arrays.asList(
                        keyword + "正品",
                        keyword + "特价",
                        keyword + "新款"
                ))
                .build();
    }
}
