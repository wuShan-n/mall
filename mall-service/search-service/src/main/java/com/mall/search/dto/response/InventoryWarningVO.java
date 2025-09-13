package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * 库存预警VO
 */
@Value
@Builder
@Schema(description = "库存预警信息")
public class InventoryWarningVO {
    @Schema(description = "SKU ID")
    Long skuId;

    @Schema(description = "SKU编码")
    String skuCode;

    @Schema(description = "商品名称")
    String productName;

    @Schema(description = "仓库名称")
    String warehouseName;

    @Schema(description = "当前库存")
    Integer currentStock;

    @Schema(description = "预警阈值")
    Integer warnThreshold;

    @Schema(description = "建议补货量")
    Integer suggestReplenishment;

    @Schema(description = "预警级别")
    String warningLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Schema(description = "最近7天销量")
    Integer last7DaysSales;

    @Schema(description = "预计可售天数")
    Integer estimatedDaysLeft;
}