package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * =====================================================
 * SKU简要信息
 * =====================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "SKU简要信息")
public class SkuSimpleVO {
    
    @Schema(description = "SKU ID")
    private Long skuId;
    
    @Schema(description = "SKU编码")
    private String skuCode;
    
    @Schema(description = "SKU名称")
    private String skuName;
    
    @Schema(description = "价格")
    private BigDecimal price;
    
    @Schema(description = "原价")
    private BigDecimal originalPrice;
    
    @Schema(description = "库存")
    private Integer stock;
    
    @Schema(description = "销量")
    private Integer salesCount;
    
    @Schema(description = "规格信息")
    private Map<String, String> specs;
    
    @Schema(description = "规格描述")
    private String specsText;
    
    @Schema(description = "SKU图片")
    private String image;
    
    @Schema(description = "是否可售")
    private Boolean available;
    
    @Schema(description = "是否默认")
    private Boolean isDefault;
    
    @Schema(description = "促销信息")
    private PromotionInfo promotion;
    
    /**
     * 促销信息
     */
    @Data
    @Builder
    public static class PromotionInfo {
        @Schema(description = "促销类型")
        private String type; // DISCOUNT, COUPON, GIFT
        
        @Schema(description = "促销标签")
        private String label;
        
        @Schema(description = "促销价格")
        private BigDecimal promotionPrice;
        
        @Schema(description = "折扣率")
        private Integer discountRate;
    }
}