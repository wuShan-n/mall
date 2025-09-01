package com.mall.api.payment.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund query request")
public class RefundQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Refund status", example = "2")
    private Integer status;
    
    @Schema(description = "Minimum amount", example = "0.00")
    private BigDecimal minAmount;
    
    @Schema(description = "Maximum amount", example = "10000.00")
    private BigDecimal maxAmount;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
}