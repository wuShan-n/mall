package com.mall.gateway.config;

import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

/**
 * Gateway Configuration
 */
@Configuration
@EnableCaching
public class GatewayConfig {

    /**
     * 配置路由规则
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 用户服务路由
                .route("user-service", r -> r
                        .path("/api/user/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken())
                        )
                        .uri("lb://user-service")
                )
                // 商品服务路由
                .route("product-service", r -> r
                        .path("/api/product/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken())
                        )
                        .uri("lb://product-service")
                )
                // 订单服务路由
                .route("order-service", r -> r
                        .path("/api/order/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken())
                                .retry(config -> config.setRetries(3))
                        )
                        .uri("lb://order-service")
                )
                // 支付服务路由
                .route("payment-service", r -> r
                        .path("/api/payment/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .addRequestHeader(SaSameUtil.SAME_TOKEN, SaSameUtil.getToken())
                        )
                        .uri("lb://payment-service")
                )
                // 认证服务路由（不需要认证）
                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f
                                .stripPrefix(1)
                        )
                        .uri("lb://user-service")
                )
                .build();
    }

    /**
     * IP地址限流键解析器
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest().getRemoteAddress())
                        .getAddress().getHostAddress()
        );
    }

    /**
     * 用户限流键解析器（基于Sa-Token的登录ID）
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            try {
                String loginId = StpUtil.getLoginIdAsString();
                return Mono.just(loginId);
            } catch (Exception e) {
                return Mono.just("anonymous");
            }
        };
    }

    /**
     * 接口限流键解析器
     */
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }

    /**
     * 本地缓存配置
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(10))
                .maximumSize(1000));
        return cacheManager;
    }
}