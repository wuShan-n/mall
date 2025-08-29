package com.eco.common.core.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

/**
 * 链路追踪工具类
 */
public class TraceUtil {

    private static final String TRACE_ID = "traceId";

    /**
     * 获取TraceId
     */
    public static String getTraceId() {
        String traceId = MDC.get(TRACE_ID);
        if (StrUtil.isBlank(traceId)) {
            traceId = generateTraceId();
            setTraceId(traceId);
        }
        return traceId;
    }

    /**
     * 设置TraceId
     */
    public static void setTraceId(String traceId) {
        if (StrUtil.isNotBlank(traceId)) {
            MDC.put(TRACE_ID, traceId);
        }
    }

    /**
     * 生成TraceId
     */
    public static String generateTraceId() {
        return IdUtil.nanoId(16);
    }

    /**
     * 清除TraceId
     */
    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }
}
