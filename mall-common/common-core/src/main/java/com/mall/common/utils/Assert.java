package com.mall.common.utils;

import com.mall.common.exception.BusinessException;
import com.mall.common.result.ResultCode;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Map;

/**
 * Assertion utility class
 */
public class Assert {
    
    private Assert() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Assert not null
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert not null with ResultCode
     */
    public static void notNull(Object object, ResultCode resultCode) {
        if (object == null) {
            throw new BusinessException(resultCode);
        }
    }
    
    /**
     * Assert not empty string
     */
    public static void notEmpty(String str, String message) {
        if (StrUtil.isEmpty(str)) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert not empty collection
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollUtil.isEmpty(collection)) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert not empty map
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (MapUtil.isEmpty(map)) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert true
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert false
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BusinessException(message);
        }
    }
    
    /**
     * Assert equals
     */
    public static void equals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new BusinessException(message);
        }
    }
}