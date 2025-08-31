package com.mall.api.product.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Audit status enumeration
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    PENDING(0, "Pending"),
    APPROVED(1, "Approved"),
    REJECTED(2, "Rejected");
    
    private final Integer code;
    private final String desc;
    
    public static AuditStatusEnum of(Integer code) {
        for (AuditStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }
}
