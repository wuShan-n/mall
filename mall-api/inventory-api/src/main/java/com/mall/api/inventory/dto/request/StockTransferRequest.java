package com.mall.api.inventory.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Stock transfer request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock transfer between warehouses request")
public class StockTransferRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Source warehouse ID cannot be null")
    @Schema(description = "Source warehouse ID", example = "1")
    private Long sourceWarehouseId;
    
    @NotNull(message = "Target warehouse ID cannot be null")
    @Schema(description = "Target warehouse ID", example = "2")
    private Long targetWarehouseId;
    
    @NotNull(message = "Transfer items cannot be null")
    @Schema(description = "Transfer items")
    private List<TransferItem> items;
    
    @Schema(description = "Transfer reason", example = "Warehouse optimization")
    private String reason;
    
    @Schema(description = "Operator ID", example = "1")
    private Long operatorId;
    
    @Schema(description = "Operator name", example = "Admin")
    private String operatorName;
    
    @Data
    @Schema(description = "Transfer item")
    public static class TransferItem {
        
        @NotNull(message = "SKU ID cannot be null")
        @Schema(description = "SKU ID", example = "1")
        private Long skuId;
        
        @NotNull(message = "Quantity cannot be null")
        @Min(value = 1, message = "Quantity must be at least 1")
        @Schema(description = "Transfer quantity", example = "100")
        private Integer quantity;
    }
}