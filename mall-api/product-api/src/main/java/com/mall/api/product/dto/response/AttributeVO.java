package com.mall.api.product.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Attribute view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Product attribute information")
public class AttributeVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Attribute ID", example = "1")
    private Long id;
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Attribute name", example = "Screen Size")
    private String attrName;
    
    @Schema(description = "Attribute type: 0-Spec, 1-Param", example = "1")
    private Integer attrType;
    
    @Schema(description = "Input type: 0-Manual, 1-Select", example = "1")
    private Integer inputType;
    
    @Schema(description = "Optional values", example = "5.8,6.1,6.7")
    private String values;
    
    @Schema(description = "Unit", example = "inches")
    private String unit;
    
    @Schema(description = "Is required", example = "true")
    private Boolean isRequired;
    
    @Schema(description = "Is searchable", example = "true")
    private Boolean isSearch;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status;
    
    @Schema(description = "Attribute value", example = "6.1 inches")
    private String attributeValue;
}