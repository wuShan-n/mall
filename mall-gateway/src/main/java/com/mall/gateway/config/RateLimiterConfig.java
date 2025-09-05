package com.mall.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * 限流配置
 * 提供多种限流维度：IP限流、用户限流、接口限流
 */
@Configuration
public class RateLimiterConfig {

    /**
     * IP地址限流
     * 根据请求IP进行限流，防止单个IP恶意刷接口
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            String ip = exchange.getRequest().getRemoteAddress().getHostString();
            return Mono.just(ip);
        };
    }

    /**
     * 用户限流
     * 根据用户ID进行限流，防止单个用户过度请求
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");
            return Mono.just(userId != null ? userId : "anonymous");
        };
    }

    /**
     * 接口限流
     * 根据请求路径进行限流，保护特定接口
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> {
            String path = exchange.getRequest().getPath().value();
            return Mono.just(path);
        };
    }

    /**
     * 组合限流
     * 结合用户ID和接口路径，实现更细粒度的限流
     */
    @Bean
    public KeyResolver combinedKeyResolver() {
        return exchange -> {
            String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");
            String path = exchange.getRequest().getPath().value();
            String key = (userId != null ? userId : "anonymous") + ":" + path;
            return Mono.just(key);
        };
    }
}