package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * SKU (Stock Keeping Unit) Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_sku")
public class ProductSku implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="SKU ID")
    private Long id;

    @Schema(description="SPU ID")
    private Long spuId;

    @Schema(description="SKU名称")
    private String skuName;

    @Schema(description="SKU编码")
    private String skuCode;

    @Schema(description="售价")
    private BigDecimal price;

    @Schema(description="原价")
    private BigDecimal originalPrice;

    @Schema(description="成本价")
    private BigDecimal costPrice;

    @Schema(description="库存数量")
    private Integer stock;

    @Schema(description="锁定库存")
    private Integer lockStock;

    @Schema(description="预警库存")
    private Integer warnStock;

    @Schema(description="SKU图片")
    private String image;

    @Schema(description="规格值JSON")
    private Object specs;

    @Schema(description="重量(kg)")
    private BigDecimal weight;

    @Schema(description="体积(m³)")
    private BigDecimal volume;

    @Schema(description="销量")
    private Integer saleCount;

    @Schema(description="状态: 0-禁用, 1-启用")
    private Integer status;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建者ID")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @Schema(description = "更新者ID")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @Schema(description = "删除标记")
    @TableLogic
    private Integer deleted;

}