package com.mall.order.domain.order.valueobject;

import lombok.Getter;

/**
 * 支付类型枚举
 */
@Getter
public enum PaymentType {
    
    ALIPAY(1, "支付宝"),
    WECHAT(2, "微信支付"),
    BALANCE(3, "余额支付"),
    BANK_CARD(4, "银行卡"),
    CREDIT_CARD(5, "信用卡"),
    CASH_ON_DELIVERY(6, "货到付款");
    
    private final Integer code;
    private final String description;
    
    PaymentType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static PaymentType fromCode(Integer code) {
        for (PaymentType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown payment type code: " + code);
    }
}