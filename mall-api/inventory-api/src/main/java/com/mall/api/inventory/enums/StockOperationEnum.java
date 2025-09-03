package com.mall.api.inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Stock operation enumeration
 */
@Getter
@AllArgsConstructor
public enum StockOperationEnum {
    ADD(1, "Add Stock"),
    DEDUCT(2, "Deduct Stock"),
    LOCK(3, "Lock Stock"),
    UNLOCK(4, "Unlock Stock"),
    TRANSFER_IN(5, "Transfer In"),
    TRANSFER_OUT(6, "Transfer Out"),
    ADJUST_INCREASE(7, "Adjust Increase"),
    ADJUST_DECREASE(8, "Adjust Decrease");
    
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