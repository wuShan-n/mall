package com.mall.common.exception;

import com.mall.common.result.ResultCode;
import lombok.Getter;

import java.util.Map;

/**
 * Parameter validation exception
 */
@Getter
public class ValidationException extends BaseException {
    private static final long serialVersionUID = 1L;

    private Map<String, String> errors;

    public ValidationException(String message) {
        super(ResultCode.PARAM_VALID_ERROR, message);
    }

    public ValidationException(Map<String, String> errors) {
        super(ResultCode.PARAM_VALID_ERROR, "Validation failed");
        this.errors = errors;
    }
}