package com.mall.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单退款实体类
 */
@Data
@TableName("oms_order_refund")
public class OrderRefund {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String refundNo;
    private Long orderId;
    private String orderNo;
    private Long userId;
    
    // 退款信息
    private Integer refundType;
    private BigDecimal refundAmount;
    private String refundReason;
    private String description;
    private String proofPics;
    
    // 状态信息
    private Integer status;
    private LocalDateTime handleTime;
    private String handleNote;
    private String handleMan;
    
    // 物流信息（退货）
    private String returnDeliveryCompany;
    private String returnDeliveryNo;
    
    // 收货信息（商家）
    private String receiveMan;
    private LocalDateTime receiveTime;
    private String receiveNote;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}