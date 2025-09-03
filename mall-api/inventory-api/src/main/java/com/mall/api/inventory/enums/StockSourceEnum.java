package com.mall.api.inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Stock source enumeration
 */
@Getter
@AllArgsConstructor
public enum StockSourceEnum {
    PURCHASE("PURCHASE", "Purchase Order"),
    SALE("SALE", "Sales Order"),
    RETURN("RETURN", "Return Order"),
    REFUND("REFUND", "Refund"),
    TRANSFER("TRANSFER", "Transfer"),
    ADJUST("ADJUST", "Manual Adjustment"),
    DAMAGE("DAMAGE", "Damage/Loss"),
    INVENTORY("INVENTORY", "Inventory Check"),
    PRODUCTION("PRODUCTION", "Production"),
    SAMPLE("SAMPLE", "Sample");
    
    private final String code;
    private final String desc;
    
    public static StockSourceEnum of(String code) {
        for (StockSourceEnum source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }
}