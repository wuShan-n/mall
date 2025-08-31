package com.mall.api.user.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * User status enumeration
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    NORMAL(0, "Normal"),
    DISABLED(1, "Disabled"),
    LOCKED(2, "Locked"),
    EXPIRED(3, "Expired");
    
    private final Integer code;
    private final String desc;
    
    public static UserStatusEnum of(Integer code) {
        for (UserStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}