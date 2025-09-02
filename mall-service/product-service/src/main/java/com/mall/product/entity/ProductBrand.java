package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Product Brand Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_brand")
public class ProductBrand implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="品牌ID")
    private Long id;

    @Schema(description="品牌名称")
    private String brandName;

    @Schema(description="品牌编码")
    private String brandCode;

    @Schema(description="LOGO URL")
    private String logo;

    @Schema(description="描述")
    private String description;

    @Schema(description="排序")
    private Integer sort;

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
