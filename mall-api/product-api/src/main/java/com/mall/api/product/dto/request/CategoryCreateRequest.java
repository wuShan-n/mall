package com.mall.api.product.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Category create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Category create request")
public class CategoryCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Parent ID cannot be null")
    @Schema(description = "Parent category ID, 0 for root", example = "0")
    private Long parentId;
    
    @NotBlank(message = "Category name cannot be empty")
    @Schema(description = "Category name", example = "Electronics")
    private String categoryName;
    
    @NotBlank(message = "Category code cannot be empty")
    @Schema(description = "Category code", example = "ELEC")
    private String categoryCode;
    
    @NotNull(message = "Level cannot be null")
    @Schema(description = "Category level", example = "1")
    private Integer level;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort = 0;
    
    @Schema(description = "Icon URL", example = "https://example.com/icon.png")
    private String icon;
    
    @Schema(description = "Description", example = "Electronic products category")
    private String description;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status = 1;
}
