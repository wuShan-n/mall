package com.mall.api.inventory.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Stock query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock query request")
public class StockQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "SKU code", example = "SKU001")
    private String skuCode;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @Schema(description = "Minimum stock", example = "0")
    private Integer minStock;
    
    @Schema(description = "Maximum stock", example = "1000")
    private Integer maxStock;
    
    @Schema(description = "Has available stock", example = "true")
    private Boolean hasStock;
    
    @Schema(description = "Is low stock", example = "false")
    private Boolean isLowStock;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
}
