package com.eco.starter.interceptor;

import cn.hutool.core.util.StrUtil;
import com.eco.common.core.util.TraceUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 链路追踪拦截器
 */
@Component
public class TraceInterceptor implements HandlerInterceptor {

    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头获取traceId，如果没有则生成
        String traceId = request.getHeader(TRACE_ID_HEADER);
        if (StrUtil.isBlank(traceId)) {
            traceId = TraceUtil.generateTraceId();
        }

        // 设置到MDC
        TraceUtil.setTraceId(traceId);

        // 添加到响应头
        response.setHeader(TRACE_ID_HEADER, traceId);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        // 清除MDC
        TraceUtil.removeTraceId();
    }
}
