package com.mall.api.search.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Search suggest request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search suggest request")
public class SuggestRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Keyword cannot be empty")
    @Schema(description = "Search keyword prefix", example = "iPh")
    private String keyword;
    
    @Schema(description = "Suggest type: product/brand/category", example = "product")
    private String suggestType = "product";
    
    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 50, message = "Size cannot exceed 50")
    @Schema(description = "Number of suggestions", example = "10")
    private Integer size = 10;
    
    @Schema(description = "Include product count", example = "true")
    private Boolean includeCount = false;
    
    @Schema(description = "Include hot flag", example = "true")
    private Boolean includeHot = true;
    
    @Schema(description = "Category ID for filtering", example = "1")
    private Long categoryId;
}