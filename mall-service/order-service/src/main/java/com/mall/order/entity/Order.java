package com.mall.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 */
@Data
@TableName("oms_order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String orderNo;
    private Long userId;
    private String username;
    
    // 金额信息
    private BigDecimal totalAmount;
    private BigDecimal productAmount;
    private BigDecimal freightAmount;
    private BigDecimal discountAmount;
    private BigDecimal couponDiscount;
    private BigDecimal pointsDiscount;
    private BigDecimal promotionDiscount;
    private BigDecimal payAmount;
    
    // 支付信息
    private Integer paymentType;
    private LocalDateTime paymentTime;
    private String paymentNo;
    
    // 状态信息
    private Integer status;
    private Integer sourceType;
    private Integer confirmStatus;
    private Integer deleteStatus;
    
    // 物流信息
    private String deliveryCompany;
    private String deliveryNo;
    private LocalDateTime deliveryTime;
    private LocalDateTime receiveTime;
    
    // 收货人信息
    private String receiverName;
    private String receiverPhone;
    private String receiverProvince;
    private String receiverCity;
    private String receiverDistrict;
    private String receiverAddress;
    private String receiverPostalCode;
    
    // 其他信息
    private String remark;
    private Long couponId;
    private Integer usePoints;
    private String promotionInfo;
    private Integer invoiceType;
    private String invoiceHeader;
    private String invoiceContent;
    private String invoiceReceiverPhone;
    private String invoiceReceiverEmail;
    
    // 时间信息
    private Integer autoConfirmDay;
    private LocalDateTime expireTime;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
