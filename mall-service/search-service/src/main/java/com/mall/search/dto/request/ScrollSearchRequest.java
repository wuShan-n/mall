package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 滚动搜索请求
 */
@Data
@Schema(description = "滚动搜索请求")
public class ScrollSearchRequest {
    @Schema(description = "搜索条件")
    private ProductSearchRequest searchRequest;

    @Schema(description = "滚动ID")
    private String scrollId;

    @Schema(description = "每批大小")
    private Integer batchSize = 1000;

    @Schema(description = "保持时间(分钟)")
    private Integer keepAliveMinutes = 5;
}
