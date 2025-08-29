package com.eco.starter.aspect;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.eco.common.core.util.JsonUtil;
import com.eco.common.core.util.TraceUtil;
import com.eco.starter.config.EcoProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志切面
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "eco.log", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LogAspect {

    private final EcoProperties ecoProperties;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || " +
              "@within(org.springframework.stereotype.Controller)")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        String requestUri = request.getRequestURI();

        // 检查是否忽略该URL
        List<String> ignoreUrls = ecoProperties.getLog().getIgnoreUrls();
        if (CollUtil.isNotEmpty(ignoreUrls)) {
            for (String ignoreUrl : ignoreUrls) {
                if (requestUri.contains(ignoreUrl)) {
                    return joinPoint.proceed();
                }
            }
        }

        // 记录请求信息
        long startTime = System.currentTimeMillis();
        String traceId = TraceUtil.getTraceId();

        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        String params = null;
        if (ecoProperties.getLog().isPrintParams() && ArrayUtil.isNotEmpty(args)) {
            params = Arrays.stream(args)
                .filter(arg -> !(arg instanceof HttpServletRequest)
                    && !(arg instanceof HttpServletResponse)
                    && !(arg instanceof MultipartFile))
                .map(JsonUtil::toJson)
                .collect(Collectors.joining(", "));
        }

        log.info("========== Request Start ==========");
        log.info("TraceId: {}", traceId);
        log.info("URL: {} {}", request.getMethod(), requestUri);
        log.info("Class Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName());
        log.info("IP: {}", getIpAddress(request));
        if (StrUtil.isNotBlank(params)) {
            log.info("Request Args: {}", params);
        }

        Object result = null;
        try {
            // 执行方法
            result = joinPoint.proceed();
            return result;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;

            // 打印响应结果
            if (ecoProperties.getLog().isPrintResult() && result != null) {
                log.info("Response: {}", JsonUtil.toJson(result));
            }

            // 慢请求告警
            if (costTime > ecoProperties.getLog().getSlowThreshold()) {
                log.warn("Slow Request: {}ms", costTime);
            }

            log.info("Time: {}ms", costTime);
            log.info("========== Request End ==========");
        }
    }

    /**
     * 获取IP地址
     */
    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多个IP时取第一个
        if (StrUtil.isNotBlank(ip) && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
