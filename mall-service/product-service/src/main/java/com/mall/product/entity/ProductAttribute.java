package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Product Attribute Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_attribute")
public class ProductAttribute implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "属性ID")
    private Long id;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "属性名")
    private String attrName;

    @Schema(description = "类型: 0-规格, 1-参数")
    private Integer attrType;

    @Schema(description = "录入方式: 0-手动, 1-选择")
    private Integer inputType;

    @Schema(description = "可选值(逗号分隔)")
    private String values;

    @Schema(description = "单位")
    private String unit;

    @Schema(description = "是否必填")
    private Integer isRequired;

    @Schema(description = "是否可搜索")
    private Integer isSearch;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态: 0-禁用, 1-启用")
    private Integer status;

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
