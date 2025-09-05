package com.mall.api.product.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Brand create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Brand create request")
public class BrandCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Brand name cannot be empty")
    @Schema(description = "Brand name", example = "Apple")
    private String brandName;
    
    @NotBlank(message = "Brand code cannot be empty")
    @Schema(description = "Brand code", example = "APPLE")
    private String brandCode;
    
    @Schema(description = "Logo URL", example = "https://example.com/logo.png")
    private String logo;
    
    @Schema(description = "Description", example = "Apple Inc.")
    private String description;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort = 0;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status = 1;
}