package com.mall.api.user.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Point source enumeration
 */
@Getter
@AllArgsConstructor
public enum PointSourceEnum {
    REGISTER("REGISTER", "New user registration", 100),
    ORDER_COMPLETE("ORDER_COMPLETE", "Order completed", 0),
    DAILY_SIGN("DAILY_SIGN", "Daily sign in", 10),
    CONTINUOUS_SIGN("CONTINUOUS_SIGN", "Continuous sign in", 20),
    COMMENT("COMMENT", "Product comment", 5),
    SHARE("SHARE", "Share product", 5),
    INVITE("INVITE", "Invite friend", 50),
    BIRTHDAY("BIRTHDAY", "Birthday gift", 100),
    EXCHANGE("EXCHANGE", "Points exchange", 0),
    REFUND("REFUND", "Order refund", 0),
    ADMIN_ADJUST("ADMIN_ADJUST", "Admin adjustment", 0);
    
    private final String code;
    private final String desc;
    private final Integer defaultPoints;
    
    public static PointSourceEnum of(String code) {
        for (PointSourceEnum source : values()) {
            if (source.getCode().equals(code)) {
                return source;
            }
        }
        return null;
    }
}