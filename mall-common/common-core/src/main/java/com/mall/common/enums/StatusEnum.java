package com.mall.common.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Common status enumeration
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    ENABLE(0, "Enable"),
    DISABLE(1, "Disable");
    
    private final Integer code;
    private final String desc;
    
    public static StatusEnum of(Integer code) {
        for (StatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}