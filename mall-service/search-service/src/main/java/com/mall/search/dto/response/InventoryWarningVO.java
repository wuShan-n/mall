package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * 库存预警VO
 */
@Data
@Builder
@Schema(description = "库存预警信息")
public class InventoryWarningVO {
    @Schema(description = "SKU ID")
    private Long skuId;
    
    @Schema(description = "SKU编码")
    private String skuCode;
    
    @Schema(description = "商品名称")
    private String productName;
    
    @Schema(description = "仓库名称")
    private String warehouseName;
    
    @Schema(description = "当前库存")
    private Integer currentStock;
    
    @Schema(description = "预警阈值")
    private Integer warnThreshold;
    
    @Schema(description = "建议补货量")
    private Integer suggestReplenishment;
    
    @Schema(description = "预警级别")
    private String warningLevel; // LOW, MEDIUM, HIGH, CRITICAL
    
    @Schema(description = "最近7天销量")
    private Integer last7DaysSales;
    
    @Schema(description = "预计可售天数")
    private Integer estimatedDaysLeft;
}