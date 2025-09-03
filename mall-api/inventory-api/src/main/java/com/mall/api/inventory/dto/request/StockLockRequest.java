package com.mall.api.inventory.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Stock lock request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock lock request")
public class StockLockRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "SKU ID cannot be null")
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity to lock", example = "5")
    private Integer quantity;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @Schema(description = "Lock expire time", example = "2024-01-01 10:30:00")
    private LocalDateTime expireTime;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
}
