package com.mall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 访问日志过滤器
 * 记录所有请求的详细信息，便于问题排查和分析
 */
@Slf4j
@Component
public class AccessLogFilter implements GlobalFilter, Ordered {
    
    private static final String REQUEST_TIME_KEY = "requestTimeBegin";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // 记录请求开始时间
        exchange.getAttributes().put(REQUEST_TIME_KEY, System.currentTimeMillis());
        
        // 获取请求信息
        String path = request.getPath().value();
        String method = request.getMethod().name();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        String clientIp = getClientIp(request);
        
        // 记录请求日志
        log.info("请求开始 ==> Method: {}, Path: {}, IP: {}, Query: {}, Time: {}", 
                method, path, clientIp, queryParams, 
                LocalDateTime.now().format(DATE_TIME_FORMATTER));
        
        // 获取路由后的实际URI
        URI targetUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        
        return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
                Long startTime = exchange.getAttribute(REQUEST_TIME_KEY);
                if (startTime != null) {
                    // 计算执行时间
                    long executeTime = System.currentTimeMillis() - startTime;
                    
                    // 获取响应状态
                    ServerHttpResponse response = exchange.getResponse();
                    HttpStatus statusCode = (HttpStatus) response.getStatusCode();
                    
                    // 记录响应日志
                    log.info("请求结束 <== Method: {}, Path: {}, Status: {}, Time: {}ms, Target: {}", 
                            method, path, 
                            statusCode != null ? statusCode.value() : "unknown",
                            executeTime,
                            targetUri != null ? targetUri.toString() : "unknown");
                    
                    // 慢请求警告（超过3秒）
                    if (executeTime > 3000) {
                        log.warn("慢请求警告: Path: {}, Time: {}ms", path, executeTime);
                    }
                }
            })
        );
    }
    
    /**
     * 获取客户端真实IP
     */
    private String getClientIp(ServerHttpRequest request) {
        // 尝试获取真实IP（考虑代理的情况）
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            if (remoteAddress != null) {
                ip = remoteAddress.getHostString();
            }
        }
        
        // 处理多个IP的情况（取第一个）
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        
        return ip;
    }
    
    @Override
    public int getOrder() {
        return -99;
    }
}