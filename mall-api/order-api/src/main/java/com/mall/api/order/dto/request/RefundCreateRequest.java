package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.List;

/**
 * Refund create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund create request")
public class RefundCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "Order item IDs to refund")
    private List<Long> orderItemIds;
    
    @NotNull(message = "Refund type cannot be null")
    @Schema(description = "Refund type: 1-Refund only, 2-Return and refund", example = "1")
    private Integer refundType;
    
    @NotNull(message = "Refund amount cannot be null")
    @Schema(description = "Refund amount", example = "100.00")
    private BigDecimal refundAmount;
    
    @NotBlank(message = "Refund reason cannot be empty")
    @Schema(description = "Refund reason", example = "Product damaged")
    private String refundReason;
    
    @Schema(description = "Description", example = "The screen is broken")
    private String description;
    
    @Schema(description = "Proof images")
    private List<String> proofImages;
}
