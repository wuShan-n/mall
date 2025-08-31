package com.mall.common.annotation;

import java.lang.annotation.*;

/**
 * Prevent repeat submit annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {
    
    /**
     * Lock time (milliseconds)
     */
    int lockTime() default 5000;
    
    /**
     * Lock key prefix
     */
    String keyPrefix() default "repeat_submit:";
    
    /**
     * Error message
     */
    String message() default "Please do not submit repeatedly";
}