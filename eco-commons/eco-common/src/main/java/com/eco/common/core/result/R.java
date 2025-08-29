package com.eco.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一响应结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间戳
     */
    private Long timestamp;

    /**
     * 链路追踪ID
     */
    private String traceId;

    // ========== 成功响应 ==========

    public static <T> R<T> ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return R.<T>builder()
            .code(ResultCode.SUCCESS.getCode())
            .msg(ResultCode.SUCCESS.getMsg())
            .data(data)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    public static <T> R<T> ok(T data, String msg) {
        return R.<T>builder()
            .code(ResultCode.SUCCESS.getCode())
            .msg(msg)
            .data(data)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    // ========== 失败响应 ==========

    public static <T> R<T> fail() {
        return fail(ResultCode.SYSTEM_ERROR.getMsg());
    }

    public static <T> R<T> fail(String msg) {
        return fail(ResultCode.SYSTEM_ERROR.getCode(), msg);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return R.<T>builder()
            .code(code)
            .msg(msg)
            .timestamp(System.currentTimeMillis())
            .build();
    }

    public static <T> R<T> fail(IResultCode resultCode) {
        return fail(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> R<T> fail(IResultCode resultCode, String msg) {
        return fail(resultCode.getCode(), msg);
    }

    // ========== 工具方法 ==========

    public boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(this.code);
    }

    public boolean isFail() {
        return !isSuccess();
    }
}
