package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存统计结果
 */
@Value
@Builder
@Schema(description = "库存统计结果")
public class InventoryStatistics {
    @Schema(description = "总SKU数")
    Integer totalSkuCount;

    @Schema(description = "总库存量")
    Long totalStockQuantity;

    @Schema(description = "总库存价值")
    BigDecimal totalStockValue;

    @Schema(description = "缺货SKU数")
    Integer outOfStockCount;

    @Schema(description = "预警SKU数")
    Integer warningCount;

    @Schema(description = "库存周转率")
    Float turnoverRate;

    @Schema(description = "平均库存天数")
    Float avgStockDays;

    @Schema(description = "分组统计")
    @Singular
    List<GroupStatistics> groupStats;

    @Value
    @Builder
    public static class GroupStatistics {
        String groupName;
        Long stockQuantity;
        BigDecimal stockValue;
        Integer skuCount;
        Float percentage;
    }
}