package com.mall.api.inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Warehouse type enumeration
 */
@Getter
@AllArgsConstructor
public enum WarehouseTypeEnum {
    MAIN(1, "Main Warehouse"),
    BRANCH(2, "Branch Warehouse"),
    TRANSIT(3, "Transit Warehouse"),
    VIRTUAL(4, "Virtual Warehouse");
    
    private final Integer code;
    private final String desc;
    
    public static WarehouseTypeEnum of(Integer code) {
        for (WarehouseTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}