package com.mall.common.security.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.mall.common.core.exception.BusinessException;
import com.mall.common.core.result.Result;
import com.mall.common.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author mall
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常：{}", e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("参数校验异常：{}", message);
        return Result.error(ResultCode.PARAM_VALID_ERROR.getCode(), message);
    }

    /**
     * 参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("参数绑定异常：{}", message);
        return Result.error(ResultCode.PARAM_VALID_ERROR.getCode(), message);
    }

    /**
     * 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<Void> handleNotLoginException(NotLoginException e) {
        String message;
        if (NotLoginException.NOT_TOKEN.equals(e.getType())) {
            message = "未提供Token";
        } else if (NotLoginException.INVALID_TOKEN.equals(e.getType())) {
            message = "Token无效";
        } else if (NotLoginException.TOKEN_TIMEOUT.equals(e.getType())) {
            message = "Token已过期";
        } else if (NotLoginException.BE_REPLACED.equals(e.getType())) {
            message = "Token已被替换";
        } else if (NotLoginException.KICK_OUT.equals(e.getType())) {
            message = "Token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        log.warn("未登录异常：{}", message);
        return Result.error(ResultCode.UNAUTHORIZED.getCode(), message);
    }

    /**
     * 权限不足异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Result<Void> handleNotPermissionException(NotPermissionException e) {
        log.warn("权限不足异常：{}", e.getMessage());
        return Result.error(ResultCode.FORBIDDEN.getCode(), "权限不足：" + e.getPermission());
    }

    /**
     * 角色不足异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Result<Void> handleNotRoleException(NotRoleException e) {
        log.warn("角色不足异常：{}", e.getMessage());
        return Result.error(ResultCode.FORBIDDEN.getCode(), "角色不足：" + e.getRole());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error(ResultCode.SYSTEM_ERROR);
    }
}
