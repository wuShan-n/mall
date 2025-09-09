package com.mall.api.search.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Hot keyword view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Hot search keyword")
public class HotKeywordVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Keyword", example = "iPhone 15")
    private String keyword;
    
    @Schema(description = "Search count", example = "10000")
    private Long searchCount;
    
    @Schema(description = "Trend: UP/DOWN/STABLE", example = "UP")
    private String trend;
    
    @Schema(description = "Trend percentage", example = "25.5")
    private Double trendPercentage;
    
    @Schema(description = "Rank", example = "1")
    private Integer rank;
    
    @Schema(description = "Previous rank", example = "3")
    private Integer previousRank;
    
    @Schema(description = "Is new", example = "true")
    private Boolean isNew;
    
    @Schema(description = "Is hot", example = "true")
    private Boolean isHot;
    
    @Schema(description = "Category", example = "Electronics")
    private String category;
    
    @Schema(description = "Icon/Badge", example = "🔥")
    private String icon;
    
    @Schema(description = "Color for display", example = "#FF0000")
    private String color;
}