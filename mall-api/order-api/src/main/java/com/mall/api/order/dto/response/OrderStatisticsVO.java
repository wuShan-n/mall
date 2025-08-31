package com.mall.api.order.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * Order statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order statistics")
public class OrderStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total orders", example = "10000")
    private Long totalOrders;
    
    @Schema(description = "Today orders", example = "100")
    private Long todayOrders;
    
    @Schema(description = "This month orders", example = "3000")
    private Long monthOrders;
    
    @Schema(description = "Pending payment orders", example = "50")
    private Long pendingPaymentOrders;
    
    @Schema(description = "Pending delivery orders", example = "200")
    private Long pendingDeliveryOrders;
    
    @Schema(description = "Delivered orders", example = "150")
    private Long deliveredOrders;
    
    @Schema(description = "Completed orders", example = "8000")
    private Long completedOrders;
    
    @Schema(description = "Refunding orders", example = "20")
    private Long refundingOrders;
    
    @Schema(description = "Total sales amount", example = "1000000.00")
    private BigDecimal totalSalesAmount;
    
    @Schema(description = "Today sales amount", example = "10000.00")
    private BigDecimal todaySalesAmount;
    
    @Schema(description = "This month sales amount", example = "300000.00")
    private BigDecimal monthSalesAmount;
    
    @Schema(description = "Average order amount", example = "100.00")
    private BigDecimal avgOrderAmount;
    
    @Schema(description = "Refund rate", example = "0.02")
    private BigDecimal refundRate;
    
    @Schema(description = "Cancel rate", example = "0.05")
    private BigDecimal cancelRate;
}
