package com.mall.gateway.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.result.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 响应工具类
 */
public class ResponseUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 写入错误响应
     */
    public static Mono<Void> writeErrorResponse(ServerHttpResponse response, Result<?> result) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        try {
            String json = objectMapper.writeValueAsString(result);
            DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return response.setComplete();
        }
    }

    /**
     * 写入成功响应
     */
    public static Mono<Void> writeSuccessResponse(ServerHttpResponse response, Object data) {
        Result<Object> result = Result.success(data);
        return writeErrorResponse(response, result);
    }
}