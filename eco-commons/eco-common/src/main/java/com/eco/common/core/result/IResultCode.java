package com.eco.common.core.result;

/**
 * 响应码接口
 */
public interface IResultCode {

    /**
     * 获取响应码
     */
    Integer getCode();

    /**
     * 获取响应消息
     */
    String getMsg();
}
