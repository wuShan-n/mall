package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * =====================================================
 * 商品简要信息
 * =====================================================
 */
@Value
@Builder
@Schema(description = "商品简要信息")
public class ProductSimpleVO {

    @Schema(description = "SPU ID")
    Long spuId;

    @Schema(description = "商品名称")
    String productName;

    @Schema(description = "商品编码")
    String productCode;

    @Schema(description = "主图")
    String mainImage;

    @Schema(description = "最低价")
    BigDecimal minPrice;

    @Schema(description = "最高价")
    BigDecimal maxPrice;

    @Schema(description = "原价")
    BigDecimal originalPrice;

    @Schema(description = "销量")
    Integer salesCount;

    @Schema(description = "评论数")
    Integer commentCount;

    @Schema(description = "评分")
    Float score;

    @Schema(description = "品牌名称")
    String brandName;

    @Schema(description = "分类名称")
    String categoryName;

    @Schema(description = "标签")
    List<String> tags;

    @Schema(description = "是否有货")
    Boolean hasStock;

    @Schema(description = "商品标识")
    ProductFlags flags;

    @Schema(description = "活动信息")
    ActivityInfo activity;

    /**
     * 商品标识
     */
    @Data
    @Builder
    public static class ProductFlags {
        @Schema(description = "是否新品")
        Boolean isNew;

        @Schema(description = "是否热销")
        Boolean isHot;

        @Schema(description = "是否精品")
        Boolean isBest;

        @Schema(description = "是否包邮")
        Boolean freeShipping;

        @Schema(description = "是否自营")
        Boolean isSelfOperated;
    }

    /**
     * 活动信息
     */
    @Data
    @Builder
    public static class ActivityInfo {
        @Schema(description = "活动类型")
        String type;

        @Schema(description = "活动名称")
        String name;

        @Schema(description = "活动标签")
        String label;

        @Schema(description = "活动图标")
        String icon;
    }
}
