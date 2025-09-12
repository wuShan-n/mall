package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * 库存VO
 */
@Data
@Builder
@Schema(description = "库存信息")
public class InventoryVO {
    @Schema(description = "库存ID")
    private Long id;
    
    @Schema(description = "SKU ID")
    private Long skuId;
    
    @Schema(description = "SKU编码")
    private String skuCode;
    
    @Schema(description = "SKU名称")
    private String skuName;
    
    @Schema(description = "SPU名称")
    private String spuName;
    
    @Schema(description = "仓库ID")
    private Long warehouseId;
    
    @Schema(description = "仓库名称")
    private String warehouseName;
    
    @Schema(description = "总库存")
    private Integer totalStock;
    
    @Schema(description = "可用库存")
    private Integer availableStock;
    
    @Schema(description = "锁定库存")
    private Integer lockedStock;
    
    @Schema(description = "在途库存")
    private Integer inTransitStock;
    
    @Schema(description = "预警库存")
    private Integer warnStock;
    
    @Schema(description = "库存状态")
    private String stockStatus;
    
    @Schema(description = "最后更新时间")
    private LocalDateTime updateTime;
}