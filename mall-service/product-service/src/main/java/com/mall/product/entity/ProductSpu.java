package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * SPU (Standard Product Unit) Entity
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_spu")
public class ProductSpu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String productName;
    private String productCode;
    private Long categoryId;
    private Long brandId;
    private String mainImage;
    private String images;
    private String videoUrl;
    private String unit;
    private BigDecimal weight;
    private String introduction;
    private String keywords;
    private String tags;
    private Integer saleCount;
    private Integer viewCount;
    private Integer commentCount;
    private BigDecimal score;
    private Boolean isNew;
    private Boolean isHot;
    private Boolean isBest;
    private Integer status;
    private Integer auditStatus;
    private Integer sort;
    private String detailHtml;
    private String detailMobileHtml;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableLogic
    private Integer deleted;
}