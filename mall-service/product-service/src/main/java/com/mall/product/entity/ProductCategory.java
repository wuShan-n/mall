package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Product Category Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_category")
public class ProductCategory implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="分类ID")
    private Long id;

    @Schema(description="父分类ID")
    private Long parentId;

    @Schema(description="分类名称")
    private String categoryName;

    @Schema(description="分类编码")
    private String categoryCode;

    @Schema(description="分类层级")
    private Integer level;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description="图标URL")
    private String icon;

    @Schema(description="描述")
    private String description;

    @Schema(description="状态: 0-禁用, 1-启用")
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
