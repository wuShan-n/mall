package com.mall.common.annotation;


import java.lang.annotation.*;

/**
 * Cache evict by pattern annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEvictByPattern {

    /**
     * Cache key pattern (supports wildcard *)
     */
    String pattern();

    /**
     * Whether to execute before method
     */
    boolean beforeInvocation() default false;
}
