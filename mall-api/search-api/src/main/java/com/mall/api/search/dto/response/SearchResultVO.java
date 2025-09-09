package com.mall.api.search.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * Search result view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search result")
public class SearchResultVO<T> extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total hits", example = "100")
    private Long total;
    
    @Schema(description = "Total pages", example = "10")
    private Long pages;
    
    @Schema(description = "Current page", example = "1")
    private Long current;
    
    @Schema(description = "Page size", example = "20")
    private Long size;
    
    @Schema(description = "Search results")
    private List<T> records;
    
    @Schema(description = "Search took time in ms", example = "15")
    private Long took;
    
    @Schema(description = "Max score", example = "10.5")
    private Float maxScore;
    
    @Schema(description = "Search keyword", example = "iPhone")
    private String keyword;
    
    @Schema(description = "Did you mean suggestion", example = "iPhone")
    private String didYouMean;
    
    @Schema(description = "Related searches")
    private List<String> relatedSearches;
    
    @Schema(description = "Aggregations")
    private Map<String, List<AggregationVO>> aggregations;
    
    @Schema(description = "Applied filters")
    private Map<String, Object> appliedFilters;
    
    @Schema(description = "Search tracking ID", example = "search_123456")
    private String trackingId;
}