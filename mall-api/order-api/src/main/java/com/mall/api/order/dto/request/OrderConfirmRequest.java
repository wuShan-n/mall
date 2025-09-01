package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Order confirm request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order confirm request")
public class OrderConfirmRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "Confirm type: 1-User confirm, 2-Auto confirm", example = "1")
    private Integer confirmType = 1;
}
