package com.mall.api.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Refund result view object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Refund result")
public class RefundResultVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @Schema(description = "Refund status", example = "SUCCESS")
    private String status;
    
    @Schema(description = "Refund amount", example = "50.00")
    private BigDecimal refundAmount;
    
    @Schema(description = "Success flag", example = "true")
    private Boolean success;
    
    @Schema(description = "Message", example = "Refund processed successfully")
    private String message;
    
    @Schema(description = "Third party refund number", example = "REF2024010122001")
    private String thirdPartyRefundNo;
}