package com.mall.common.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Gender enumeration
 */
@Getter
@AllArgsConstructor
public enum GenderEnum {
    UNKNOWN(0, "Unknown"),
    MALE(1, "Male"),
    FEMALE(2, "Female");
    
    private final Integer code;
    private final String desc;
    
    public static GenderEnum of(Integer code) {
        for (GenderEnum gender : values()) {
            if (gender.getCode().equals(code)) {
                return gender;
            }
        }
        return UNKNOWN;
    }
}