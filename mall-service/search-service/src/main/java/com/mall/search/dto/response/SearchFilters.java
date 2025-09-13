package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

/**
 * 搜索筛选项
 */
@Value
@Builder
@Schema(description = "搜索筛选项")
public class SearchFilters {
    @Schema(description = "品牌列表")
    List<FilterOption> brands;

    @Schema(description = "分类列表")
    List<FilterOption> categories;

    @Schema(description = "属性列表")
    List<AttributeFilter> attributes;

    @Schema(description = "价格区间")
    List<PriceFilter> priceRanges;

    @Schema(description = "标签列表")
    List<FilterOption> tags;

    @Data
    @Builder
    public static class FilterOption {
        Long id;
        String name;
        Long count;
        Boolean selected;
        String icon;
    }

    @Data
    @Builder
    public static class AttributeFilter {
        String attrName;
        String displayName;
        List<FilterOption> options;
        Boolean multiSelect;
    }

    @Data
    @Builder
    public static class PriceFilter {
        BigDecimal minPrice;
        BigDecimal maxPrice;
        String label;
        Long count;
        Boolean selected;
    }
}
