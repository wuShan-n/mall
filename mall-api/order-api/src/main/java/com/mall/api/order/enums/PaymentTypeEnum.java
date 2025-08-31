package com.mall.api.order.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Payment type enumeration
 */
@Getter
@AllArgsConstructor
public enum PaymentTypeEnum {
    ALIPAY(1, "Alipay"),
    WECHAT(2, "WeChat Pay"),
    BALANCE(3, "Balance"),
    BANK_CARD(4, "Bank Card"),
    CREDIT_CARD(5, "Credit Card"),
    CASH_ON_DELIVERY(6, "Cash on Delivery");
    
    private final Integer code;
    private final String desc;
    
    public static PaymentTypeEnum of(Integer code) {
        for (PaymentTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}