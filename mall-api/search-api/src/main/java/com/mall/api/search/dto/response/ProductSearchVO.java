package com.mall.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Product search result view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Product search result")
public class ProductSearchVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Product ID", example = "1")
    private Long id;
    
    @Schema(description = "SPU ID", example = "1")
    private Long spuId;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "Product name with highlight", example = "<em>iPhone</em> 15 Pro")
    private String productName;
    
    @Schema(description = "Product code", example = "IP15PRO")
    private String productCode;
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Category name", example = "Smartphones")
    private String categoryName;
    
    @Schema(description = "Category path", example = "Electronics > Phones > Smartphones")
    private String categoryPath;
    
    @Schema(description = "Brand ID", example = "1")
    private Long brandId;
    
    @Schema(description = "Brand name", example = "Apple")
    private String brandName;
    
    @Schema(description = "Main image URL", example = "https://example.com/image.jpg")
    private String mainImage;
    
    @Schema(description = "Price", example = "999.99")
    private BigDecimal price;
    
    @Schema(description = "Original price", example = "1099.99")
    private BigDecimal originalPrice;
    
    @Schema(description = "Discount percentage", example = "10")
    private Integer discountPercent;
    
    @Schema(description = "Sales count", example = "1000")
    private Integer salesCount;
    
    @Schema(description = "Comment count", example = "500")
    private Integer commentCount;
    
    @Schema(description = "Average score", example = "4.8")
    private BigDecimal score;
    
    @Schema(description = "Stock status", example = "IN_STOCK")
    private String stockStatus;
    
    @Schema(description = "Available stock", example = "100")
    private Integer stock;
    
    @Schema(description = "Tags", example = "[\"New\", \"Hot\", \"Free Shipping\"]")
    private List<String> tags;
    
    @Schema(description = "Product attributes")
    private Map<String, String> attributes;
    
    @Schema(description = "SKU options")
    private List<SkuOptionVO> skuOptions;
    
    @Schema(description = "Promotion info", example = "Buy 2 Get 10% Off")
    private String promotionInfo;
    
    @Schema(description = "Is new arrival", example = "true")
    private Boolean isNew;
    
    @Schema(description = "Is hot sale", example = "true")
    private Boolean isHot;
    
    @Schema(description = "Is best product", example = "false")
    private Boolean isBest;
    
    @Schema(description = "Has discount", example = "true")
    private Boolean hasDiscount;
    
    @Schema(description = "Free shipping", example = "true")
    private Boolean freeShipping;
    
    @Schema(description = "Search score", example = "10.5")
    private Float searchScore;
    
    @Schema(description = "Distance in km (for location-based search)", example = "2.5")
    private Double distance;
    
    @Schema(description = "Store name", example = "Apple Store")
    private String storeName;
    
    @Schema(description = "Store ID", example = "1")
    private Long storeId;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    /**
     * SKU option
     */
    @Data
    public static class SkuOptionVO {
        @Schema(description = "SKU ID", example = "1")
        private Long skuId;
        
        @Schema(description = "SKU name", example = "256GB Natural Titanium")
        private String skuName;
        
        @Schema(description = "Price", example = "999.99")
        private BigDecimal price;
        
        @Schema(description = "Stock", example = "50")
        private Integer stock;
        
        @Schema(description = "Specifications")
        private Map<String, String> specs;
    }
}