package com.mall.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
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
 * 请求日志过滤器
 */
@Slf4j
@Component
public class RequestLogFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_TIME_KEY = "requestTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // 记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_KEY, System.currentTimeMillis());
        
        // 获取请求信息
        String traceId = exchange.getAttribute("X-Trace-Id");
        String method = request.getMethod().name();
        String path = request.getURI().getPath();
        String query = request.getURI().getQuery();
        String clientIp = IpUtils.getClientIp(request);
        HttpHeaders headers = request.getHeaders();
        
        // 获取登录用户信息（如果已登录）
        String userId = "anonymous";
        try {
            if (StpUtil.isLogin()) {
                userId = StpUtil.getLoginIdAsString();
            }
        } catch (Exception ignored) {
        }
        
        log.info("===> Gateway Request: TraceId={}, UserId={}, Method={}, Path={}, Query={}, IP={}, Time={}",
                traceId, userId, method, path, query, clientIp, LocalDateTime.now());
        
        // 记录请求头（开发环境）
        if (log.isDebugEnabled()) {
            headers.forEach((key, value) -> 
                log.debug("Request Header: {} = {}", key, value)
            );
        }
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(REQUEST_TIME_KEY);
            if (startTime != null) {
                long executeTime = System.currentTimeMillis() - startTime;
                int statusCode = exchange.getResponse().getStatusCode() != null ? 
                        exchange.getResponse().getStatusCode().value() : 0;
                
                log.info("<=== Gateway Response: TraceId={}, Path={}, Status={}, Time={}ms",
                        traceId, path, statusCode, executeTime);
                
                // 慢请求告警
                if (executeTime > 3000) {
                    log.warn("Slow Request Detected: TraceId={}, Path={}, Time={}ms", 
                            traceId, path, executeTime);
                }
            }
        }));
    }

    @Override
    public int getOrder() {
        return -99;
    }
}