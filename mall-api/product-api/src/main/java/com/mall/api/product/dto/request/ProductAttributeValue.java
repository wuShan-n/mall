package com.mall.api.product.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;

/**
 * Product attribute value
 */
@Data
@Schema(description = "Product attribute value")
public class ProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Attribute ID", example = "1")
    private Long attributeId;
    
    @Schema(description = "Attribute name", example = "Screen Size")
    private String attributeName;
    
    @Schema(description = "Attribute value", example = "6.1 inches")
    private String attributeValue;
}
