package com.mall.api.product.constant;

/**
 * Product service constants
 */
public interface ProductConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "product-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/product";
    
    /**
     * Product status constants
     */
    interface Status {
        Integer DOWN = 0;  // Off shelf
        Integer UP = 1;    // On shelf
    }
    
    /**
     * Audit status constants
     */
    interface AuditStatus {
        Integer PENDING = 0;
        Integer APPROVED = 1;
        Integer REJECTED = 2;
    }
    
    /**
     * Attribute type constants
     */
    interface AttributeType {
        Integer SPEC = 0;      // Specification
        Integer PARAM = 1;     // Parameter
    }
    
    /**
     * Input type constants
     */
    interface InputType {
        Integer MANUAL = 0;    // Manual input
        Integer SELECT = 1;    // Select from list
    }
}