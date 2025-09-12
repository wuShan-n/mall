package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * =====================================================
 * 搜索聚合数据
 * =====================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "搜索聚合数据")
public class SearchAggregations {
    
    @Schema(description = "品牌聚合")
    private List<BrandAggregation> brands;
    
    @Schema(description = "分类聚合")
    private List<CategoryAggregation> categories;
    
    @Schema(description = "属性聚合")
    private List<AttributeAggregation> attributes;
    
    @Schema(description = "价格区间")
    private PriceRangeAggregation priceRange;
    
    @Schema(description = "标签聚合")
    private List<TagAggregation> tags;
    
    @Schema(description = "统计信息")
    private SearchStatistics statistics;
    
    /**
     * 品牌聚合项
     */
    @Data
    @Builder
    @Schema(description = "品牌聚合项")
    public static class BrandAggregation {
        @Schema(description = "品牌ID")
        private Long brandId;
        
        @Schema(description = "品牌名称")
        private String brandName;
        
        @Schema(description = "品牌logo")
        private String brandLogo;
        
        @Schema(description = "商品数量")
        private Long productCount;
        
        @Schema(description = "是否选中")
        private Boolean selected;
    }
    
    /**
     * 分类聚合项
     */
    @Data
    @Builder
    @Schema(description = "分类聚合项")
    public static class CategoryAggregation {
        @Schema(description = "分类ID")
        private Long categoryId;
        
        @Schema(description = "分类名称")
        private String categoryName;
        
        @Schema(description = "分类层级")
        private Integer level;
        
        @Schema(description = "父分类ID")
        private Long parentId;
        
        @Schema(description = "商品数量")
        private Long productCount;
        
        @Schema(description = "子分类")
        private List<CategoryAggregation> children;
        
        @Schema(description = "是否选中")
        private Boolean selected;
    }
    
    /**
     * 属性聚合项
     */
    @Data
    @Builder
    @Schema(description = "属性聚合项")
    public static class AttributeAggregation {
        @Schema(description = "属性名")
        private String attrName;
        
        @Schema(description = "属性显示名")
        private String attrDisplayName;
        
        @Schema(description = "属性值列表")
        private List<AttributeValue> values;
        
        @Schema(description = "属性类型")
        private String attrType; // COLOR, SIZE, SPEC
        
        @Schema(description = "是否多选")
        private Boolean multiSelect;
        
        @Data
        @Builder
        public static class AttributeValue {
            @Schema(description = "属性值")
            private String value;
            
            @Schema(description = "显示值")
            private String displayValue;
            
            @Schema(description = "商品数量")
            private Long count;
            
            @Schema(description = "是否选中")
            private Boolean selected;
            
            @Schema(description = "扩展信息(如颜色值)")
            private String extra;
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
        private BigDecimal minPrice;
        
        @Schema(description = "最高价")
        private BigDecimal maxPrice;
        
        @Schema(description = "价格区间分布")
        private List<PriceInterval> intervals;
        
        @Data
        @Builder
        public static class PriceInterval {
            @Schema(description = "区间起始价格")
            private BigDecimal from;
            
            @Schema(description = "区间结束价格")
            private BigDecimal to;
            
            @Schema(description = "区间描述")
            private String label;
            
            @Schema(description = "商品数量")
            private Long count;
            
            @Schema(description = "是否选中")
            private Boolean selected;
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
        private String tagName;
        
        @Schema(description = "标签显示名")
        private String displayName;
        
        @Schema(description = "商品数量")
        private Long count;
        
        @Schema(description = "标签类型")
        private String tagType; // HOT, NEW, PROMOTION
        
        @Schema(description = "标签颜色")
        private String color;
        
        @Schema(description = "是否选中")
        private Boolean selected;
    }
    
    /**
     * 搜索统计信息
     */
    @Data
    @Builder
    @Schema(description = "搜索统计信息")
    public static class SearchStatistics {
        @Schema(description = "结果总数")
        private Long totalCount;
        
        @Schema(description = "品牌数量")
        private Integer brandCount;
        
        @Schema(description = "分类数量")
        private Integer categoryCount;
        
        @Schema(description = "价格区间")
        private String priceRange;
        
        @Schema(description = "平均价格")
        private BigDecimal avgPrice;
    }
}