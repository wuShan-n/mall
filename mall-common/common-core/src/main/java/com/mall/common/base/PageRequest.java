package com.mall.common.base;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pagination request parameters
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    /**
     * Current page number
     */
    @Min(value = 1, message = "Page number must be greater than 0")
    private Long current = 1L;
    
    /**
     * Page size
     */
    @Min(value = 1, message = "Page size must be greater than 0")
    @Max(value = 100, message = "Page size must not exceed 100")
    private Long size = 10L;
    
    /**
     * Sort field
     */
    private String orderBy;
    
    /**
     * Sort direction (asc/desc)
     */
    private String orderDirection = "desc";
}
