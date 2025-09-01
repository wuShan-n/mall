package com.mall.api.payment.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Payment statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment statistics")
public class PaymentStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total payment count", example = "10000")
    private Long totalPaymentCount;
    
    @Schema(description = "Total payment amount", example = "1000000.00")
    private BigDecimal totalPaymentAmount;
    
    @Schema(description = "Today payment count", example = "100")
    private Long todayPaymentCount;
    
    @Schema(description = "Today payment amount", example = "10000.00")
    private BigDecimal todayPaymentAmount;
    
    @Schema(description = "Total refund count", example = "100")
    private Long totalRefundCount;
    
    @Schema(description = "Total refund amount", example = "10000.00")
    private BigDecimal totalRefundAmount;
    
    @Schema(description = "Success rate", example = "0.98")
    private BigDecimal successRate;
    
    @Schema(description = "Average payment amount", example = "100.00")
    private BigDecimal avgPaymentAmount;
    
    @Schema(description = "Payment type distribution")
    private Map<String, Long> paymentTypeDistribution;
    
    @Schema(description = "Daily statistics")
    private Map<String, BigDecimal> dailyStatistics;
}