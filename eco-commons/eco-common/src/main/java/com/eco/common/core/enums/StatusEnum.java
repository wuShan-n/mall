package com.eco.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态枚举
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {

    ENABLE(0, "启用"),
    DISABLE(1, "禁用");

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
