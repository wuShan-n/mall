package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
/**
 * 订单商品VO
 */
@Data
@Builder
@Schema(description = "订单商品信息")
public class OrderItemVO {
    @Schema(description = "商品ID")
    private Long skuId;
    
    @Schema(description = "商品名称")
    private String productName;
    
    @Schema(description = "商品图片")
    private String productImage;
    
    @Schema(description = "商品规格")
    private String productSpecs;
    
    @Schema(description = "单价")
    private BigDecimal price;
    
    @Schema(description = "数量")
    private Integer quantity;
    
    @Schema(description = "小计")
    private BigDecimal subtotal;
}