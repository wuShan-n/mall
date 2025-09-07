package com.mall.order.domain.order.valueobject;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderStatus {
    
    PENDING_PAYMENT(0, "待付款"),//用户创建订单后，用户付款前
    PENDING_DELIVERY(1, "待发货"),//用户付款后，商家发货前
    DELIVERED(2, "已发货"), //商家发货后，用户收货前
    RECEIVED(3, "已收货"),//用户确认收货
    COMPLETED(4, "已完成"),//用户收货无异议，订单结束
    CANCELLED(5, "已取消"),//用户取消订单，等待商家处理
    REFUNDING(6, "退款中"),//商家同意退款，退款中
    REFUNDED(7, "已退款"),//商家退款成功
    CLOSED(8, "已关闭");//退款结束
    
    private final Integer code;
    private final String description;
    
    OrderStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    /**
     * 是否可以支付
     */
    public boolean canPay() {
        return this == PENDING_PAYMENT;
    }
    
    /**
     * 是否可以取消
     */
    public boolean canCancel() {
        return this == PENDING_PAYMENT || this == PENDING_DELIVERY;
    }
    
    /**
     * 是否可以发货
     */
    public boolean canShip() {
        return this == PENDING_DELIVERY;
    }
    
    /**
     * 是否可以退款
     */
    public boolean canRefund() {
        return this == PENDING_DELIVERY || this == DELIVERED || 
               this == RECEIVED || this == COMPLETED;
    }
    
    public static OrderStatus fromCode(Integer code) {
        for (OrderStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order status code: " + code);
    }
}