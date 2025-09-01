package com.mall.api.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Order source enumeration
 */
@Getter
@AllArgsConstructor
public enum OrderSourceEnum {
    PC(1, "PC Website"),
    APP(2, "Mobile App"),
    MINI_PROGRAM(3, "Mini Program"),
    H5(4, "H5 Mobile Web");
    
    private final Integer code;
    private final String desc;
    
    public static OrderSourceEnum of(Integer code) {
        for (OrderSourceEnum source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }
}