package com.eco.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用响应码枚举
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    // ========== 成功响应 ==========
    SUCCESS(200, "操作成功"),

    // ========== 客户端错误 ==========
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "无权限访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    TOO_MANY_REQUESTS(429, "请求过于频繁"),

    // ========== 服务端错误 ==========
    SYSTEM_ERROR(500, "系统错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),

    // ========== 业务错误 ==========
    BIZ_ERROR(1000, "业务异常"),
    DATA_NOT_FOUND(1001, "数据不存在"),
    DATA_EXISTED(1002, "数据已存在"),
    DATA_ERROR(1003, "数据异常"),

    // ========== 参数校验 ==========
    PARAM_VALID_ERROR(1100, "参数校验失败"),
    PARAM_IS_NULL(1101, "参数为空"),
    PARAM_TYPE_ERROR(1102, "参数类型错误"),
    PARAM_FORMAT_ERROR(1103, "参数格式错误"),

    // ========== 用户相关 ==========
    USER_NOT_FOUND(2001, "用户不存在"),
    USER_EXISTED(2002, "用户已存在"),
    USER_PASSWORD_ERROR(2003, "密码错误"),
    USER_ACCOUNT_DISABLED(2004, "账号已禁用"),
    USER_ACCOUNT_LOCKED(2005, "账号已锁定"),

    // ========== Token相关 ==========
    TOKEN_INVALID(2101, "Token无效"),
    TOKEN_EXPIRED(2102, "Token已过期"),
    TOKEN_NOT_FOUND(2103, "Token不存在");

    private final Integer code;
    private final String msg;
}
