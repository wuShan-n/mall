package com.mall.api.payment.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Transaction type enumeration
 */
@Getter
@AllArgsConstructor
public enum TransactionTypeEnum {
    PAYMENT(1, "Payment"),
    REFUND(2, "Refund"),
    TRANSFER(3, "Transfer"),
    RECHARGE(4, "Recharge"),
    WITHDRAW(5, "Withdraw"),
    FREEZE(6, "Freeze"),
    UNFREEZE(7, "Unfreeze");
    
    private final Integer code;
    private final String desc;
    
    public static TransactionTypeEnum of(Integer code) {
        for (TransactionTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}