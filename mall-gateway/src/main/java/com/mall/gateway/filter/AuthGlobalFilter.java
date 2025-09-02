package com.mall.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import com.mall.gateway.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 认证全局过滤器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final ReactiveRedisTemplate<String, Object> redisTemplate;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 白名单路径
     */
    private static final List<String> WHITE_LIST = List.of(
            "/*/login",
            "/*/register",
            "/*/doc.html",
            "/*/swagger-ui.html",
            "/*/swagger-resources/**",
            "/*/v3/api-docs/**",
            "/*/webjars/**",
            "/actuator/**",
            "/favicon.ico"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 检查是否在白名单中
        if (isWhiteList(path)) {
            return chain.filter(exchange);
        }

        // 获取token
        String token = getToken(request);
        if (StrUtil.isBlank(token)) {
            log.warn("Token is missing for path: {}", path);
            return ResponseUtils.writeErrorResponse(exchange.getResponse(), 
                    Result.failed(ResultCode.UNAUTHORIZED));
        }

        // 验证token
        return validateToken(token)
                .flatMap(valid -> {
                    if (Boolean.TRUE.equals(valid)) {
                        // Token有效，添加用户信息到header
                        return getUserInfo(token)
                                .flatMap(userInfo -> {
                                    ServerHttpRequest newRequest = request.mutate()
                                            .header("user-id", String.valueOf(userInfo))
                                            .build();
                                    return chain.filter(exchange.mutate().request(newRequest).build());
                                });
                    } else {
                        log.warn("Invalid token: {}", token);
                        return ResponseUtils.writeErrorResponse(exchange.getResponse(),
                                Result.failed(ResultCode.USER_TOKEN_INVALID));
                    }
                });
    }

    private boolean isWhiteList(String path) {
        return WHITE_LIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotBlank(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    private Mono<Boolean> validateToken(String token) {
        String key = "mall:token:" + token;
        return redisTemplate.hasKey(key);
    }

    private Mono<Object> getUserInfo(String token) {
        String key = "mall:token:" + token;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}