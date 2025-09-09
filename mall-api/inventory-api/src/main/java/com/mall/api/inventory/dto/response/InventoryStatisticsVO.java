package com.mall.api.inventory.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Inventory statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Inventory statistics")
public class InventoryStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Total warehouses", example = "10")
    private Integer totalWarehouses;

    @Schema(description = "Active warehouses", example = "8")
    private Integer activeWarehouses;

    @Schema(description = "Total SKUs", example = "10000")
    private Long totalSkus;

    @Schema(description = "Total stock quantity", example = "1000000")
    private Long totalStockQuantity;

    @Schema(description = "Total stock value", example = "50000000.00")
    private BigDecimal totalStockValue;

    @Schema(description = "Available stock quantity", example = "950000")
    private Long availableStockQuantity;

    @Schema(description = "Locked stock quantity", example = "50000")
    private Long lockedStockQuantity;

    @Schema(description = "Out of stock SKUs", example = "100")
    private Long outOfStockSkus;

    @Schema(description = "Low stock SKUs", example = "500")
    private Long lowStockSkus;

    @Schema(description = "Overstock SKUs", example = "200")
    private Long overstockSkus;
}