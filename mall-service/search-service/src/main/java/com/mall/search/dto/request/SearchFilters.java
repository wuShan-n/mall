package com.mall.search.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
/**
 * 搜索筛选项
 */
@Data
@Builder
@Schema(description = "搜索筛选项")
public class SearchFilters {
    @Schema(description = "品牌列表")
    private List<FilterOption> brands;
    
    @Schema(description = "分类列表")
    private List<FilterOption> categories;
    
    @Schema(description = "属性列表")
    private List<AttributeFilter> attributes;
    
    @Schema(description = "价格区间")
    private List<PriceFilter> priceRanges;
    
    @Schema(description = "标签列表")
    private List<FilterOption> tags;
    
    @Data
    @Builder
    public static class FilterOption {
        private Long id;
        private String name;
        private Long count;
        private Boolean selected;
        private String icon;
    }
    
    @Data
    @Builder
    public static class AttributeFilter {
        private String attrName;
        private String displayName;
        private List<FilterOption> options;
        private Boolean multiSelect;
    }
    
    @Data
    @Builder
    public static class PriceFilter {
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        private String label;
        private Long count;
        private Boolean selected;
    }
}
