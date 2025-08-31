package com.mall.api.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Payment status enumeration
 */
@Getter
@AllArgsConstructor
public enum PaymentStatusEnum {
    PENDING(0, "Pending Payment"),
    PAID(1, "Paid"),
    FAILED(2, "Payment Failed"),
    CANCELLED(3, "Cancelled"),
    EXPIRED(4, "Expired"),
    REFUNDED(5, "Refunded"),
    PARTIAL_REFUNDED(6, "Partial Refunded");
    
    private final Integer code;
    private final String desc;
    
    public static PaymentStatusEnum of(Integer code) {
        for (PaymentStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}