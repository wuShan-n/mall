package com.mall.common.annotation;

import java.lang.annotation.*;

/**
 * Rate limiting annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * Rate limit key
     */
    String key() default "";

    /**
     * Time window (seconds)
     */
    int time() default 60;

    /**
     * Maximum requests in time window
     */
    int count() default 100;

    /**
     * Rate limit type
     */
    LimitType limitType() default LimitType.DEFAULT;

    enum LimitType {
        /**
         * Default rate limit by method
         */
        DEFAULT,

        /**
         * Rate limit by IP
         */
        IP,

        /**
         * Rate limit by user
         */
        USER
    }
}