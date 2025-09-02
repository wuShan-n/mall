package com.mall.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import com.mall.gateway.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sentinel限流异常处理器
 */
@Slf4j
@Component
public class GatewayBlockExceptionHandler implements BlockRequestHandler {

    @Override
    public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable ex) {
        Result<Void> result;
        
        if (ex instanceof FlowException) {
            log.warn("Flow control triggered for path: {}", exchange.getRequest().getPath());
            result = Result.failed(ResultCode.TOO_MANY_REQUESTS, "系统繁忙，请稍后再试");
        } else if (ex instanceof DegradeException) {
            log.warn("Service degraded for path: {}", exchange.getRequest().getPath());
            result = Result.failed(ResultCode.SERVICE_UNAVAILABLE, "服务降级中，请稍后再试");
        } else if (ex instanceof ParamFlowException) {
            log.warn("Param flow control triggered for path: {}", exchange.getRequest().getPath());
            result = Result.failed(ResultCode.TOO_MANY_REQUESTS, "请求过于频繁");
        } else if (ex instanceof SystemBlockException) {
            log.error("System protection triggered");
            result = Result.failed(ResultCode.SERVICE_UNAVAILABLE, "系统保护中");
        } else if (ex instanceof AuthorityException) {
            log.warn("Authority check failed for path: {}", exchange.getRequest().getPath());
            result = Result.failed(ResultCode.FORBIDDEN, "无访问权限");
        } else {
            log.error("Unknown block exception", ex);
            result = Result.failed(ResultCode.FAILED, "系统异常");
        }
        
        return ResponseUtils.writeErrorResponse(exchange.getResponse(), result)
                .then(Mono.empty());
    }
}