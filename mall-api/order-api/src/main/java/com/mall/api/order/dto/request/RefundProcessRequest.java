package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Refund process request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund process request")
public class RefundProcessRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Refund number cannot be empty")
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @NotNull(message = "Process action cannot be null")
    @Schema(description = "Process action: 1-Approve, 2-Reject", example = "1")
    private Integer action;
    
    @Schema(description = "Handle note", example = "Approved for refund")
    private String handleNote;
    
    @Schema(description = "Handler ID", example = "1")
    private Long handlerId;
    
    @Schema(description = "Handler name", example = "Admin")
    private String handlerName;
}
