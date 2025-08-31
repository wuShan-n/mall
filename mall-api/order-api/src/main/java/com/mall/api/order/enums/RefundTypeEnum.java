package com.mall.api.order.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Refund type enumeration
 */
@Getter
@AllArgsConstructor
public enum RefundTypeEnum {
    REFUND_ONLY(1, "Refund Only"),
    RETURN_AND_REFUND(2, "Return and Refund"),
    EXCHANGE(3, "Exchange");
    
    private final Integer code;
    private final String desc;
    
    public static RefundTypeEnum of(Integer code) {
        for (RefundTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}