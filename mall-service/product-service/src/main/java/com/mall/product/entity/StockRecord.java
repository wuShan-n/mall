package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long skuId;
    private Integer quantity;
    private Integer stockBefore;
    private Integer stockAfter;
    private Integer operationType;
    private String businessType;
    private String businessNo;
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
}