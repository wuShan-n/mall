package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.*;
import co.elastic.clients.json.JsonData;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.*;
import com.mall.search.service.ProductSearchService;
import com.mall.search.strategy.SearchStrategy;
import com.mall.search.strategy.SearchStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品搜索服务实现
 * 使用原生 Elasticsearch Java Client 实现复杂的搜索功能
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ElasticsearchClient elasticsearchClient;
    private final SearchStrategyFactory searchStrategyFactory;
    
    private static final String PRODUCT_INDEX = "mall_product_v1";
    private static final float MIN_SCORE = 0.1f;

    @Override
    public ProductSearchResponse search(ProductSearchRequest request) {
        try {
            long startTime = System.currentTimeMillis();
            
            // 1. 参数预处理
            preprocessRequest(request);
            
            // 2. 获取搜索策略
            SearchStrategy strategy = searchStrategyFactory.getStrategy(request.getScene());
            
            // 3. 构建查询
            Query query = buildQuery(request, strategy);
            
            // 4. 构建搜索请求
            SearchRequest searchRequest = buildSearchRequest(query, request);
            
            // 5. 执行搜索
            SearchResponse<ProductSearchDocument> response = elasticsearchClient.search(
                searchRequest, 
                ProductSearchDocument.class
            );
            
            // 6. 处理搜索结果
            ProductSearchResponse searchResponse = processSearchResponse(response, request);
            
            // 7. 设置搜索耗时
            searchResponse = ProductSearchResponse.builder()
                .products(searchResponse.getProducts())
                .total(searchResponse.getTotal())
                .totalPages(searchResponse.getTotalPages())
                .pageNum(searchResponse.getPageNum())
                .pageSize(searchResponse.getPageSize())
                .took(System.currentTimeMillis() - startTime)
                .aggregations(searchResponse.getAggregations())
                .suggestion(searchResponse.getSuggestion())
                .build();
            
            log.info("Search completed. Keyword: {}, Total: {}, Took: {}ms", 
                request.getKeyword(), searchResponse.getTotal(), searchResponse.getTook());
            
            return searchResponse;
            
        } catch (Exception e) {
            log.error("Search error. Request: {}", request, e);
            throw new RuntimeException("搜索服务异常", e);
        }
    }

    /**
     * 参数预处理
     */
    private void preprocessRequest(ProductSearchRequest request) {
        // 设置默认值
        if (request.getPageNum() == null || request.getPageNum() < 1) {
            request.setPageNum(1);
        }
        if (request.getPageSize() == null || request.getPageSize() < 1) {
            request.setPageSize(20);
        }
        if (request.getPageSize() > 100) {
            request.setPageSize(100);
        }
        
        // 关键词处理
        if (StringUtils.hasText(request.getKeyword())) {
            request.setKeyword(request.getKeyword().trim());
        }
    }

    /**
     * 构建查询
     */
    private Query buildQuery(ProductSearchRequest request, SearchStrategy strategy) {
        BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
        
        // 1. 构建主查询（关键词搜索）
        Query mainQuery = buildMainQuery(request, strategy);
        if (mainQuery != null) {
            boolBuilder.must(mainQuery);
        }
        
        // 2. 构建过滤条件
        List<Query> filters = buildFilters(request);
        if (!filters.isEmpty()) {
            filters.forEach(boolBuilder::filter);
        }
        
        // 3. 应用策略特定的查询调整
        strategy.adjustQuery(boolBuilder, request);
        
        // 4. 包装为 Function Score Query 进行评分调整
        return buildFunctionScoreQuery(boolBuilder.build(), request, strategy);
    }

    /**
     * 构建主查询（多字段搜索）
     */
    private Query buildMainQuery(ProductSearchRequest request, SearchStrategy strategy) {
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
        fieldBoosts.forEach((field, boost) -> 
            multiMatchBuilder.fields(field + "^" + boost)
        );
        
        return Query.of(q -> q.multiMatch(multiMatchBuilder.build()));
    }

    /**
     * 构建过滤条件
     */
    private List<Query> buildFilters(ProductSearchRequest request) {
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
        
        return filters;
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
     * 构建 Function Score Query
     */
    private Query buildFunctionScoreQuery(BoolQuery boolQuery, 
                                         ProductSearchRequest request, 
                                         SearchStrategy strategy) {
        FunctionScoreQuery.Builder fsBuilder = new FunctionScoreQuery.Builder()
            .query(boolQuery._toQuery())
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
     * 构建搜索请求
     */
    private SearchRequest buildSearchRequest(Query query, ProductSearchRequest request) {
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder()
            .index(PRODUCT_INDEX)
            .query(query)
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
            Map<String, Aggregation> aggregations = buildAggregations(request);
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

    /**
     * 构建排序选项
     */
    private List<SortOptions> buildSortOptions(ProductSearchRequest request) {
        List<SortOptions> sorts = new ArrayList<>();
        
        if (request.getSortField() == null || 
            request.getSortField() == ProductSearchRequest.SortField.DEFAULT) {
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
    private Map<String, Aggregation> buildAggregations(ProductSearchRequest request) {
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

    /**
     * 处理搜索响应
     */
    private ProductSearchResponse processSearchResponse(SearchResponse<ProductSearchDocument> response,
                                                       ProductSearchRequest request) {
        // 处理搜索结果
        List<ProductSearchVO> products = processHits(response.hits());
        
        // 处理聚合结果
        SearchAggregations aggregations = null;
        if (Boolean.TRUE.equals(request.getNeedAggregation()) && response.aggregations() != null) {
            aggregations = processAggregations(response.aggregations(), request);
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
     * 处理聚合结果
     */
    private SearchAggregations processAggregations(Map<String, Aggregate> aggregates,
                                                  ProductSearchRequest request) {
        SearchAggregations.SearchAggregationsBuilder builder = SearchAggregations.builder();
        
        // 处理品牌聚合
        if (aggregates.containsKey("brands")) {
            List<SearchAggregations.BrandAggregation> brands = processBrandAggregation(
                aggregates.get("brands"), 
                request.getBrandIds()
            );
            builder.brands(brands);
        }
        
        // 处理分类聚合
        if (aggregates.containsKey("categories")) {
            List<SearchAggregations.CategoryAggregation> categories = processCategoryAggregation(
                aggregates.get("categories")
            );
            builder.categories(categories);
        }
        
        // 处理价格聚合
        if (aggregates.containsKey("priceStats")) {
            SearchAggregations.PriceRangeAggregation priceRange = processPriceAggregation(
                aggregates.get("priceStats"),
                aggregates.get("priceRanges")
            );
            builder.priceRange(priceRange);
        }
        
        // 处理标签聚合
        if (aggregates.containsKey("tags")) {
            List<SearchAggregations.TagAggregation> tags = processTagAggregation(
                aggregates.get("tags")
            );
            builder.tags(tags);
        }
        
        return builder.build();
    }

    /**
     * 处理品牌聚合
     */
    private List<SearchAggregations.BrandAggregation> processBrandAggregation(
            Aggregate aggregate, List<Long> selectedBrandIds) {
        if (!aggregate.isLterms()) {
            return Collections.emptyList();
        }
        
        Set<Long> selectedSet = selectedBrandIds != null 
            ? new HashSet<>(selectedBrandIds) 
            : Collections.emptySet();
        
        return aggregate.lterms().buckets().array().stream()
            .map(bucket -> SearchAggregations.BrandAggregation.builder()
                .brandId(bucket.key())
                .brandName(extractBrandName(bucket))
                .productCount(bucket.docCount())
                .selected(selectedSet.contains(bucket.key()))
                .build())
            .limit(20)
            .collect(Collectors.toList());
    }

    /**
     * 提取品牌名称
     */
    private String extractBrandName(LongTermsBucket bucket) {
        if (bucket.aggregations() != null && 
            bucket.aggregations().containsKey("brandName")) {
            Aggregate brandNameAgg = bucket.aggregations().get("brandName");
            if (brandNameAgg.isSterms() && !brandNameAgg.sterms().buckets().array().isEmpty()) {
                return brandNameAgg.sterms().buckets().array().get(0).key().stringValue();
            }
        }
        return "品牌" + bucket.key();
    }

    /**
     * 处理分类聚合
     */
    private List<SearchAggregations.CategoryAggregation> processCategoryAggregation(
            Aggregate aggregate) {
        if (!aggregate.isLterms()) {
            return Collections.emptyList();
        }
        
        return aggregate.lterms().buckets().array().stream()
            .map(bucket -> SearchAggregations.CategoryAggregation.builder()
                .categoryId(bucket.key())
                .categoryName(extractCategoryName(bucket))
                .productCount(bucket.docCount())
                .build())
            .limit(20)
            .collect(Collectors.toList());
    }

    /**
     * 提取分类名称
     */
    private String extractCategoryName(LongTermsBucket bucket) {
        if (bucket.aggregations() != null && 
            bucket.aggregations().containsKey("categoryName")) {
            Aggregate categoryNameAgg = bucket.aggregations().get("categoryName");
            if (categoryNameAgg.isSterms() && !categoryNameAgg.sterms().buckets().array().isEmpty()) {
                return categoryNameAgg.sterms().buckets().array().get(0).key().stringValue();
            }
        }
        return "分类" + bucket.key();
    }

    /**
     * 处理价格聚合
     */
    private SearchAggregations.PriceRangeAggregation processPriceAggregation(
            Aggregate statsAgg, Aggregate rangeAgg) {
        SearchAggregations.PriceRangeAggregation.PriceRangeAggregationBuilder builder = 
            SearchAggregations.PriceRangeAggregation.builder();
        
        // 处理统计信息
        if (statsAgg != null && statsAgg.isStats()) {
            builder.minPrice(BigDecimal.valueOf(statsAgg.stats().min()))
                   .maxPrice(BigDecimal.valueOf(statsAgg.stats().max()));
        }
        
        // 处理区间信息
        if (rangeAgg != null && rangeAgg.isRange()) {
            List<SearchAggregations.PriceRangeAggregation.PriceInterval> intervals = 
                rangeAgg.range().buckets().array().stream()
                    .map(this::convertToPriceInterval)
                    .collect(Collectors.toList());
            builder.intervals(intervals);
        }
        
        return builder.build();
    }

    /**
     * 转换价格区间
     */
    private SearchAggregations.PriceRangeAggregation.PriceInterval convertToPriceInterval(
            RangeBucket bucket) {
        BigDecimal from = bucket.from() != null ? BigDecimal.valueOf(bucket.from()) : null;
        BigDecimal to = bucket.to() != null ? BigDecimal.valueOf(bucket.to()) : null;
        
        String label = generatePriceLabel(from, to);
        
        return SearchAggregations.PriceRangeAggregation.PriceInterval.builder()
            .from(from)
            .to(to)
            .label(label)
            .count(bucket.docCount())
            .build();
    }

    /**
     * 生成价格区间标签
     */
    private String generatePriceLabel(BigDecimal from, BigDecimal to) {
        if (from == null && to != null) {
            return "￥" + to + "以下";
        } else if (from != null && to == null) {
            return "￥" + from + "以上";
        } else if (from != null && to != null) {
            return "￥" + from + "-￥" + to;
        }
        return "全部价格";
    }

    /**
     * 处理标签聚合
     */
    private List<SearchAggregations.TagAggregation> processTagAggregation(Aggregate aggregate) {
        if (!aggregate.isSterms()) {
            return Collections.emptyList();
        }
        
        return aggregate.sterms().buckets().array().stream()
            .map(bucket -> SearchAggregations.TagAggregation.builder()
                .tagName(bucket.key().stringValue())
                .displayName(bucket.key().stringValue())
                .count(bucket.docCount())
                .tagType(determineTagType(bucket.key().stringValue()))
                .build())
            .limit(10)
            .collect(Collectors.toList());
    }

    /**
     * 判断标签类型
     */
    private String determineTagType(String tag) {
        if (tag.contains("新") || tag.contains("NEW")) {
            return "NEW";
        } else if (tag.contains("热") || tag.contains("HOT")) {
            return "HOT";
        } else if (tag.contains("促销") || tag.contains("特价") || tag.contains("折扣")) {
            return "PROMOTION";
        }
        return "NORMAL";
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
            return processFiltersFromAggregations(response.aggregations(), request);
            
        } catch (Exception e) {
            log.error("Get filters error. Request: {}", request, e);
            throw new RuntimeException("获取筛选项失败", e);
        }
    }

    /**
     * 从聚合结果构建筛选项
     */
    private SearchFilters processFiltersFromAggregations(Map<String, Aggregate> aggregates,
                                                        FilterRequest request) {
        SearchFilters.SearchFiltersBuilder builder = SearchFilters.builder();
        
        // 处理品牌筛选项
        if (aggregates.containsKey("brands")) {
            List<SearchFilters.FilterOption> brands = processBrandFilters(
                aggregates.get("brands"),
                request.getSelectedBrandIds()
            );
            builder.brands(brands);
        }
        
        // 处理分类筛选项
        if (aggregates.containsKey("categories")) {
            List<SearchFilters.FilterOption> categories = processCategoryFilters(
                aggregates.get("categories")
            );
            builder.categories(categories);
        }
        
        // 处理价格区间筛选项
        if (aggregates.containsKey("priceRanges")) {
            List<SearchFilters.PriceFilter> priceRanges = processPriceFilters(
                aggregates.get("priceRanges")
            );
            builder.priceRanges(priceRanges);
        }
        
        // 处理标签筛选项
        if (aggregates.containsKey("tags")) {
            List<SearchFilters.FilterOption> tags = processTagFilters(
                aggregates.get("tags")
            );
            builder.tags(tags);
        }
        
        return builder.build();
    }

    /**
     * 处理品牌筛选项
     */
    private List<SearchFilters.FilterOption> processBrandFilters(Aggregate aggregate,
                                                                List<Long> selectedIds) {
        if (!aggregate.isLterms()) {
            return Collections.emptyList();
        }
        
        Set<Long> selectedSet = selectedIds != null 
            ? new HashSet<>(selectedIds) 
            : Collections.emptySet();
        
        return aggregate.lterms().buckets().array().stream()
            .map(bucket -> SearchFilters.FilterOption.builder()
                .id(bucket.key())
                .name(extractBrandName(bucket))
                .count(bucket.docCount())
                .selected(selectedSet.contains(bucket.key()))
                .build())
            .collect(Collectors.toList());
    }

    /**
     * 处理分类筛选项
     */
    private List<SearchFilters.FilterOption> processCategoryFilters(Aggregate aggregate) {
        if (!aggregate.isLterms()) {
            return Collections.emptyList();
        }
        
        return aggregate.lterms().buckets().array().stream()
            .map(bucket -> SearchFilters.FilterOption.builder()
                .id(bucket.key())
                .name(extractCategoryName(bucket))
                .count(bucket.docCount())
                .selected(false)
                .build())
            .collect(Collectors.toList());
    }

    /**
     * 处理价格筛选项
     */
    private List<SearchFilters.PriceFilter> processPriceFilters(Aggregate aggregate) {
        if (!aggregate.isRange()) {
            return Collections.emptyList();
        }
        
        return aggregate.range().buckets().array().stream()
            .map(bucket -> {
                BigDecimal minPrice = bucket.from() != null ? 
                    BigDecimal.valueOf(bucket.from()) : null;
                BigDecimal maxPrice = bucket.to() != null ? 
                    BigDecimal.valueOf(bucket.to()) : null;
                
                return SearchFilters.PriceFilter.builder()
                    .minPrice(minPrice)
                    .maxPrice(maxPrice)
                    .label(bucket.key())
                    .count(bucket.docCount())
                    .selected(false)
                    .build();
            })
            .collect(Collectors.toList());
    }

    /**
     * 处理标签筛选项
     */
    private List<SearchFilters.FilterOption> processTagFilters(Aggregate aggregate) {
        if (!aggregate.isSterms()) {
            return Collections.emptyList();
        }
        
        return aggregate.sterms().buckets().array().stream()
            .map(bucket -> SearchFilters.FilterOption.builder()
                .name(bucket.key().stringValue())
                .count(bucket.docCount())
                .selected(false)
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductSimpleVO> getRelatedProducts(Long spuId, Integer size) {
        try {
            // 1. 先获取基准商品信息
            GetRequest getRequest = GetRequest.of(g -> g
                .index(PRODUCT_INDEX)
                .id(spuId.toString())
            );
            
            GetResponse<ProductSearchDocument> getResponse = elasticsearchClient.get(
                getRequest,
                ProductSearchDocument.class
            );
            
            if (!getResponse.found() || getResponse.source() == null) {
                log.warn("Product not found. SPU ID: {}", spuId);
                return Collections.emptyList();
            }
            
            ProductSearchDocument baseProduct = getResponse.source();
            
            // 2. 构建相似查询
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();
            
            // 排除自己
            boolBuilder.mustNot(Query.of(q -> q
                .term(t -> t
                    .field("spuId")
                    .value(spuId)
                )
            ));
            
            // 只查询上架商品
            boolBuilder.filter(Query.of(q -> q
                .term(t -> t
                    .field("status")
                    .value(1)
                )
            ));
            
            // 相似条件构建
            List<Query> shouldQueries = new ArrayList<>();
            
            // 同分类（权重高）
            if (baseProduct.getCategoryId() != null) {
                shouldQueries.add(Query.of(q -> q
                    .term(t -> t
                        .field("categoryId")
                        .value(baseProduct.getCategoryId())
                    )
                ));
            }
            
            // 同品牌
            if (baseProduct.getBrandId() != null) {
                shouldQueries.add(Query.of(q -> q
                    .term(t -> t
                        .field("brandId")
                        .value(baseProduct.getBrandId())
                    )
                ));
            }
            
            // 相似价格区间（±30%）
            if (baseProduct.getMinPrice() != null) {
                BigDecimal minPrice = baseProduct.getMinPrice()
                    .multiply(BigDecimal.valueOf(0.7));
                BigDecimal maxPrice = baseProduct.getMinPrice()
                    .multiply(BigDecimal.valueOf(1.3));
                
                shouldQueries.add(Query.of(q -> q
                    .range(r -> r
                        .field("minPrice")
                        .gte(JsonData.of(minPrice))
                        .lte(JsonData.of(maxPrice))
                    )
                ));
            }
            
            // 相似标签
            if (baseProduct.getTags() != null && !baseProduct.getTags().isEmpty()) {
                shouldQueries.add(Query.of(q -> q
                    .terms(t -> t
                        .field("tags")
                        .terms(TermsQueryField.of(tf -> tf
                            .value(baseProduct.getTags().stream()
                                .map(FieldValue::of)
                                .collect(Collectors.toList()))
                        ))
                    )
                ));
            }
            
            // 关键词匹配
            if (StringUtils.hasText(baseProduct.getKeywords())) {
                shouldQueries.add(Query.of(q -> q
                    .match(m -> m
                        .field("keywords")
                        .query(baseProduct.getKeywords())
                    )
                ));
            }
            
            boolBuilder.should(shouldQueries);
            boolBuilder.minimumShouldMatch("2"); // 至少满足2个条件
            
            // 3. 执行搜索
            SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(PRODUCT_INDEX)
                .query(boolBuilder.build()._toQuery())
                .size(size)
                .sort(so -> so
                    .field(f -> f
                        .field("salesCount")
                        .order(SortOrder.Desc)
                    )
                )
            );
            
            SearchResponse<ProductSearchDocument> response = elasticsearchClient.search(
                searchRequest,
                ProductSearchDocument.class
            );
            
            // 4. 转换结果
            return response.hits().hits().stream()
                .map(hit -> convertToProductSimpleVO(hit.source()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("Get related products error. SPU ID: {}", spuId, e);
            return Collections.emptyList();
        }
    }

    /**
     * 转换为商品简要信息
     */
    private ProductSimpleVO convertToProductSimpleVO(ProductSearchDocument doc) {
        if (doc == null) {
            return null;
        }
        
        return ProductSimpleVO.builder()
            .spuId(doc.getSpuId())
            .productName(doc.getProductName())
            .productCode(doc.getProductCode())
            .mainImage(doc.getMainImage())
            .minPrice(doc.getMinPrice())
            .maxPrice(doc.getMaxPrice())
            .salesCount(doc.getSalesCount())
            .commentCount(doc.getCommentCount())
            .score(doc.getAverageScore())
            .brandName(doc.getBrandName())
            .categoryName(doc.getCategoryName())
            .tags(doc.getTags())
            .hasStock(doc.getHasStock())
            .flags(ProductSimpleVO.ProductFlags.builder()
                .isNew(doc.getIsNew())
                .isHot(doc.getIsHot())
                .isBest(doc.getIsBest())
                .build())
            .build();
    }
}