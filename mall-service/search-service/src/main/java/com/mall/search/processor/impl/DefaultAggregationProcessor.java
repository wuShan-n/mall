package com.mall.search.processor.impl;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.LongTermsBucket;
import co.elastic.clients.elasticsearch._types.aggregations.RangeBucket;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.SearchAggregations;
import com.mall.search.dto.response.SearchFilters;
import com.mall.search.processor.AggregationProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 默认聚合处理器实现
 */
@Slf4j
@Component
public class DefaultAggregationProcessor implements AggregationProcessor {

    @Override
    public SearchAggregations processSearchAggregations(
            Map<String, Aggregate> aggregates,
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

    @Override
    public SearchFilters processFilterAggregations(
            Map<String, Aggregate> aggregates,
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
        if (bucket.aggregations() != null
                && bucket.aggregations().containsKey("brandName")) {
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
        if (bucket.aggregations() != null
                && bucket.aggregations().containsKey("categoryName")) {
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
        SearchAggregations.PriceRangeAggregation.PriceRangeAggregationBuilder builder
                = SearchAggregations.PriceRangeAggregation.builder();

        // 处理统计信息
        if (statsAgg != null && statsAgg.isStats()) {
            builder.minPrice(BigDecimal.valueOf(statsAgg.stats().min()))
                    .maxPrice(BigDecimal.valueOf(statsAgg.stats().max()));
        }

        // 处理区间信息
        if (rangeAgg != null && rangeAgg.isRange()) {
            List<SearchAggregations.PriceRangeAggregation.PriceInterval> intervals
                    = rangeAgg.range().buckets().array().stream()
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
        } else if (from != null) {
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

    // === 筛选项处理方法 ===
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
                    BigDecimal minPrice = bucket.from() != null
                            ? BigDecimal.valueOf(bucket.from()) : null;
                    BigDecimal maxPrice = bucket.to() != null
                            ? BigDecimal.valueOf(bucket.to()) : null;

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
}
