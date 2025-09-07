package com.mall.order.domain.refund.valueobject;

import lombok.Getter;

/**
 * 退款类型枚举
 */
@Getter
public enum RefundType {
    
    REFUND_ONLY(1, "仅退款"),
    RETURN_AND_REFUND(2, "退货退款"),
    EXCHANGE(3, "换货");
    
    private final Integer code;
    private final String description;
    
    RefundType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static RefundType fromCode(Integer code) {
        for (RefundType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown refund type code: " + code);
    }
}