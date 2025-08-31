package com.mall.api.order.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Refund status enumeration
 */
@Getter
@AllArgsConstructor
public enum RefundStatusEnum {
    PENDING(0, "Pending"),
    PROCESSING(1, "Processing"),
    APPROVED(2, "Approved"),
    REJECTED(3, "Rejected"),
    REFUNDED(4, "Refunded"),
    CANCELLED(5, "Cancelled"),
    CLOSED(6, "Closed");
    
    private final Integer code;
    private final String desc;
    
    public static RefundStatusEnum of(Integer code) {
        for (RefundStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
