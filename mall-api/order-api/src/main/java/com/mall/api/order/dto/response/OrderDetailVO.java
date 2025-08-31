package com.mall.api.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * Order detail view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order detail information")
public class OrderDetailVO extends OrderVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Order items")
    private List<OrderItemVO> orderItems;
    
    @Schema(description = "Order payment info")
    private OrderPaymentVO paymentInfo;
    
    @Schema(description = "Order timeline")
    private List<OrderTimelineVO> timeline;
    
    @Schema(description = "Invoice info")
    private InvoiceVO invoice;
    
    @Schema(description = "Coupon info")
    private CouponVO coupon;
    
    @Schema(description = "Points used", example = "100")
    private Integer pointsUsed;
    
    @Schema(description = "Points deducted amount", example = "10.00")
    private BigDecimal pointsAmount;
}