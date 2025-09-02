package com.mall.product.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description="SPU ID")
    private Long id;

    @Schema(description="商品名称")
    private String productName;

    @Schema(description="商品编码")
    private String productCode;

    @Schema(description="分类ID")
    private Long categoryId;

    @Schema(description="品牌ID")
    private Long brandId;

    @Schema(description="主图URL")
    private String mainImage;

    @Schema(description="图片列表JSON")
    private String images;

    @Schema(description="视频URL")
    private String videoUrl;

    @Schema(description="单位")
    private String unit;

    @Schema(description="重量(kg)")
    private BigDecimal weight;

    @Schema(description="商品简介")
    private String introduction;

    @Schema(description="关键词")
    private String keywords;

    @Schema(description="标签")
    private String tags;

    @Schema(description="销量")
    private Integer saleCount;

    @Schema(description="浏览量")
    private Integer viewCount;

    @Schema(description="评论数")
    private Integer commentCount;

    @Schema(description="平均评分")
    private BigDecimal score;

    @Schema(description="是否新品")
    private Integer isNew;

    @Schema(description="是否热销")
    private Integer isHot;

    @Schema(description="是否精品")
    private Integer isBest;

    @Schema(description="状态: 0-下架, 1-上架")
    private Integer status;

    @Schema(description="审核状态: 0-待审核, 1-通过, 2-驳回")
    private Integer auditStatus;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description="PC端详情HTML")
    private String detailHtml;

    @Schema(description="移动端详情HTML")
    private String detailMobileHtml;

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