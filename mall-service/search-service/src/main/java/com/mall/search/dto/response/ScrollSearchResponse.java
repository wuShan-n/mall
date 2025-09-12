package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 滚动搜索响应
 */
@Data
@Builder
@Schema(description = "滚动搜索响应")
public class ScrollSearchResponse {
    @Schema(description = "滚动ID")
    private String scrollId;
    
    @Schema(description = "数据列表")
    private List<ProductSearchVO> products;
    
    @Schema(description = "是否还有数据")
    private Boolean hasMore;
    
    @Schema(description = "当前批次")
    private Integer currentBatch;
    
    @Schema(description = "总记录数")
    private Long total;
}