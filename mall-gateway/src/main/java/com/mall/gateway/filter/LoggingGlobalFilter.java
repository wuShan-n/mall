package com.mall.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.mall.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * 统一日志记录全局过滤器
 *
 * 合并了 TraceId 生成、请求日志、响应日志和慢请求监控的功能。
 */
@Slf4j
@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_TIME_KEY = "startTime";
    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. TraceId 处理 (合并自 TraceIdFilter 和 RequestLogGlobalFilter)
        String traceId = exchange.getRequest().getHeaders().getFirst(TRACE_ID_HEADER);
        if (traceId == null) {
            traceId = IdUtil.fastSimpleUUID();
        }

        // 2. 将 TraceId 和请求开始时间放入 exchange 的属性中，供后续使用
        exchange.getAttributes().put(REQUEST_TIME_KEY, System.currentTimeMillis());
        exchange.getAttributes().put(TRACE_ID_HEADER, traceId);

        // 3. 构建新的请求，将 TraceId 添加到请求头中，传递给下游服务
        ServerHttpRequest newRequest = exchange.getRequest().mutate()
                .header(TRACE_ID_HEADER, traceId)
                .build();

        // 4. 将 TraceId 也添加到响应头中
        exchange.getResponse().getHeaders().add(TRACE_ID_HEADER, traceId);

        // 5. 记录详细请求日志 (合并自 RequestLogFilter)
        String method = newRequest.getMethod().name();
        String path = newRequest.getURI().getPath();
        String query = newRequest.getURI().getQuery();
        String clientIp = IpUtils.getClientIp(newRequest);

        // 获取登录用户信息（如果已登录）
        String userId = "anonymous";
        try {
            if (StpUtil.isLogin()) {
                userId = StpUtil.getLoginIdAsString();
            }
        } catch (Exception ignored) {
            // 忽略 Sa-Token 的所有异常，确保日志记录本身不会中断请求
        }

        log.info("===> Gateway Request: TraceId={}, UserId={}, Method={}, Path={}, Query={}, IP={}, Time={}",
                traceId, userId, method, path, query, clientIp, LocalDateTime.now());

        // 在 DEBUG 级别下记录请求头
        if (log.isDebugEnabled()) {
            HttpHeaders headers = newRequest.getHeaders();
            headers.forEach((key, value) -> log.debug("Request Header: {} = {}", key, value));
        }

        // 6. 执行过滤器链，并在完成后记录响应日志
        String finalTraceId = traceId;
        return chain.filter(exchange.mutate().request(newRequest).build())
                .then(Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_KEY);
                    if (startTime != null) {
                        long executeTime = System.currentTimeMillis() - startTime;
                        int statusCode = exchange.getResponse().getStatusCode() != null ?
                                exchange.getResponse().getStatusCode().value() : 0;

                        log.info("<=== Gateway Response: TraceId={}, Path={}, Status={}, Time={}ms",
                                finalTraceId, path, statusCode, executeTime);

                        // 慢请求告警
                        if (executeTime > 3000) {
                            log.warn("Slow Request Detected: TraceId={}, Path={}, Time={}ms",
                                    finalTraceId, path, executeTime);
                        }
                    }
                }));
    }

    @Override
    public int getOrder() {
        // 设置为最高优先级，确保 TraceId 最先生成并可用于所有后续过滤器
        return Ordered.HIGHEST_PRECEDENCE;
    }
}