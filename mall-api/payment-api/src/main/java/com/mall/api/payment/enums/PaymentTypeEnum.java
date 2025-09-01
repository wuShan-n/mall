package com.mall.api.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Payment type enumeration
 */
@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {
    ALIPAY(1, "Alipay", "alipay"),
    WECHAT(2, "WeChat Pay", "wechat"),
    UNIONPAY(3, "UnionPay", "unionpay"),
    BALANCE(4, "Balance", "balance"),
    BANK_TRANSFER(5, "Bank Transfer", "bank"),
    CREDIT_CARD(6, "Credit Card", "credit"),
    PAYPAL(7, "PayPal", "paypal"),
    APPLE_PAY(8, "Apple Pay", "apple");
    
    private final Integer code;
    private final String desc;
    private final String channel;
    
    public static PaymentTypeEnum of(Integer code) {
        for (PaymentTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
    
    public static PaymentTypeEnum ofChannel(String channel) {
        for (PaymentTypeEnum type : values()) {
            if (type.getChannel().equals(channel)) {
                return type;
            }
        }
        return null;
    }
}
