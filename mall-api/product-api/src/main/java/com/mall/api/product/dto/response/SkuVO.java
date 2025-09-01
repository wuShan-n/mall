package com.mall.api.product.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * SKU view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SKU information")
public class SkuVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "SKU ID", example = "1")
    private Long id;
    
    @Schema(description = "SPU ID", example = "1")
    private Long spuId;
    
    @Schema(description = "SKU name", example = "iPhone 15 Pro 256GB Natural Titanium")
    private String skuName;
    
    @Schema(description = "SKU code", example = "IP15PRO-256-NT")
    private String skuCode;
    
    @Schema(description = "Sale price", example = "9999.00")
    private BigDecimal price;
    
    @Schema(description = "Original price", example = "10999.00")
    private BigDecimal originalPrice;
    
    @Schema(description = "Cost price", example = "8000.00")
    private BigDecimal costPrice;
    
    @Schema(description = "Stock quantity", example = "100")
    private Integer stock;
    
    @Schema(description = "Warning stock level", example = "10")
    private Integer warnStock;
    
    @Schema(description = "SKU image URL", example = "https://example.com/sku.jpg")
    private String image;
    
    @Schema(description = "Specification values", example = "{\"color\":\"Natural Titanium\",\"storage\":\"256GB\"}")
    private Map<String, String> specs;
    
    @Schema(description = "Weight in kg", example = "0.5")
    private BigDecimal weight;
    
    @Schema(description = "Volume in cubic meters", example = "0.001")
    private BigDecimal volume;
    
    @Schema(description = "Sale count", example = "50")
    private Integer saleCount;
    
    @Schema(description = "Locked stock", example = "5")
    private Integer lockStock;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}