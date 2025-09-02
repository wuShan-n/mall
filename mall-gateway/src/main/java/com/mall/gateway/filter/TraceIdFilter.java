package com.mall.gateway.filter;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * TraceId 全局过滤器
 */
@Slf4j
@Component
public class TraceIdFilter implements GlobalFilter, Ordered {

    private static final String TRACE_ID = "X-Trace-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 生成或获取TraceId
        String traceId = exchange.getRequest().getHeaders().getFirst(TRACE_ID);
        if (traceId == null) {
            traceId = IdUtil.fastSimpleUUID();
        }
        
        // 将TraceId添加到请求头
        ServerHttpRequest request = exchange.getRequest().mutate()
                .header(TRACE_ID, traceId)
                .build();
        
        // 将TraceId添加到响应头
        exchange.getResponse().getHeaders().add(TRACE_ID, traceId);
        
        // 保存到上下文
        exchange.getAttributes().put(TRACE_ID, traceId);
        
        log.debug("Request TraceId: {}", traceId);
        
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}