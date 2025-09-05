package com.mall.api.product.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * SPU create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SPU create request")
public class SpuCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Product name cannot be empty")
    @Schema(description = "Product name", example = "iPhone 15 Pro")
    private String productName;
    
    @NotBlank(message = "Product code cannot be empty")
    @Schema(description = "Product code", example = "IP15PRO")
    private String productCode;
    
    @NotNull(message = "Category ID cannot be null")
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Brand ID", example = "1")
    private Long brandId;
    
    @NotBlank(message = "Main image cannot be empty")
    @Schema(description = "Main image URL", example = "https://example.com/main.jpg")
    private String mainImage;
    
    @Schema(description = "Image list")
    private List<String> images;
    
    @Schema(description = "Video URL", example = "https://example.com/video.mp4")
    private String videoUrl;
    
    @Schema(description = "Unit", example = "piece")
    private String unit;
    
    @Schema(description = "Weight in kg", example = "0.5")
    private BigDecimal weight;
    
    @Schema(description = "Product introduction")
    private String introduction;
    
    @Schema(description = "Keywords", example = "phone,smartphone,apple")
    private String keywords;
    
    @Schema(description = "Tags", example = "new,hot,recommended")
    private String tags;
    
    @Schema(description = "Is new product", example = "true")
    private Boolean isNew = false;
    
    @Schema(description = "Is hot sale", example = "false")
    private Boolean isHot = false;
    
    @Schema(description = "Is best product", example = "false")
    private Boolean isBest = false;
    
    @Schema(description = "Status: 0-Off shelf, 1-On shelf", example = "1")
    private Integer status = 0;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort = 0;
    
    @Schema(description = "SKU list")
    private List<SkuCreateRequest> skuList;
    
    @Schema(description = "Product attributes")
    private List<ProductAttributeValue> attributes;
}