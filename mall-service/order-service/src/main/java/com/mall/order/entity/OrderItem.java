package com.mall.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单商品项实体类
 */
@Data
@TableName("oms_order_item")
public class OrderItem {
    @TableId(type = IdType.AUTO)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "订单ID")
    private Long orderId;
    @Schema(description = "订单编号")
    private String orderNo;
    @Schema(description = "SPU ID")
    private Long spuId;
    @Schema(description = "SKU ID")
    private Long skuId;
    @Schema(description = "商品名称")
    private String productName;
    @Schema(description = "商品图片")
    private String productImage;
    @Schema(description = "商品规格")
    private String productSpecs;
    @Schema(description = "商品编码")
    private String productCode;
    @Schema(description = "商品分类ID")
    private Long productCategoryId;
    @Schema(description = "品牌")
    private String productBrand;
    @Schema(description = "销售价格")
    private BigDecimal price;
    @Schema(description = "购买数量")
    private Integer quantity;
    @Schema(description = "商品总价")
    private BigDecimal totalAmount;
    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;
    @Schema(description = "实际金额")
    private BigDecimal realAmount;
    @Schema(description = "促销名称")
    private String promotionName;
    @Schema(description = "赠送的成长值")
    private Integer giftGrowth;
    @Schema(description = "赠送的积分")
    private Integer giftPoint;
    @Schema(description = "退款状态：0->未退款；1->退款中；2->已退款")
    private Integer refundStatus;
    @Schema(description = "退款金额")
    private BigDecimal refundAmount;
    @Schema(description = "评价状态：0->未评价；1->已评价")
    private Integer commentStatus;
    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}