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
 * 商品简要信息
 * =====================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品简要信息")
public class ProductSimpleVO {
    
    @Schema(description = "SPU ID")
    private Long spuId;
    
    @Schema(description = "商品名称")
    private String productName;
    
    @Schema(description = "商品编码")
    private String productCode;
    
    @Schema(description = "主图")
    private String mainImage;
    
    @Schema(description = "最低价")
    private BigDecimal minPrice;
    
    @Schema(description = "最高价")
    private BigDecimal maxPrice;
    
    @Schema(description = "原价")
    private BigDecimal originalPrice;
    
    @Schema(description = "销量")
    private Integer salesCount;
    
    @Schema(description = "评论数")
    private Integer commentCount;
    
    @Schema(description = "评分")
    private Float score;
    
    @Schema(description = "品牌名称")
    private String brandName;
    
    @Schema(description = "分类名称")
    private String categoryName;
    
    @Schema(description = "标签")
    private List<String> tags;
    
    @Schema(description = "是否有货")
    private Boolean hasStock;
    
    @Schema(description = "商品标识")
    private ProductFlags flags;
    
    @Schema(description = "活动信息")
    private ActivityInfo activity;
    
    /**
     * 商品标识
     */
    @Data
    @Builder
    public static class ProductFlags {
        @Schema(description = "是否新品")
        private Boolean isNew;
        
        @Schema(description = "是否热销")
        private Boolean isHot;
        
        @Schema(description = "是否精品")
        private Boolean isBest;
        
        @Schema(description = "是否包邮")
        private Boolean freeShipping;
        
        @Schema(description = "是否自营")
        private Boolean isSelfOperated;
    }
    
    /**
     * 活动信息
     */
    @Data
    @Builder
    public static class ActivityInfo {
        @Schema(description = "活动类型")
        private String type;
        
        @Schema(description = "活动名称")
        private String name;
        
        @Schema(description = "活动标签")
        private String label;
        
        @Schema(description = "活动图标")
        private String icon;
    }
}
