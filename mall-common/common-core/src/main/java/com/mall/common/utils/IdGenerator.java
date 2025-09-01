package com.mall.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * ID generator utility
 */
public class IdGenerator {
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    private IdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate unique ID
     */
    public static long nextId() {
        return snowflake.nextId();
    }

    /**
     * Generate unique ID string
     */
    public static String nextIdStr() {
        return String.valueOf(nextId());
    }

    /**
     * Generate UUID
     */
    public static String uuid() {
        return IdUtil.randomUUID();
    }

    /**
     * Generate simple UUID (without hyphen)
     */
    public static String simpleUuid() {
        return IdUtil.simpleUUID();
    }
}