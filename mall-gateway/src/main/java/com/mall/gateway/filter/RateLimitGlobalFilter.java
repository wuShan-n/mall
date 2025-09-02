//package com.mall.gateway.filter;
//
//import com.mall.common.result.Result;
//import com.mall.common.result.ResultCode;
//import com.mall.gateway.utils.IpUtils;
//import com.mall.gateway.utils.ResponseUtils;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.core.script.DefaultRedisScript;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 限流全局过滤器
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class RateLimitGlobalFilter implements GlobalFilter, Ordered {
//
//    private final ReactiveRedisTemplate<String, String> redisTemplate;
//
//    private static final String RATE_LIMIT_KEY_PREFIX = "rate_limit:";
//    private static final int DEFAULT_LIMIT = 100; // 默认每分钟100次
//    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(1);
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String path = exchange.getRequest().getURI().getPath();
//        String clientIp = IpUtils.getClientIp(exchange.getRequest());
//        String key = RATE_LIMIT_KEY_PREFIX + clientIp + ":" + path;
//
//        // 使用Lua脚本实现原子性的限流判断
//        String luaScript = """
//                local key = KEYS[1]
//                local limit = tonumber(ARGV[1])
//                local duration = tonumber(ARGV[2])
//                local current = redis.call('GET', key)
//                if current == false then
//                    redis.call('SET', key, 1)
//                    redis.call('EXPIRE', key, duration)
//                    return 1
//                elseif tonumber(current) < limit then
//                    return redis.call('INCR', key)
//                else
//                    return -1
//                end
//                """;
//
//        DefaultRedisScript<Long> script = new DefaultRedisScript<>(luaScript, Long.class);
//        List<String> keys = Arrays.asList(key);
//
//        return redisTemplate.execute(script, keys, String.valueOf(DEFAULT_LIMIT), String.valueOf(DEFAULT_DURATION.getSeconds()))
//                .flatMap(result -> {
//                    if (result != null && result > 0) {
//                        // 未超过限流阈值
//                        return chain.filter(exchange);
//                    } else {
//                        // 超过限流阈值
//                        log.warn("Rate limit exceeded for IP: {}, Path: {}", clientIp, path);
//                        return ResponseUtils.writeErrorResponse(exchange.getResponse(),
//                                Result.failed(ResultCode.TOO_MANY_REQUESTS, "请求过于频繁，请稍后再试"));
//                    }
//                })
//                .switchIfEmpty(chain.filter(exchange));
//    }
//
//    @Override
//    public int getOrder() {
//        return -50;
//    }
//}