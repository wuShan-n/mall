package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Map;

/**
 * =====================================================
 * SKU简要信息
 * =====================================================
 */
@Value
@Builder
@Schema(description = "SKU简要信息")
public class SkuSimpleVO {

    @Schema(description = "SKU ID")
    Long skuId;

    @Schema(description = "SKU编码")
    String skuCode;

    @Schema(description = "SKU名称")
    String skuName;

    @Schema(description = "价格")
    BigDecimal price;

    @Schema(description = "原价")
    BigDecimal originalPrice;

    @Schema(description = "库存")
    Integer stock;

    @Schema(description = "销量")
    Integer salesCount;

    @Schema(description = "规格信息")
    Map<String, String> specs;

    @Schema(description = "规格描述")
    String specsText;

    @Schema(description = "SKU图片")
    String image;

    @Schema(description = "是否可售")
    Boolean available;

    @Schema(description = "是否默认")
    Boolean isDefault;

    @Schema(description = "促销信息")
    PromotionInfo promotion;

    /**
     * 促销信息
     */
    @Data
    @Builder
    public static class PromotionInfo {
        @Schema(description = "促销类型")
        String type; // DISCOUNT, COUPON, GIFT

        @Schema(description = "促销标签")
        String label;

        @Schema(description = "促销价格")
        BigDecimal promotionPrice;

        @Schema(description = "折扣率")
        Integer discountRate;
    }
}