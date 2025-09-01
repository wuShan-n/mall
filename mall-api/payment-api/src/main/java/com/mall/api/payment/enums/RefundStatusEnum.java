package com.mall.api.payment.enums;

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
    SUCCESS(2, "Refunded"),
    FAILED(3, "Refund Failed"),
    CANCELLED(4, "Cancelled");
    
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
