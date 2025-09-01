package com.mall.api.product.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Product statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Product statistics")
public class ProductStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total products", example = "10000")
    private Long totalProducts;
    
    @Schema(description = "On shelf products", example = "8000")
    private Long onShelfProducts;
    
    @Schema(description = "Off shelf products", example = "2000")
    private Long offShelfProducts;
    
    @Schema(description = "Total SKUs", example = "50000")
    private Long totalSkus;
    
    @Schema(description = "Out of stock SKUs", example = "100")
    private Long outOfStockSkus;
    
    @Schema(description = "Low stock SKUs", example = "500")
    private Long lowStockSkus;
    
    @Schema(description = "Total categories", example = "100")
    private Long totalCategories;
    
    @Schema(description = "Total brands", example = "50")
    private Long totalBrands;
    
    @Schema(description = "Today new products", example = "10")
    private Long todayNewProducts;
    
    @Schema(description = "This month new products", example = "300")
    private Long monthNewProducts;
    
    @Schema(description = "Total inventory value", example = "1000000.00")
    private BigDecimal totalInventoryValue;
    
    @Schema(description = "Average product rating", example = "4.5")
    private BigDecimal avgProductRating;
}