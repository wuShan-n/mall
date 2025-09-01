package com.mall.api.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Order status enumeration
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    PENDING_PAYMENT(0, "Pending Payment"),
    PENDING_DELIVERY(1, "Pending Delivery"),
    DELIVERED(2, "Delivered"),
    RECEIVED(3, "Received"),
    COMPLETED(4, "Completed"),
    CANCELLED(5, "Cancelled"),
    REFUNDING(6, "Refunding"),
    REFUNDED(7, "Refunded"),
    CLOSED(8, "Closed");
    
    private final Integer code;
    private final String desc;
    
    public static OrderStatusEnum of(Integer code) {
        for (OrderStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
    
    public boolean canCancel() {
        return this == PENDING_PAYMENT || this == PENDING_DELIVERY;
    }
    
    public boolean canPay() {
        return this == PENDING_PAYMENT;
    }
    
    public boolean canShip() {
        return this == PENDING_DELIVERY;
    }
    
    public boolean canReceive() {
        return this == DELIVERED;
    }
    
    public boolean canRefund() {
        return this == PENDING_DELIVERY || this == DELIVERED || 
               this == RECEIVED || this == COMPLETED;
    }
}