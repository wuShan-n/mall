//package com.mall.user.handler;
//
//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.exception.NotPermissionException;
//import cn.dev33.satoken.exception.NotRoleException;
//import com.mall.common.exception.BaseException;
//import com.mall.common.exception.BusinessException;
//import com.mall.common.exception.ValidationException;
//import com.mall.common.result.Result;
//import com.mall.common.result.ResultCode;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//
///**
// * Global Exception Handler
// *
// * @author mall
// * @date 2024-01-01
// */
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**
//     * 处理业务异常
//     */
//    @ExceptionHandler(BusinessException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Result<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
//        log.error("Business exception at {}: {}", request.getRequestURI(), e.getMessage());
//        return Result.failed(e.getCode(), e.getMessage());
//    }
//
//    /**
//     * 处理基础异常
//     */
//    @ExceptionHandler(BaseException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Result<Void> handleBaseException(BaseException e, HttpServletRequest request) {
//        log.error("Base exception at {}: {}", request.getRequestURI(), e.getMessage());
//        return Result.failed(e.getCode(), e.getMessage());
//    }
//
//    /**
//     * 处理参数校验异常 - @Valid校验失败
//     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
//        log.warn("Validation failed: {}", e.getMessage());
//
//        Map<String, String> errors = new HashMap<>();
//        e.getBindingResult().getFieldErrors().forEach(error ->
//            errors.put(error.getField(), error.getDefaultMessage())
//        );
//
//        return Result.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                "参数校验失败", errors);
//    }
//
//    /**
//     * 处理参数绑定异常
//     */
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Map<String, String>> handleBindException(BindException e) {
//        log.warn("Bind exception: {}", e.getMessage());
//
//        Map<String, String> errors = e.getFieldErrors().stream()
//                .collect(Collectors.toMap(
//                        FieldError::getField,
//                        FieldError::getDefaultMessage,
//                        (v1, v2) -> v1
//                ));
//
//        return Result.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                "参数绑定失败", errors);
//    }
//
//    /**
//     * 处理约束违反异常
//     */
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Map<String, String>> handleConstraintViolationException(ConstraintViolationException e) {
//        log.warn("Constraint violation: {}", e.getMessage());
//
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        Map<String, String> errors = violations.stream()
//                .collect(Collectors.toMap(
//                        v -> v.getPropertyPath().toString(),
//                        ConstraintViolation::getMessage,
//                        (v1, v2) -> v1
//                ));
//
//        return Result.failed(ResultCode.PARAM_VALID_ERROR.getCode(),
//                "参数约束违反", errors);
//    }
//
//    /**
//     * 处理验证异常
//     */
//    @ExceptionHandler(ValidationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Map<String, String>> handleValidationException(ValidationException e) {
//        log.warn("Validation exception: {}", e.getMessage());
//        return Result.failed(e.getCode(), e.getMessage(), e.getErrors());
//    }
//
//    /**
//     * 处理缺少请求参数异常
//     */
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Void> handleMissingParams(MissingServletRequestParameterException e) {
//        log.warn("Missing parameter: {}", e.getParameterName());
//        return Result.failed(ResultCode.PARAM_MISSING,
//                String.format("缺少必要参数: %s", e.getParameterName()));
//    }
//
//    /**
//     * 处理Http请求方法不支持异常
//     */
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    public Result<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
//        log.warn("Method not supported: {}", e.getMethod());
//        return Result.failed(ResultCode.METHOD_NOT_ALLOWED,
//                String.format("不支持的请求方法: %s", e.getMethod()));
//    }
//
//    /**
//     * 处理404异常
//     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public Result<Void> handleNoHandlerFoundException(NoHandlerFoundException e) {
//        log.warn("No handler found: {}", e.getRequestURL());
//        return Result.failed(ResultCode.NOT_FOUND,
//                String.format("接口不存在: %s", e.getRequestURL()));
//    }
//
//    /**
//     * 处理文件上传大小超限异常
//     */
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Result<Void> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
//        log.warn("Upload size exceeded: {}", e.getMessage());
//        return Result.failed(ResultCode.PARAM_ERROR, "文件大小超过限制");
//    }
//
//    /**
//     * 处理数据库唯一约束异常
//     */
//    @ExceptionHandler(DuplicateKeyException.class)
//    @ResponseStatus(HttpStatus.OK)
//    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
//        log.error("Duplicate key error: {}", e.getMessage());
//        return Result.failed(ResultCode.BUSINESS_ERROR, "数据重复，请检查输入");
//    }
//
//    /**
//     * Sa-Token 未登录异常
//     */
//    @ExceptionHandler(NotLoginException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public Result<Void> handleNotLoginException(NotLoginException e) {
//        log.warn("Not login: {}", e.getMessage());
//        return Result.failed(ResultCode.UNAUTHORIZED, "请先登录");
//    }
//
//    /**
//     * Sa-Token 无权限异常
//     */
//    @ExceptionHandler(NotPermissionException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Result<Void> handleNotPermissionException(NotPermissionException e) {
//        log.warn("No permission: {}", e.getMessage());
//        return Result.failed(ResultCode.FORBIDDEN, "无访问权限");
//    }
//
//    /**
//     * Sa-Token 无角色异常
//     */
//    @ExceptionHandler(NotRoleException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Result<Void> handleNotRoleException(NotRoleException e) {
//        log.warn("No role: {}", e.getMessage());
//        return Result.failed(ResultCode.FORBIDDEN, "无此角色权限");
//    }
//
//    /**
//     * 处理运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
//        log.error("Runtime exception at {}: ", request.getRequestURI(), e);
//        return Result.failed(ResultCode.FAILED, "系统运行异常，请稍后重试");
//    }
//
//    /**
//     * 处理所有未捕获的异常
//     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public Result<Void> handleException(Exception e, HttpServletRequest request) {
//        log.error("Unexpected exception at {}: ", request.getRequestURI(), e);
//        return Result.failed(ResultCode.FAILED, "系统异常，请联系管理员");
//    }
//}