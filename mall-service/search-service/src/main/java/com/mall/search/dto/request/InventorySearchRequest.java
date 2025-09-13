package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "库存搜索请求")
public class InventorySearchRequest {

    @Schema(description = "SKU编码")
    private String skuCode;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "仓库ID列表")
    private List<Long> warehouseIds;

    @Schema(description = "库存状态")
    private StockStatus stockStatus;

    @Schema(description = "是否需要补货")
    private Boolean needReplenish;

    @Schema(description = "库存范围")
    private StockRange stockRange;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "品牌ID")
    private Long brandId;

    private Integer pageNum = 1;
    private Integer pageSize = 20;

    public enum StockStatus {
        SUFFICIENT,  // 充足
        WARNING,     // 预警
        DANGER,      // 危险
        OUT_OF_STOCK // 缺货
    }

    @Data
    public static class StockRange {
        private Integer minStock;
        private Integer maxStock;
    }
}