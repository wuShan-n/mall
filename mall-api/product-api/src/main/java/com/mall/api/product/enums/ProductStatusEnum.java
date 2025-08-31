package com.mall.api.product.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Product status enumeration
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum {
    DOWN(0, "Off Shelf"),
    UP(1, "On Shelf");
    
    private final Integer code;
    private final String desc;
    
    public static ProductStatusEnum of(Integer code) {
        for (ProductStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
