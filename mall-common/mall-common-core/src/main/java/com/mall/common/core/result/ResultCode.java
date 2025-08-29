package com.mall.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举
 *
 * @author mall
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(500, "系统繁忙，请稍后重试"),
    /**
     * 参数错误
     */
    PARAM_ERROR(400, "参数错误"),
    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(400, "参数校验失败"),
    /**
     * 认证失败
     */
    UNAUTHORIZED(401, "认证失败"),
    /**
     * 权限不足
     */
    FORBIDDEN(403, "权限不足"),
    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),
    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    /**
     * 业务异常
     */
    BUSINESS_ERROR(600, "业务异常"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND(1001, "用户不存在"),
    /**
     * 用户名或密码错误
     */
    USER_LOGIN_ERROR(1002, "用户名或密码错误"),
    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS(1003, "用户已存在"),
    /**
     * 用户已被禁用
     */
    USER_DISABLED(1004, "用户已被禁用"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR(1005, "验证码错误"),
    /**
     * Token无效
     */
    TOKEN_INVALID(1006, "Token无效"),
    /**
     * Token过期
     */
    TOKEN_EXPIRED(1007, "Token过期");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 响应消息
     */
    private final String message;
}
