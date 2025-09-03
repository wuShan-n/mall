package com.mall.api.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Stock view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock information")
public class StockVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Stock ID", example = "1")
    private Long id;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "SKU code", example = "SKU001")
    private String skuCode;
    
    @Schema(description = "SKU name", example = "iPhone 15 Pro 256GB")
    private String skuName;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @Schema(description = "Warehouse name", example = "Beijing Main Warehouse")
    private String warehouseName;
    
    @Schema(description = "Total stock", example = "1000")
    private Integer totalStock;
    
    @Schema(description = "Available stock", example = "950")
    private Integer availableStock;
    
    @Schema(description = "Locked stock", example = "50")
    private Integer lockedStock;
    
    @Schema(description = "In transit stock", example = "100")
    private Integer inTransitStock;
    
    @Schema(description = "Warning stock level", example = "100")
    private Integer warnStock;
    
    @Schema(description = "Safety stock level", example = "50")
    private Integer safetyStock;
    
    @Schema(description = "Is available", example = "true")
    private Boolean isAvailable;
    
    @Schema(description = "Is low stock", example = "false")
    private Boolean isLowStock;
    
    @Schema(description = "Last update time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}