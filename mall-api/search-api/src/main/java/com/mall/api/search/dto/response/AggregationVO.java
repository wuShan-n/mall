package com.mall.api.search.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Aggregation view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search aggregation")
public class AggregationVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Aggregation key", example = "Apple")
    private String key;
    
    @Schema(description = "Aggregation value/ID", example = "1")
    private String value;
    
    @Schema(description = "Document count", example = "100")
    private Long docCount;
    
    @Schema(description = "Display name", example = "Apple")
    private String displayName;
    
    @Schema(description = "Is selected", example = "false")
    private Boolean selected = false;
    
    @Schema(description = "Extra data (e.g., logo for brand)")
    private String extraData;
    
    @Schema(description = "Sort order", example = "1")
    private Integer sortOrder;
}