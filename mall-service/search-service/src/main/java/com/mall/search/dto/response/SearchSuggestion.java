package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * =====================================================
 * 搜索建议
 * =====================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "搜索建议")
public class SearchSuggestion {
    
    @Schema(description = "是否有搜索结果")
    private Boolean hasResults;
    
    @Schema(description = "原始关键词")
    private String originalKeyword;
    
    @Schema(description = "纠正后的关键词")
    private String correctedKeyword;
    
    @Schema(description = "搜索建议列表")
    private List<SuggestionItem> suggestions;
    
    @Schema(description = "相关搜索词")
    private List<String> relatedKeywords;
    
    @Schema(description = "推荐分类")
    private List<RecommendCategory> recommendCategories;
    
    @Schema(description = "推荐品牌")
    private List<RecommendBrand> recommendBrands;
    
    /**
     * 建议项
     */
    @Data
    @Builder
    @Schema(description = "建议项")
    public static class SuggestionItem {
        @Schema(description = "建议文本")
        private String text;
        
        @Schema(description = "高亮文本(HTML)")
        private String highlightText;
        
        @Schema(description = "建议类型")
        private String type; // PRODUCT, CATEGORY, BRAND, KEYWORD
        
        @Schema(description = "关联ID")
        private Long refId;
        
        @Schema(description = "匹配度分数")
        private Float score;
        
        @Schema(description = "搜索次数")
        private Integer searchCount;
    }
    
    /**
     * 推荐分类
     */
    @Data
    @Builder
    @Schema(description = "推荐分类")
    public static class RecommendCategory {
        @Schema(description = "分类ID")
        private Long categoryId;
        
        @Schema(description = "分类名称")
        private String categoryName;
        
        @Schema(description = "分类路径")
        private String categoryPath;
        
        @Schema(description = "商品数量")
        private Integer productCount;
    }
    
    /**
     * 推荐品牌
     */
    @Data
    @Builder
    @Schema(description = "推荐品牌")
    public static class RecommendBrand {
        @Schema(description = "品牌ID")
        private Long brandId;
        
        @Schema(description = "品牌名称")
        private String brandName;
        
        @Schema(description = "品牌Logo")
        private String brandLogo;
        
        @Schema(description = "商品数量")
        private Integer productCount;
    }
}