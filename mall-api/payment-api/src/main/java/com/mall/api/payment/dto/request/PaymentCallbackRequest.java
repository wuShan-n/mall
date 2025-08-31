package com.mall.api.payment.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Payment callback request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment callback request")
public class PaymentCallbackRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Third party transaction ID", example = "2024010122001")
    private String transactionId;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Payment status", example = "SUCCESS")
    private String status;
    
    @Schema(description = "Payment amount", example = "100.00")
    private BigDecimal paymentAmount;
    
    @Schema(description = "Actual payment amount", example = "100.00")
    private BigDecimal actualAmount;
    
    @Schema(description = "Payment time", example = "2024-01-01 10:00:00")
    private LocalDateTime paymentTime;
    
    @Schema(description = "Signature", example = "abc123...")
    private String signature;
    
    @Schema(description = "Callback parameters")
    private Map<String, String> callbackParams;
}
