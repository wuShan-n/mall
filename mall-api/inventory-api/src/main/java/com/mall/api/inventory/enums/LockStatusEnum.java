package com.mall.api.inventory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Lock status enumeration
 */
@Getter
@AllArgsConstructor
public enum LockStatusEnum {
    LOCKED(1, "Locked"),
    RELEASED(2, "Released"),
    DEDUCTED(3, "Deducted"),
    EXPIRED(4, "Expired");
    
    private final Integer code;
    private final String desc;
    
    public static LockStatusEnum of(Integer code) {
        for (LockStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}