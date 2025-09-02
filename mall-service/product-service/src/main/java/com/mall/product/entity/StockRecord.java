package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Stock Change Record Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_stock_record")
public class StockRecord implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "记录ID")
    private Long id;

    @Schema(description = "SKU ID")
    private Long skuId;

    @Schema(description = "变更数量")
    private Integer quantity;

    @Schema(description = "变更前库存")
    private Integer stockBefore;

    @Schema(description = "变更后库存")
    private Integer stockAfter;

    @Schema(description = "操作: 1-入库, 2-出库, 3-锁定, 4-解锁")
    private Integer operationType;

    @Schema(description = "业务类型")
    private String businessType;

    @Schema(description = "业务单号")
    private String businessNo;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "操作者ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

}