package com.mall.api.order.constant;

/**
 * Order service constants
 */
public interface OrderConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "order-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/order";
    
    /**
     * Order number prefix
     */
    String ORDER_NO_PREFIX = "ORD";
    
    /**
     * Refund number prefix
     */
    String REFUND_NO_PREFIX = "REF";
    
    /**
     * Order timeout minutes
     */
    Integer ORDER_TIMEOUT_MINUTES = 30;
    
    /**
     * Auto confirm days
     */
    Integer AUTO_CONFIRM_DAYS = 15;
    
    /**
     * Auto complete days after confirm
     */
    Integer AUTO_COMPLETE_DAYS = 7;
}
