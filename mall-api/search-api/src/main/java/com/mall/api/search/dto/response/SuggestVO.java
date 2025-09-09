package com.mall.api.search.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Search suggest view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search suggestion")
public class SuggestVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Suggestion text", example = "iPhone 15 Pro")
    private String text;
    
    @Schema(description = "Highlighted text", example = "<em>iPhone</em> 15 Pro")
    private String highlightedText;
    
    @Schema(description = "Suggestion type: product/brand/category", example = "product")
    private String type;
    
    @Schema(description = "Related ID (product/brand/category ID)", example = "1")
    private Long relatedId;
    
    @Schema(description = "Product count", example = "50")
    private Integer count;
    
    @Schema(description = "Is hot suggestion", example = "true")
    private Boolean isHot;
    
    @Schema(description = "Search frequency", example = "1000")
    private Integer frequency;
    
    @Schema(description = "Category name", example = "Smartphones")
    private String categoryName;
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Image URL", example = "https://example.com/image.jpg")
    private String imageUrl;
    
    @Schema(description = "Price range", example = "$999 - $1299")
    private String priceRange;
    
    @Schema(description = "Score/Weight", example = "100")
    private Integer score;
}