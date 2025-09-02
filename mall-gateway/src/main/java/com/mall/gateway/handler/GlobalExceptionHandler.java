package com.mall.gateway.handler;

import cn.dev33.satoken.exception.*;
import cn.dev33.satoken.util.SaResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局异常处理器
 */
@Slf4j
@Order(-1)
@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        
        // 设置响应头
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        
        // 处理异常
        SaResult result = handleException(ex);
        
        // 设置状态码
        if (ex instanceof NotLoginException) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof NotRoleException || ex instanceof NotPermissionException) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
        } else if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatusCode());
        } else {
            response.setStatusCode(HttpStatus.OK);
        }
        
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBuffer buffer = null;
            try {
                buffer = response.bufferFactory().wrap(
                    objectMapper.writeValueAsBytes(result)
                );
            } catch (JsonProcessingException e) {
                log.error("Error writing response", e);
                buffer = response.bufferFactory().wrap(new byte[0]);
            }
            return buffer;
        }));
    }

    private SaResult handleException(Throwable ex) {
        String path = "";
        
        // Sa-Token 异常
        if (ex instanceof NotLoginException) {
            NotLoginException e = (NotLoginException) ex;
            log.warn("Not Login: type={}, message={}", e.getType(), e.getMessage());
            
            String message = switch (e.getType()) {
                case NotLoginException.NOT_TOKEN -> "未提供Token";
                case NotLoginException.INVALID_TOKEN -> "Token无效";
                case NotLoginException.TOKEN_TIMEOUT -> "Token已过期";
                case NotLoginException.BE_REPLACED -> "Token已被顶下线";
                case NotLoginException.KICK_OUT -> "Token已被踢下线";
                default -> "当前会话未登录";
            };
            
            return SaResult.error(message).setCode(401);
            
        } else if (ex instanceof NotRoleException) {
            NotRoleException e = (NotRoleException) ex;
            log.warn("No Role: {}", e.getMessage());
            return SaResult.error("无此角色：" + e.getRole()).setCode(403);
            
        } else if (ex instanceof NotPermissionException) {
            NotPermissionException e = (NotPermissionException) ex;
            log.warn("No Permission: {}", e.getMessage());
            return SaResult.error("无此权限：" + e.getPermission()).setCode(403);
            
        } else if (ex instanceof DisableServiceException) {
            DisableServiceException e = (DisableServiceException) ex;
            log.warn("Service Disabled: {}", e.getMessage());
            return SaResult.error("账号已被封禁：" + e.getDisableTime() + "秒后解封").setCode(403);
            
        } else if (ex instanceof SaTokenException) {
            SaTokenException e = (SaTokenException) ex;
            log.error("SaToken Error: {}", e.getMessage());
            return SaResult.error(e.getMessage()).setCode(500);
            
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException e = (ResponseStatusException) ex;
            log.error("Response Status Error: {}", e.getMessage());
            return SaResult.error(e.getReason()).setCode(e.getStatusCode().value());
            
        } else {
            log.error("Unknown Error: ", ex);
            return SaResult.error("系统异常，请稍后重试").setCode(500);
        }
    }
}