package com.mall.gateway.filter;

import com.mall.gateway.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 请求日志全局过滤器
 */
@Slf4j
@Component
public class RequestLogGlobalFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_TIME_KEY = "requestTime";
    private static final String TRACE_ID_KEY = "traceId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // 生成traceId
        String traceId = UUID.randomUUID().toString().replace("-", "");
        
        // 记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_KEY, System.currentTimeMillis());
        exchange.getAttributes().put(TRACE_ID_KEY, traceId);
        
        // 获取请求信息
        String method = request.getMethod().name();
        String path = request.getURI().getPath();
        String query = request.getURI().getQuery();
        String clientIp = IpUtils.getClientIp(request);
        
        log.info("Gateway Request Start - TraceId: {}, Method: {}, Path: {}, Query: {}, ClientIP: {}, Time: {}",
                traceId, method, path, query, clientIp, LocalDateTime.now());
        
        // 添加traceId到响应头
        ServerHttpRequest newRequest = request.mutate()
                .header("X-Trace-Id", traceId)
                .build();
        
        return chain.filter(exchange.mutate().request(newRequest).build())
                .then(Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_KEY);
                    if (startTime != null) {
                        long executeTime = System.currentTimeMillis() - startTime;
                        log.info("Gateway Request End - TraceId: {}, Path: {}, ExecuteTime: {}ms",
                                traceId, path, executeTime);
                    }
                }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}