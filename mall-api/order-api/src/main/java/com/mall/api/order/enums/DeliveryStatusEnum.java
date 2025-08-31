package com.mall.api.order.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Delivery status enumeration
 */
@Getter
@AllArgsConstructor
public enum DeliveryStatusEnum {
    NOT_SHIPPED(0, "Not Shipped"),
    SHIPPED(1, "Shipped"),
    IN_TRANSIT(2, "In Transit"),
    DELIVERED(3, "Delivered"),
    SIGNED(4, "Signed"),
    REJECTED(5, "Rejected"),
    LOST(6, "Lost");
    
    private final Integer code;
    private final String desc;
    
    public static DeliveryStatusEnum of(Integer code) {
        for (DeliveryStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}