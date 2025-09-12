package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 商品搜索响应
 */
@Data
@Builder
@Schema(description ="商品搜索响应")
public class ProductSearchResponse {
    
    @Schema(description = "商品列表")
    private List<ProductSearchVO> products;
    
    @Schema(description = "总记录数")
    private Long total;
    
    @Schema(description = "总页数")
    private Integer totalPages;
    
    @Schema(description = "当前页码")
    private Integer pageNum;
    
    @Schema(description = "每页大小")
    private Integer pageSize;
    
    @Schema(description = "搜索用时(ms)")
    private Long took;
    
    @Schema(description = "聚合数据")
    private SearchAggregations aggregations;
    
    @Schema(description = "搜索建议")
    private SearchSuggestion suggestion;
}