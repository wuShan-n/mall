package com.mall.order.domain.order.valueobject;

import lombok.Getter;

@Getter
public enum OrderStatus {

    INIT(0, "初始化"),                    // 新增：初始状态
    PENDING_STOCK(1, "待锁定库存"),        // 新增：等待库存锁定
    STOCK_LOCKED(2, "库存已锁定"),         // 新增：库存锁定成功
    PENDING_PAYMENT(3, "待付款"),          // 原来的待付款
    PAID(4, "已付款"),                    // 新增：已付款状态
    PENDING_DELIVERY(5, "待发货"),
    DELIVERED(6, "已发货"),
    RECEIVED(7, "已收货"),
    COMPLETED(8, "已完成"),
    CANCELLED(9, "已取消"),
    STOCK_LOCK_FAILED(10, "库存锁定失败"), // 新增：库存锁定失败
    REFUNDING(11, "退款中"),
    REFUNDED(12, "已退款"),
    CLOSED(13, "已关闭");

    private final Integer code;
    private final String description;

    OrderStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public boolean canPay() {
        return this == STOCK_LOCKED || this == PENDING_PAYMENT;
    }

    public boolean canCancel() {
        return this == INIT || this == PENDING_STOCK ||
                this == STOCK_LOCKED || this == PENDING_PAYMENT;
    }

    public boolean canLockStock() {
        return this == INIT || this == PENDING_STOCK;
    }

    public boolean canShip() {
        return this == PAID || this == PENDING_DELIVERY;
    }

    public boolean canReceive() {
        return this == DELIVERED;
    }

    public boolean canRefund() {
        return this == PENDING_DELIVERY || this == DELIVERED || this == RECEIVED;
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