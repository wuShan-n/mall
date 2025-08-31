package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Order ship request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order ship request")
public class OrderShipRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @NotBlank(message = "Delivery company cannot be empty")
    @Schema(description = "Delivery company", example = "SF Express")
    private String deliveryCompany;
    
    @NotBlank(message = "Delivery number cannot be empty")
    @Schema(description = "Delivery tracking number", example = "SF123456789")
    private String deliveryNo;
}