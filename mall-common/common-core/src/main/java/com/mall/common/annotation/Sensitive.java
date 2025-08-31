package com.mall.common.annotation;

import java.lang.annotation.*;

/**
 * Sensitive data masking annotation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {
    
    /**
     * Sensitive type
     */
    SensitiveType type();
    
    enum SensitiveType {
        /**
         * Mobile phone
         */
        MOBILE,
        
        /**
         * Email
         */
        EMAIL,
        
        /**
         * ID card
         */
        ID_CARD,
        
        /**
         * Bank card
         */
        BANK_CARD,
        
        /**
         * Password
         */
        PASSWORD,
        
        /**
         * Name
         */
        NAME,
        
        /**
         * Address
         */
        ADDRESS
    }
}