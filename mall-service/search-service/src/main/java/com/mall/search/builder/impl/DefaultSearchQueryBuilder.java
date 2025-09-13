package com.mall.search.builder.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.json.JsonData;
import com.mall.search.builder.SearchQueryBuilder;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 默认搜索查询构建器实现
 */
@Slf4j
@Component
public class DefaultSearchQueryBuilder implements SearchQueryBuilder {

    private static final String PRODUCT_INDEX = "mall_product_v1";
    private static final float MIN_SCORE = 0.1f;

    @Override
    public SearchRequest buildSearchRequest(ProductSearchRequest request, SearchStrategy strategy) {
        // 构建主查询
        Query mainQuery = buildMainQuery(request, strategy);
        Query filterQuery = buildFilterQuery(request);

        // 组合查询
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        if (mainQuery != null) {
            boolBuilder.must(mainQuery);
        }
        if (filterQuery != null) {
            boolBuilder.filter(filterQuery);
        }

        // 应用策略特定的查询调整
        strategy.adjustQuery(boolBuilder, request);

        // 构建Function Score查询
        Query finalQuery = buildFunctionScoreQuery(boolBuilder.build()._toQuery(), request, strategy);

        // 构建搜索请求
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder()
                .index(PRODUCT_INDEX)
                .query(finalQuery)
                .minScore((double) MIN_SCORE)
                .trackTotalHits(TrackHits.of(th -> th.enabled(true)));

        // 分页
        int from = (request.getPageNum() - 1) * request.getPageSize();
        searchBuilder.from(from).size(request.getPageSize());

        // 排序
        List<SortOptions> sorts = buildSortOptions(request);
        if (!sorts.isEmpty()) {
            searchBuilder.sort(sorts);
        }

        // 高亮
        if (StringUtils.hasText(request.getKeyword())) {
            searchBuilder.highlight(buildHighlight());
        }

        // 聚合
        if (Boolean.TRUE.equals(request.getNeedAggregation())) {
            Map<String, Aggregation> aggregations = buildAggregations();
            searchBuilder.aggregations(aggregations);
        }

        // Source 过滤（优化性能）
        searchBuilder.source(SourceConfig.of(sc -> sc
                .filter(f -> f
                .excludes("skus.specs", "keywords")
                )
        ));

        return searchBuilder.build();
    }

    @Override
    public Query buildMainQuery(ProductSearchRequest request, SearchStrategy strategy) {
        if (!StringUtils.hasText(request.getKeyword())) {
            return null;
        }

        String keyword = request.getKeyword();

        // 使用 Multi-Match Query 进行多字段搜索
        MultiMatchQuery.Builder multiMatchBuilder = new MultiMatchQuery.Builder()
                .query(keyword)
                .type(TextQueryType.BestFields)
                .minimumShouldMatch("70%")
                .tieBreaker(0.3);

        // 根据策略设置搜索字段和权重
        Map<String, Float> fieldBoosts = strategy.getFieldBoosts();
        fieldBoosts.forEach((field, boost)
                -> multiMatchBuilder.fields(field + "^" + boost)
        );

        return Query.of(q -> q.multiMatch(multiMatchBuilder.build()));
    }

    @Override
    public Query buildFilterQuery(ProductSearchRequest request) {
        List<Query> filters = new ArrayList<>();

        // 分类过滤
        if (request.getCategoryId() != null) {
            filters.add(Query.of(q -> q
                    .term(t -> t
                    .field("categoryId")
                    .value(request.getCategoryId())
                    )
            ));
        } else if (!CollectionUtils.isEmpty(request.getCategoryPath())) {
            filters.add(Query.of(q -> q
                    .terms(t -> t
                    .field("categoryPath")
                    .terms(TermsQueryField.of(tf -> tf
                    .value(request.getCategoryPath().stream()
                            .map(FieldValue::of)
                            .collect(Collectors.toList()))
                    ))
                    )
            ));
        }

        // 品牌过滤
        if (!CollectionUtils.isEmpty(request.getBrandIds())) {
            filters.add(Query.of(q -> q
                    .terms(t -> t
                    .field("brandId")
                    .terms(TermsQueryField.of(tf -> tf
                    .value(request.getBrandIds().stream()
                            .map(FieldValue::of)
                            .collect(Collectors.toList()))
                    ))
                    )
            ));
        }

        // 价格区间过滤
        if (request.getPriceRange() != null) {
            RangeQuery.Builder rangeBuilder = new RangeQuery.Builder()
                    .field("minPrice");

            if (request.getPriceRange().getMinPrice() != null) {
                rangeBuilder.gte(JsonData.of(request.getPriceRange().getMinPrice()));
            }
            if (request.getPriceRange().getMaxPrice() != null) {
                rangeBuilder.lte(JsonData.of(request.getPriceRange().getMaxPrice()));
            }

            filters.add(Query.of(q -> q.range(rangeBuilder.build())));
        }

        // 库存过滤
        if (Boolean.TRUE.equals(request.getHasStock())) {
            filters.add(Query.of(q -> q
                    .term(t -> t
                    .field("hasStock")
                    .value(true)
                    )
            ));
        }

        // 标签过滤
        if (!CollectionUtils.isEmpty(request.getTags())) {
            filters.add(Query.of(q -> q
                    .terms(t -> t
                    .field("tags")
                    .terms(TermsQueryField.of(tf -> tf
                    .value(request.getTags().stream()
                            .map(FieldValue::of)
                            .collect(Collectors.toList()))
                    ))
                    )
            ));
        }

        // 状态过滤
        filters.add(Query.of(q -> q
                .term(t -> t
                .field("status")
                .value(1) // 只查询上架商品
                )
        ));

        // 新品、热销、精品过滤
        if (Boolean.TRUE.equals(request.getIsNew())) {
            filters.add(Query.of(q -> q.term(t -> t.field("isNew").value(true))));
        }
        if (Boolean.TRUE.equals(request.getIsHot())) {
            filters.add(Query.of(q -> q.term(t -> t.field("isHot").value(true))));
        }
        if (Boolean.TRUE.equals(request.getIsBest())) {
            filters.add(Query.of(q -> q.term(t -> t.field("isBest").value(true))));
        }

        // SKU 属性过滤（嵌套查询）
        if (!CollectionUtils.isEmpty(request.getAttributes())) {
            for (ProductSearchRequest.AttributeFilter attr : request.getAttributes()) {
                filters.add(buildNestedAttributeQuery(attr));
            }
        }

        // 组合所有过滤条件
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        filters.forEach(boolBuilder::filter);
        return boolBuilder.build()._toQuery();
    }

    @Override
    public Query buildFunctionScoreQuery(Query baseQuery, ProductSearchRequest request, SearchStrategy strategy) {
        FunctionScoreQuery.Builder fsBuilder = new FunctionScoreQuery.Builder()
                .query(baseQuery)
                .boostMode(FunctionBoostMode.Multiply)
                .scoreMode(FunctionScoreMode.Sum)
                .minScore((double) MIN_SCORE);

        List<FunctionScore> functions = new ArrayList<>();

        // 1. 销量评分函数
        functions.add(FunctionScore.of(fs -> fs
                .fieldValueFactor(fvf -> fvf
                .field("salesCount")
                .factor(strategy.getSalesBoostFactor())
                .modifier(FieldValueFactorModifier.Log1p)
                .missing(1.0)
                )
                .weight(strategy.getSalesWeight())
        ));

        // 2. 评分评分函数
        functions.add(FunctionScore.of(fs -> fs
                .fieldValueFactor(fvf -> fvf
                .field("averageScore")
                .factor(strategy.getScoreBoostFactor())
                .modifier(FieldValueFactorModifier.Log1p)
                .missing(3.0)
                )
                .weight(strategy.getScoreWeight())
        ));

        // 3. 库存加分
        functions.add(FunctionScore.of(fs -> fs
                .filter(Query.of(q -> q.term(t -> t.field("hasStock").value(true))))
                .weight(strategy.getStockBoostWeight())
        ));

        // 4. 新品加分
        if (request.getScene() != ProductSearchRequest.SearchScene.PROMOTION) {
            functions.add(FunctionScore.of(fs -> fs
                    .filter(Query.of(q -> q.term(t -> t.field("isNew").value(true))))
                    .weight(strategy.getNewProductWeight())
            ));
        }

        // 5. 人气评分
        functions.add(FunctionScore.of(fs -> fs
                .fieldValueFactor(fvf -> fvf
                .field("popularityScore")
                .factor(1.0)
                .modifier(FieldValueFactorModifier.Log1p)
                .missing(0.0)
                )
                .weight(strategy.getPopularityWeight())
        ));

        // 6. 随机性（用于打散结果）
        if (strategy.needRandomScore()) {
            functions.add(FunctionScore.of(fs -> fs
                    .randomScore(rs -> rs
                    .seed(String.valueOf(System.currentTimeMillis() / 3600000))
                    .field("_seq_no")
                    )
                    .weight(0.1)
            ));
        }

        fsBuilder.functions(functions);

        return Query.of(q -> q.functionScore(fsBuilder.build()));
    }

    /**
     * 构建嵌套属性查询
     */
    private Query buildNestedAttributeQuery(ProductSearchRequest.AttributeFilter attr) {
        BoolQuery.Builder nestedBool = new BoolQuery.Builder();

        nestedBool.must(Query.of(q -> q
                .term(t -> t
                .field("skus.specs." + attr.getAttrName() + ".keyword")
                .value(attr.getAttrValues().get(0))
                )
        ));

        return Query.of(q -> q
                .nested(n -> n
                .path("skus")
                .query(nestedBool.build()._toQuery())
                )
        );
    }

    /**
     * 构建排序选项
     */
    private List<SortOptions> buildSortOptions(ProductSearchRequest request) {
        List<SortOptions> sorts = new ArrayList<>();

        if (request.getSortField() == null
                || request.getSortField() == ProductSearchRequest.SortField.DEFAULT) {
            // 默认按相关性排序
            return sorts;
        }

        SortOrder sortOrder = request.getSortDirection() == ProductSearchRequest.SortDirection.ASC
                ? SortOrder.Asc : SortOrder.Desc;

        sorts.add(SortOptions.of(so -> so
                .field(f -> f
                .field(request.getSortField().getField())
                .order(sortOrder)
                .missing("_last")
                )
        ));

        // 添加次要排序（保证结果稳定性）
        sorts.add(SortOptions.of(so -> so
                .field(f -> f
                .field("spuId")
                .order(SortOrder.Desc)
                )
        ));

        return sorts;
    }

    /**
     * 构建高亮配置
     */
    private Highlight buildHighlight() {
        return Highlight.of(h -> h
                .fields("productName", HighlightField.of(hf -> hf
                .preTags("<em class=\"highlight\">")
                .postTags("</em>")
                .numberOfFragments(0)
                ))
                .fields("productName.pinyin", HighlightField.of(hf -> hf
                .preTags("<em class=\"highlight\">")
                .postTags("</em>")
                .numberOfFragments(0)
                ))
        );
    }

    /**
     * 构建聚合查询
     */
    private Map<String, Aggregation> buildAggregations() {
        Map<String, Aggregation> aggregations = new HashMap<>();

        // 品牌聚合
        aggregations.put("brands", Aggregation.of(a -> a
                .terms(t -> t
                .field("brandId")
                .size(50)
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
                .size(50)
                )
                .aggregations("categoryName", Aggregation.of(sa -> sa
                .terms(st -> st
                .field("categoryName")
                .size(1)
                )
                ))
        ));

        // 价格统计
        aggregations.put("priceStats", Aggregation.of(a -> a
                .stats(s -> s
                .field("minPrice")
                )
        ));

        // 价格区间
        aggregations.put("priceRanges", Aggregation.of(a -> a
                .range(r -> r
                .field("minPrice")
                .ranges(
                        AggregationRange.of(ar -> ar.to("100")),
                        AggregationRange.of(ar -> ar.from("100").to("500")),
                        AggregationRange.of(ar -> ar.from("500").to("1000")),
                        AggregationRange.of(ar -> ar.from("1000").to("5000")),
                        AggregationRange.of(ar -> ar.from("5000"))
                )
                )
        ));

        // 标签聚合
        aggregations.put("tags", Aggregation.of(a -> a
                .terms(t -> t
                .field("tags")
                .size(20)
                )
        ));

        return aggregations;
    }
}
