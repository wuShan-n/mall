package com.mall.common.exception;

import com.mall.common.result.ResultCode;
import lombok.Getter;

/**
 * Base business exception
 */
@Getter
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private Integer code;
    private String message;
    
    public BaseException() {
        super(ResultCode.FAILED.getMessage());
        this.code = ResultCode.FAILED.getCode();
        this.message = ResultCode.FAILED.getMessage();
    }
    
    public BaseException(String message) {
        super(message);
        this.code = ResultCode.FAILED.getCode();
        this.message = message;
    }
    
    public BaseException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
    
    public BaseException(ResultCode resultCode, String message) {
        super(message);
        this.code = resultCode.getCode();
        this.message = message;
    }
    
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.FAILED.getCode();
        this.message = message;
    }
}