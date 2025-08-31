package com.mall.api.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Specification view object
 */
@Data
@Schema(description = "Product specification")
public class SpecificationVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Specification name", example = "Color")
    private String specName;
    
    @Schema(description = "Specification values", example = "[\"Natural Titanium\", \"Blue Titanium\", \"White Titanium\", \"Black Titanium\"]")
    private List<String> specValues;
}