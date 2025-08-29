package com.eco.starter.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.eco.common.core.exception.BizException;
import com.eco.common.core.result.R;
import com.eco.common.core.result.ResultCode;
import com.eco.common.core.util.TraceUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public R<Void> handleBizException(BizException e, HttpServletRequest request) {
        log.warn("业务异常 [{}] {}", request.getRequestURI(), e.getMessage());
        return R.<Void>fail(e.getCode(), e.getMessage())
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<Void> handleNotLoginException(NotLoginException e, HttpServletRequest request) {
        log.warn("未登录 [{}]", request.getRequestURI());
        return R.<Void>fail(ResultCode.UNAUTHORIZED)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 无权限异常
     */
    @ExceptionHandler({NotPermissionException.class, NotRoleException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<Void> handleNotPermissionException(Exception e, HttpServletRequest request) {
        log.warn("无权限访问 [{}]", request.getRequestURI());
        return R.<Void>fail(ResultCode.FORBIDDEN)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 参数校验异常 - @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));
        log.warn("参数校验失败: {}", message);
        return R.<Void>fail(ResultCode.PARAM_VALID_ERROR.getCode(), message)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 参数校验异常 - 表单
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handleBindException(BindException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.joining(", "));
        log.warn("参数绑定失败: {}", message);
        return R.<Void>fail(ResultCode.PARAM_VALID_ERROR.getCode(), message)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 参数校验异常 - 单个参数
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
        log.warn("约束校验失败: {}", message);
        return R.<Void>fail(ResultCode.PARAM_VALID_ERROR.getCode(), message)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 缺少请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数: {}", e.getMessage());
        return R.<Void>fail(ResultCode.PARAM_IS_NULL.getCode(), "缺少参数: " + e.getParameterName())
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("请求方法不支持: {}", e.getMessage());
        return R.<Void>fail(ResultCode.METHOD_NOT_ALLOWED)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.warn("请求地址不存在: {}", e.getRequestURL());
        return R.<Void>fail(ResultCode.NOT_FOUND)
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 文件上传大小超限
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Void> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.warn("文件上传大小超限: {}", e.getMessage());
        return R.<Void>fail(ResultCode.BAD_REQUEST.getCode(), "文件大小超出限制")
            .setTraceId(TraceUtil.getTraceId());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常 [{}]", request.getRequestURI(), e);
        return R.<Void>fail(ResultCode.SYSTEM_ERROR)
            .setTraceId(TraceUtil.getTraceId());
    }
}
