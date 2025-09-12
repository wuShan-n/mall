package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 库存统计结果
 */
@Data
@Builder
@Schema(description = "库存统计结果")
public class InventoryStatistics {
    @Schema(description = "总SKU数")
    private Integer totalSkuCount;
    
    @Schema(description = "总库存量")
    private Long totalStockQuantity;
    
    @Schema(description = "总库存价值")
    private BigDecimal totalStockValue;
    
    @Schema(description = "缺货SKU数")
    private Integer outOfStockCount;
    
    @Schema(description = "预警SKU数")
    private Integer warningCount;
    
    @Schema(description = "库存周转率")
    private Float turnoverRate;
    
    @Schema(description = "平均库存天数")
    private Float avgStockDays;
    
    @Schema(description = "分组统计")
    private List<GroupStatistics> groupStats;
    
    @Data
    @Builder
    public static class GroupStatistics {
        private String groupName;
        private Long stockQuantity;
        private BigDecimal stockValue;
        private Integer skuCount;
        private Float percentage;
    }
}