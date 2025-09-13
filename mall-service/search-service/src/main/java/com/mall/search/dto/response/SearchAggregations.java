package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

/**
 * =====================================================
 * 搜索聚合数据
 * =====================================================
 */
@Value
@Builder
@Schema(description = "搜索聚合数据")
public class SearchAggregations {

    @Schema(description = "品牌聚合")
    List<BrandAggregation> brands;

    @Schema(description = "分类聚合")
    List<CategoryAggregation> categories;

    @Schema(description = "属性聚合")
    List<AttributeAggregation> attributes;

    @Schema(description = "价格区间")
    PriceRangeAggregation priceRange;

    @Schema(description = "标签聚合")
    List<TagAggregation> tags;

    @Schema(description = "统计信息")
    SearchStatistics statistics;

    /**
     * 品牌聚合项
     */
    @Data
    @Builder
    @Schema(description = "品牌聚合项")
    public static class BrandAggregation {
        @Schema(description = "品牌ID")
        Long brandId;

        @Schema(description = "品牌名称")
        String brandName;

        @Schema(description = "品牌logo")
        String brandLogo;

        @Schema(description = "商品数量")
        Long productCount;

        @Schema(description = "是否选中")
        Boolean selected;
    }

    /**
     * 分类聚合项
     */
    @Data
    @Builder
    @Schema(description = "分类聚合项")
    public static class CategoryAggregation {
        @Schema(description = "分类ID")
        Long categoryId;

        @Schema(description = "分类名称")
        String categoryName;

        @Schema(description = "分类层级")
        Integer level;

        @Schema(description = "父分类ID")
        Long parentId;

        @Schema(description = "商品数量")
        Long productCount;

        @Schema(description = "子分类")
        List<CategoryAggregation> children;

        @Schema(description = "是否选中")
        Boolean selected;
    }

    /**
     * 属性聚合项
     */
    @Data
    @Builder
    @Schema(description = "属性聚合项")
    public static class AttributeAggregation {
        @Schema(description = "属性名")
        String attrName;

        @Schema(description = "属性显示名")
        String attrDisplayName;

        @Schema(description = "属性值列表")
        List<AttributeValue> values;

        @Schema(description = "属性类型")
        String attrType; // COLOR, SIZE, SPEC

        @Schema(description = "是否多选")
        Boolean multiSelect;

        @Data
        @Builder
        public static class AttributeValue {
            @Schema(description = "属性值")
            String value;

            @Schema(description = "显示值")
            String displayValue;

            @Schema(description = "商品数量")
            Long count;

            @Schema(description = "是否选中")
            Boolean selected;

            @Schema(description = "扩展信息(如颜色值)")
            String extra;
        }
    }

    /**
     * 价格区间聚合
     */
    @Data
    @Builder
    @Schema(description = "价格区间聚合")
    public static class PriceRangeAggregation {
        @Schema(description = "最低价")
        BigDecimal minPrice;

        @Schema(description = "最高价")
        BigDecimal maxPrice;

        @Schema(description = "价格区间分布")
        List<PriceInterval> intervals;

        @Data
        @Builder
        public static class PriceInterval {
            @Schema(description = "区间起始价格")
            BigDecimal from;

            @Schema(description = "区间结束价格")
            BigDecimal to;

            @Schema(description = "区间描述")
            String label;

            @Schema(description = "商品数量")
            Long count;

            @Schema(description = "是否选中")
            Boolean selected;
        }
    }

    /**
     * 标签聚合项
     */
    @Data
    @Builder
    @Schema(description = "标签聚合项")
    public static class TagAggregation {
        @Schema(description = "标签名")
        String tagName;

        @Schema(description = "标签显示名")
        String displayName;

        @Schema(description = "商品数量")
        Long count;

        @Schema(description = "标签类型")
        String tagType; // HOT, NEW, PROMOTION

        @Schema(description = "标签颜色")
        String color;

        @Schema(description = "是否选中")
        Boolean selected;
    }

    /**
     * 搜索统计信息
     */
    @Data
    @Builder
    @Schema(description = "搜索统计信息")
    public static class SearchStatistics {
        @Schema(description = "结果总数")
        Long totalCount;

        @Schema(description = "品牌数量")
        Integer brandCount;

        @Schema(description = "分类数量")
        Integer categoryCount;

        @Schema(description = "价格区间")
        String priceRange;

        @Schema(description = "平均价格")
        BigDecimal avgPrice;
    }
}