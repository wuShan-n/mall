package com.mall.order.domain.refund.valueobject;

import lombok.Getter;

/**
 * 退款状态枚举
 */
@Getter
public enum RefundStatus {
    
    PENDING(0, "待处理"),
    PROCESSING(1, "处理中"),
    APPROVED(2, "已同意"),
    REJECTED(3, "已拒绝"),
    REFUNDED(4, "已退款"),
    CANCELLED(5, "已取消"),
    CLOSED(6, "已关闭");
    
    private final Integer code;
    private final String description;
    
    RefundStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public static RefundStatus fromCode(Integer code) {
        for (RefundStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown refund status code: " + code);
    }
}