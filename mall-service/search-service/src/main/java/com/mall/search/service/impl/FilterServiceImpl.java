package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationRange;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.response.SearchFilters;
import com.mall.search.processor.AggregationProcessor;
import com.mall.search.service.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 筛选服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final ElasticsearchClient elasticsearchClient;
    private final AggregationProcessor aggregationProcessor;

    private static final String PRODUCT_INDEX = "mall_product_v1";

    @Override
    public SearchFilters getFilters(FilterRequest request) {
        try {
            // 构建过滤查询
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

            // 添加关键词查询
            if (StringUtils.hasText(request.getKeyword())) {
                boolBuilder.must(Query.of(q -> q
                        .multiMatch(m -> m
                        .query(request.getKeyword())
                        .fields("productName", "keywords", "tags")
                        )
                ));
            }

            // 添加分类过滤
            if (request.getCategoryId() != null) {
                if (Boolean.TRUE.equals(request.getIncludeSubCategories())) {
                    boolBuilder.filter(Query.of(q -> q
                            .term(t -> t
                            .field("categoryPath")
                            .value(request.getCategoryId())
                            )
                    ));
                } else {
                    boolBuilder.filter(Query.of(q -> q
                            .term(t -> t
                            .field("categoryId")
                            .value(request.getCategoryId())
                            )
                    ));
                }
            }

            // 构建聚合查询
            Map<String, Aggregation> aggregations = buildFilterAggregations();

            // 执行搜索
            SearchRequest searchRequest = SearchRequest.of(s -> s
                    .index(PRODUCT_INDEX)
                    .query(boolBuilder.build()._toQuery())
                    .size(0) // 只要聚合结果
                    .aggregations(aggregations)
            );

            SearchResponse<ProductSearchDocument> response = elasticsearchClient.search(
                    searchRequest,
                    ProductSearchDocument.class
            );

            // 处理聚合结果
            return aggregationProcessor.processFilterAggregations(response.aggregations(), request);

        } catch (Exception e) {
            log.error("Get filters error. Request: {}", request, e);
            throw new RuntimeException("获取筛选项失败", e);
        }
    }

    /**
     * 构建筛选聚合查询
     */
    private Map<String, Aggregation> buildFilterAggregations() {
        Map<String, Aggregation> aggregations = new HashMap<>();

        // 品牌聚合
        aggregations.put("brands", Aggregation.of(a -> a
                .terms(t -> t
                .field("brandId")
                .size(100)
                )
                .aggregations("brandName", Aggregation.of(sa -> sa
                .terms(st -> st
                .field("brandName")
                .size(1)
                )
                ))
        ));

        // 分类聚合
        aggregations.put("categories", Aggregation.of(a -> a
                .terms(t -> t
                .field("categoryId")
                .size(100)
                )
                .aggregations("categoryName", Aggregation.of(sa -> sa
                .terms(st -> st
                .field("categoryName")
                .size(1)
                )
                ))
        ));

        // 标签聚合
        aggregations.put("tags", Aggregation.of(a -> a
                .terms(t -> t
                .field("tags")
                .size(50)
                )
        ));

        // 价格区间聚合
        aggregations.put("priceRanges", Aggregation.of(a -> a
                .range(r -> r
                .field("minPrice")
                .ranges(
                        AggregationRange.of(ar -> ar.key("0-100").to("100")),
                        AggregationRange.of(ar -> ar.key("100-500").from("100").to("500")),
                        AggregationRange.of(ar -> ar.key("500-1000").from("500").to("1000")),
                        AggregationRange.of(ar -> ar.key("1000-5000").from("1000").to("5000")),
                        AggregationRange.of(ar -> ar.key("5000+").from("5000"))
                )
                )
        ));

        return aggregations;
    }
}
