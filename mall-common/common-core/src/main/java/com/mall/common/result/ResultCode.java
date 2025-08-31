package com.mall.common.result;

import lombok.Getter;

/**
 * API response code enumeration
 */
@Getter
public enum ResultCode {
    // Success codes
    SUCCESS(200, "Success"),
    
    // Client error codes (4xx)
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    
    // Business error codes (1xxx)
    PARAM_ERROR(1001, "Parameter Error"),
    PARAM_MISSING(1002, "Required Parameter Missing"),
    PARAM_TYPE_ERROR(1003, "Parameter Type Error"),
    PARAM_VALID_ERROR(1004, "Parameter Validation Failed"),
    
    // User related (2xxx)
    USER_NOT_FOUND(2001, "User Not Found"),
    USER_ALREADY_EXISTS(2002, "User Already Exists"),
    USER_PASSWORD_ERROR(2003, "Invalid Username or Password"),
    USER_ACCOUNT_DISABLED(2004, "User Account Disabled"),
    USER_ACCOUNT_LOCKED(2005, "User Account Locked"),
    USER_TOKEN_EXPIRED(2006, "Token Expired"),
    USER_TOKEN_INVALID(2007, "Invalid Token"),
    
    // Business related (3xxx)
    BUSINESS_ERROR(3000, "Business Error"),
    PRODUCT_NOT_FOUND(3001, "Product Not Found"),
    STOCK_INSUFFICIENT(3002, "Insufficient Stock"),
    ORDER_NOT_FOUND(3003, "Order Not Found"),
    ORDER_STATUS_ERROR(3004, "Order Status Error"),
    PAYMENT_ERROR(3005, "Payment Error"),
    
    // Server error codes (5xx)
    FAILED(500, "Internal Server Error"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout");
    
    private final Integer code;
    private final String message;
    
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}