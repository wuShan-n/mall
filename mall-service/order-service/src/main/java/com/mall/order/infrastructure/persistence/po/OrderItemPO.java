package com.mall.order.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单项持久化对象
 */
@Data
@TableName("oms_order_item")
public class OrderItemPO {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long orderId;
    
    private String orderNo;
    
    // 商品信息
    private Long spuId;
    private Long skuId;
    private String productName;
    private String productImage;
    private String productSpecs;
    private String productCode;
    private Long productCategoryId;
    private String productBrand;
    
    // 价格信息
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal realAmount;
    
    // 促销信息
    private String promotionName;
    private Integer giftGrowth;
    private Integer giftPoint;
    
    // 售后信息
    private Integer refundStatus;
    private BigDecimal refundAmount;
    private Integer commentStatus;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}