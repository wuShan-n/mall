package com.mall.api.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Register source enumeration
 */
@Getter
@AllArgsConstructor
public enum RegisterSourceEnum {
    PC(1, "PC Website"),
    APP(2, "Mobile App"),
    MINI_PROGRAM(3, "Mini Program"),
    H5(4, "H5 Mobile Web"),
    ADMIN(5, "Admin Created");
    
    private final Integer code;
    private final String desc;
    
    public static RegisterSourceEnum of(Integer code) {
        for (RegisterSourceEnum source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }
}