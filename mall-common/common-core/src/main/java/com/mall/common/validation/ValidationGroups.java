package com.mall.common.validation;

/**
 * Validation groups for different scenarios
 */
public interface ValidationGroups {
    
    /**
     * Create validation group
     */
    interface Create {}
    
    /**
     * Update validation group
     */
    interface Update {}
    
    /**
     * Query validation group
     */
    interface Query {}
    
    /**
     * Delete validation group
     */
    interface Delete {}
}