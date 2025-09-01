package com.mall.common.exception;

import com.mall.common.result.ResultCode;

/**
 * Business logic exception
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(ResultCode.BUSINESS_ERROR, message);
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode);
    }

    public BusinessException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
