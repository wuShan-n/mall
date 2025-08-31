package com.mall.api.product.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Stock operation enumeration
 */
@Getter
@AllArgsConstructor
public enum StockOperationEnum {
    ADD(1, "Add Stock"),
    DEDUCT(2, "Deduct Stock"),
    LOCK(3, "Lock Stock"),
    UNLOCK(4, "Unlock Stock");
    
    private final Integer code;
    private final String desc;
    
    public static StockOperationEnum of(Integer code) {
        for (StockOperationEnum operation : values()) {
            if (operation.getCode().equals(code)) {
                return operation;
            }
        }
        return null;
    }
}