package com.mall.common.constant;

/**
 * Cache key constants
 */
public interface CacheConstants {

    /**
     * Cache key prefix
     */
    String CACHE_PREFIX = "mall:";

    /**
     * User cache prefix
     */
    String USER_CACHE_PREFIX = CACHE_PREFIX + "user:";

    /**
     * Product cache prefix
     */
    String PRODUCT_CACHE_PREFIX = CACHE_PREFIX + "product:";

    /**
     * Order cache prefix
     */
    String ORDER_CACHE_PREFIX = CACHE_PREFIX + "order:";

    /**
     * Token cache prefix
     */
    String TOKEN_CACHE_PREFIX = CACHE_PREFIX + "token:";

    /**
     * Captcha cache prefix
     */
    String CAPTCHA_CACHE_PREFIX = CACHE_PREFIX + "captcha:";

    /**
     * Default cache duration (seconds)
     */
    long DEFAULT_EXPIRE = 3600L;

    /**
     * Token expiration time (seconds)
     */
    long TOKEN_EXPIRE = 7200L;

    /**
     * Captcha expiration time (seconds)
     */
    long CAPTCHA_EXPIRE = 300L;
}