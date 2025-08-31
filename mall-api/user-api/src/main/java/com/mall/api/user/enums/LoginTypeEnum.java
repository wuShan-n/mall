package com.mall.api.user.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Login type enumeration
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    PASSWORD(1, "Password"),
    SMS_CODE(2, "SMS Code"),
    WECHAT(3, "WeChat"),
    ALIPAY(4, "Alipay"),
    QQ(5, "QQ");
    
    private final Integer code;
    private final String desc;
    
    public static LoginTypeEnum of(Integer code) {
        for (LoginTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}