package com.mall.api.payment.constant;

/**
 * Payment service constants
 */
public interface PaymentConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "payment-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/payment";
    
    /**
     * Payment number prefix
     */
    String PAYMENT_NO_PREFIX = "PAY";
    
    /**
     * Refund number prefix
     */
    String REFUND_NO_PREFIX = "REF";
    
    /**
     * Transaction number prefix
     */
    String TRANSACTION_NO_PREFIX = "TXN";
    
    /**
     * Payment timeout minutes
     */
    Integer PAYMENT_TIMEOUT_MINUTES = 30;
    
    /**
     * Refund processing days limit
     */
    Integer REFUND_PROCESS_DAYS = 180;
    
    /**
     * Default currency
     */
    String DEFAULT_CURRENCY = "CNY";
}