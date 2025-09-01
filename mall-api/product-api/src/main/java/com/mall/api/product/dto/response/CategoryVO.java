package com.mall.api.product.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Category view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Category information")
public class CategoryVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Category ID", example = "1")
    private Long id;
    
    @Schema(description = "Parent category ID", example = "0")
    private Long parentId;
    
    @Schema(description = "Category name", example = "Electronics")
    private String categoryName;
    
    @Schema(description = "Category code", example = "ELEC")
    private String categoryCode;
    
    @Schema(description = "Category level", example = "1")
    private Integer level;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort;
    
    @Schema(description = "Icon URL", example = "https://example.com/icon.png")
    private String icon;
    
    @Schema(description = "Description", example = "Electronic products")
    private String description;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status;
    
    @Schema(description = "Child categories")
    private List<CategoryVO> children;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}