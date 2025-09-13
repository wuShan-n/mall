package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * 订单商品VO
 */
@Value
@Builder
@Schema(description = "订单商品信息")
public class OrderItemVO {
    @Schema(description = "商品ID")
    Long skuId;

    @Schema(description = "商品名称")
    String productName;

    @Schema(description = "商品图片")
    String productImage;

    @Schema(description = "商品规格")
    String productSpecs;

    @Schema(description = "单价")
    BigDecimal price;

    @Schema(description = "数量")
    Integer quantity;

    @Schema(description = "小计")
    BigDecimal subtotal;
}