package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.List;

/**
 * 商品搜索响应
 */
@Value
@Builder
@Schema(description = "商品搜索响应")
public class ProductSearchResponse {

    @Schema(description = "商品列表")
    List<ProductSearchVO> products;

    @Schema(description = "总记录数")
    Long total;

    @Schema(description = "总页数")
    Integer totalPages;

    @Schema(description = "当前页码")
    Integer pageNum;

    @Schema(description = "每页大小")
    Integer pageSize;

    @Schema(description = "搜索用时(ms)")
    Long took;

    @Schema(description = "聚合数据")
    SearchAggregations aggregations;

    @Schema(description = "搜索建议")
    SearchSuggestion suggestion;
}