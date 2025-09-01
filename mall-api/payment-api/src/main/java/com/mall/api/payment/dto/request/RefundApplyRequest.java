package com.mall.api.payment.dto.request;

import com.mall.common.base.BaseDTO;
import com.mall.common.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * Refund apply request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund apply request")
public class RefundApplyRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Payment number cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Original payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @NotBlank(message = "Order number cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Business order number", example = "ORD202401010001")
    private String orderNo;
    
    @NotNull(message = "User ID cannot be null", groups = ValidationGroups.Create.class)
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @NotNull(message = "Refund amount cannot be null", groups = ValidationGroups.Create.class)
    @DecimalMin(value = "0.01", message = "Refund amount must be greater than 0")
    @Schema(description = "Refund amount", example = "50.00")
    private BigDecimal refundAmount;
    
    @NotBlank(message = "Refund reason cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Refund reason", example = "Product quality issue")
    private String refundReason;
    
    @Schema(description = "Refund description", example = "The product has defects")
    private String description;
    
    @Schema(description = "Notify URL", example = "https://example.com/refund/notify")
    private String notifyUrl;
    
    @Schema(description = "Operator ID", example = "1")
    private Long operatorId;
    
    @Schema(description = "Operator name", example = "Admin")
    private String operatorName;
}