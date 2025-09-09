package com.mall.api.search.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Search history request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search history request")
public class SearchHistoryRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "User ID cannot be null")
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size cannot exceed 100")
    @Schema(description = "Number of history records", example = "20")
    private Integer size = 20;
    
    @Schema(description = "Search type filter", example = "product")
    private String searchType;
    
    @Schema(description = "Include click count", example = "true")
    private Boolean includeClickCount = false;
}