package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * 库存VO
 */
@Value
@Builder
@Schema(description = "库存信息")
public class InventoryVO {
    @Schema(description = "库存ID")
    Long id;

    @Schema(description = "SKU ID")
    Long skuId;

    @Schema(description = "SKU编码")
    String skuCode;

    @Schema(description = "SKU名称")
    String skuName;

    @Schema(description = "SPU名称")
    String spuName;

    @Schema(description = "仓库ID")
    Long warehouseId;

    @Schema(description = "仓库名称")
    String warehouseName;

    @Schema(description = "总库存")
    Integer totalStock;

    @Schema(description = "可用库存")
    Integer availableStock;

    @Schema(description = "锁定库存")
    Integer lockedStock;

    @Schema(description = "在途库存")
    Integer inTransitStock;

    @Schema(description = "预警库存")
    Integer warnStock;

    @Schema(description = "库存状态")
    String stockStatus;

    @Schema(description = "最后更新时间")
    LocalDateTime updateTime;
}