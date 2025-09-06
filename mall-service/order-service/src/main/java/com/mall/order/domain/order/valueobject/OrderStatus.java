package com.mall.order.domain.order.valueobject;

import lombok.Getter;

/**
 * 订单状态枚举
 */
@Getter
public enum OrderStatus {
    
    PENDING_PAYMENT(0, "待付款"),
    PENDING_DELIVERY(1, "待发货"),
    DELIVERED(2, "已发货"),
    RECEIVED(3, "已收货"),
    COMPLETED(4, "已完成"),
    CANCELLED(5, "已取消"),
    REFUNDING(6, "退款中"),
    REFUNDED(7, "已退款"),
    CLOSED(8, "已关闭");
    
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