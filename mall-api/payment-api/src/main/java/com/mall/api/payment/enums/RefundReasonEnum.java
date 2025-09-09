package com.mall.api.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Refund reason enumeration
 */
@Getter
@AllArgsConstructor
public enum RefundReasonEnum {
    QUALITY_ISSUE(1, "Product Quality Issue"),
    NOT_AS_DESCRIBED(2, "Not As Described"),
    WRONG_ITEM(3, "Wrong Item Sent"),
    DAMAGED(4, "Product Damaged"),
    NOT_RECEIVED(5, "Not Received"),
    CANCEL_ORDER(6, "Order Cancelled"),
    DUPLICATE_PAYMENT(7, "Duplicate Payment"),
    OTHER(99, "Other Reason");
    
    private final Integer code;
    private final String desc;
    
    public static RefundReasonEnum of(Integer code) {
        for (RefundReasonEnum reason : values()) {
            if (reason.getCode().equals(code)) {
                return reason;
            }
        }
        return OTHER;
    }
}