package com.mall.api.inventory.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Inventory check request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Inventory check request")
public class InventoryCheckRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Warehouse ID cannot be null")
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @NotEmpty(message = "Check items cannot be empty")
    @Valid
    @Schema(description = "Check items")
    private List<CheckItem> items;
    
    @Schema(description = "Check reason", example = "Monthly inventory check")
    private String reason;
    
    @Schema(description = "Operator ID", example = "1")
    private Long operatorId;
    
    @Schema(description = "Operator name", example = "Admin")
    private String operatorName;
    
    @Data
    @Schema(description = "Inventory check item")
    public static class CheckItem {
        
        @NotNull(message = "SKU ID cannot be null")
        @Schema(description = "SKU ID", example = "1")
        private Long skuId;
        
        @NotNull(message = "System stock cannot be null")
        @Schema(description = "System stock quantity", example = "100")
        private Integer systemStock;
        
        @NotNull(message = "Actual stock cannot be null")
        @Schema(description = "Actual stock quantity", example = "98")
        private Integer actualStock;
        
        @Schema(description = "Difference reason", example = "Damage during storage")
        private String differenceReason;
    }
}