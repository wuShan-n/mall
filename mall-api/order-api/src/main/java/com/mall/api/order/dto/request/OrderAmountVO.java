package com.mall.api.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Order amount view object
 */
@Data
@Schema(description = "Order amount breakdown")
public class OrderAmountVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Product amount", example = "1000.00")
    private BigDecimal productAmount;
    
    @Schema(description = "Freight amount", example = "10.00")
    private BigDecimal freightAmount;
    
    @Schema(description = "Tax amount", example = "50.00")
    private BigDecimal taxAmount;
    
    @Schema(description = "Discount amount", example = "100.00")
    private BigDecimal discountAmount;
    
    @Schema(description = "Coupon discount", example = "50.00")
    private BigDecimal couponDiscount;
    
    @Schema(description = "Points discount", example = "20.00")
    private BigDecimal pointsDiscount;
    
    @Schema(description = "Promotion discount", example = "30.00")
    private BigDecimal promotionDiscount;
    
    @Schema(description = "Total amount", example = "1010.00")
    private BigDecimal totalAmount;
    
    @Schema(description = "Pay amount", example = "910.00")
    private BigDecimal payAmount;
}