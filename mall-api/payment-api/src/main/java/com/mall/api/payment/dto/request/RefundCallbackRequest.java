package com.mall.api.payment.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Refund callback request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund callback request")
public class RefundCallbackRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @Schema(description = "Third party refund ID", example = "REF2024010122001")
    private String thirdPartyRefundNo;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Refund status", example = "SUCCESS")
    private String status;
    
    @Schema(description = "Refund amount", example = "50.00")
    private BigDecimal refundAmount;
    
    @Schema(description = "Refund time", example = "2024-01-02 10:00:00")
    private LocalDateTime refundTime;
    
    @Schema(description = "Signature", example = "abc123...")
    private String signature;
    
    @Schema(description = "Callback parameters")
    private Map<String, String> callbackParams;
}