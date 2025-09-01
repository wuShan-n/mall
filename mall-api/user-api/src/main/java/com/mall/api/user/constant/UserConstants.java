package com.mall.api.user.constant;

/**
 * User service constants
 */
public interface UserConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "user-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/user";
    
    /**
     * User status constants
     */
    interface Status {
        Integer NORMAL = 0;
        Integer DISABLED = 1;
        Integer LOCKED = 2;
    }
    
    /**
     * Register source constants
     */
    interface RegisterSource {
        Integer PC = 1;
        Integer APP = 2;
        Integer MINI_PROGRAM = 3;
        Integer H5 = 4;
    }
    
    /**
     * Gender constants
     */
    interface Gender {
        Integer UNKNOWN = 0;
        Integer MALE = 1;
        Integer FEMALE = 2;
    }
}