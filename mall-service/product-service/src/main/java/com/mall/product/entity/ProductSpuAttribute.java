package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Product SPU Attribute Value Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_spu_attribute")
public class ProductSpuAttribute implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "SPU ID")
    private Long spuId;

    @Schema(description = "属性ID")
    private Long attributeId;

    @Schema(description = "属性名")
    private String attributeName;

    @Schema(description = "属性值")
    private String attributeValue;

    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}