package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 库存统计请求
 */
@Data
@Schema(description = "库存统计请求")
public class InventoryStatRequest {
    @Schema(description = "仓库ID列表")
    private List<Long> warehouseIds;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "品牌ID")
    private Long brandId;

    @Schema(description = "统计维度")
    private StatDimension dimension;

    @Schema(description = "时间范围")
    private DateRange dateRange;

    public enum StatDimension {
        WAREHOUSE,  // 按仓库
        CATEGORY,   // 按分类
        BRAND,      // 按品牌
        SKU         // 按SKU
    }

    @Data
    public static class DateRange {
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }
}
