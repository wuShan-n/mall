package com.mall.api.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * Order item request
 */
@Data
@Schema(description = "Order item")
public class OrderItemRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "SKU ID cannot be null")
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity", example = "2")
    private Integer quantity;
    
    @Schema(description = "Cart ID", example = "1")
    private Long cartId;
}