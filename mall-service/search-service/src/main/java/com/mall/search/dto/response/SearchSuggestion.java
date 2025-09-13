package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

/**
 * =====================================================
 * 搜索建议
 * =====================================================
 */
@Value
@Builder
@Schema(description = "搜索建议")
public class SearchSuggestion {

    @Schema(description = "是否有搜索结果")
    Boolean hasResults;

    @Schema(description = "原始关键词")
    String originalKeyword;

    @Schema(description = "纠正后的关键词")
    String correctedKeyword;

    @Schema(description = "搜索建议列表")
    List<SuggestionItem> suggestions;

    @Schema(description = "相关搜索词")
    List<String> relatedKeywords;

    @Schema(description = "推荐分类")
    List<RecommendCategory> recommendCategories;

    @Schema(description = "推荐品牌")
    List<RecommendBrand> recommendBrands;

    /**
     * 建议项
     */
    @Data
    @Builder
    @Schema(description = "建议项")
    public static class SuggestionItem {
        @Schema(description = "建议文本")
        String text;

        @Schema(description = "高亮文本(HTML)")
        String highlightText;

        @Schema(description = "建议类型")
        String type; // PRODUCT, CATEGORY, BRAND, KEYWORD

        @Schema(description = "关联ID")
        Long refId;

        @Schema(description = "匹配度分数")
        Float score;

        @Schema(description = "搜索次数")
        Integer searchCount;
    }

    /**
     * 推荐分类
     */
    @Data
    @Builder
    @Schema(description = "推荐分类")
    public static class RecommendCategory {
        @Schema(description = "分类ID")
        Long categoryId;

        @Schema(description = "分类名称")
        String categoryName;

        @Schema(description = "分类路径")
        String categoryPath;

        @Schema(description = "商品数量")
        Integer productCount;
    }

    /**
     * 推荐品牌
     */
    @Data
    @Builder
    @Schema(description = "推荐品牌")
    public static class RecommendBrand {
        @Schema(description = "品牌ID")
        Long brandId;

        @Schema(description = "品牌名称")
        String brandName;

        @Schema(description = "品牌Logo")
        String brandLogo;

        @Schema(description = "商品数量")
        Integer productCount;
    }
}