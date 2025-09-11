package com.mall.api.search.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Search request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search request")
public class ProductSearchQuery extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Search keyword cannot be empty")
    @Schema(description = "Search keyword", example = "iPhone")
    private String keyword;
    
    @Schema(description = "Search type: product/store/brand", example = "product")
    private String searchType = "product";
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Brand ID", example = "1")
    private Long brandId;
    
    @Schema(description = "Brand IDs for filter")
    private List<Long> brandIds;
    
    @Schema(description = "Minimum price", example = "0.00")
    private BigDecimal minPrice;
    
    @Schema(description = "Maximum price", example = "10000.00")
    private BigDecimal maxPrice;
    
    @Schema(description = "Product attributes filter", example = "{\"color\":[\"red\",\"blue\"],\"size\":[\"M\",\"L\"]}")
    private Map<String, List<String>> attributes;
    
    @Schema(description = "Product tags", example = "[\"new\",\"hot\"]")
    private List<String> tags;
    
    @Schema(description = "Sort type: default/sales/price_asc/price_desc/new/score", example = "default")
    private String sortType = "default";
    
    @Schema(description = "Only in stock", example = "true")
    private Boolean inStock;
    
    @Schema(description = "Only on sale", example = "true")
    private Boolean onSale;
    
    @Schema(description = "Has discount", example = "false")
    private Boolean hasDiscount;
    
    @Schema(description = "Is new arrival", example = "false")
    private Boolean isNew;
    
    @Schema(description = "Is hot sale", example = "false")
    private Boolean isHot;
    
    @Schema(description = "Is best product", example = "false")
    private Boolean isBest;
    
    @Schema(description = "Minimum score", example = "4.0")
    private Float minScore;
    
    @Schema(description = "Include aggregations", example = "true")
    private Boolean includeAggregations = true;
    
    @Schema(description = "Highlight keyword in results", example = "true")
    private Boolean highlight = true;
    
    @Schema(description = "User location latitude", example = "34.0522")
    private Double latitude;
    
    @Schema(description = "User location longitude", example = "-118.2437")
    private Double longitude;
    
    @Schema(description = "Search within distance (km)", example = "10")
    private Integer distance;
}