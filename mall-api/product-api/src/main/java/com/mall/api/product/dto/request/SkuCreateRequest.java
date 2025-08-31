package com.mall.api.product.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Map;

/**
 * SKU create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SKU create request")
public class SkuCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "SKU name cannot be empty")
    @Schema(description = "SKU name", example = "iPhone 15 Pro 256GB Natural Titanium")
    private String skuName;
    
    @NotBlank(message = "SKU code cannot be empty")
    @Schema(description = "SKU code", example = "IP15PRO-256-NT")
    private String skuCode;
    
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Schema(description = "Sale price", example = "9999.00")
    private BigDecimal price;
    
    @Schema(description = "Original price", example = "10999.00")
    private BigDecimal originalPrice;
    
    @Schema(description = "Cost price", example = "8000.00")
    private BigDecimal costPrice;
    
    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    @Schema(description = "Stock quantity", example = "100")
    private Integer stock;
    
    @Schema(description = "Warning stock level", example = "10")
    private Integer warnStock = 10;
    
    @Schema(description = "SKU image URL", example = "https://example.com/sku.jpg")
    private String image;
    
    @Schema(description = "Specification values", example = "{\"color\":\"Natural Titanium\",\"storage\":\"256GB\"}")
    private Map<String, String> specs;
    
    @Schema(description = "Weight in kg", example = "0.5")
    private BigDecimal weight;
    
    @Schema(description = "Volume in cubic meters", example = "0.001")
    private BigDecimal volume;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status = 1;
}
