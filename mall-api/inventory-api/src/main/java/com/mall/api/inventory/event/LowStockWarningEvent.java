package com.mall.api.inventory.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Low stock warning event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LowStockWarningEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * SKU ID
     */
    private Long skuId;
    
    /**
     * SKU code
     */
    private String skuCode;
    
    /**
     * SKU name
     */
    private String skuName;
    
    /**
     * Warehouse ID
     */
    private Long warehouseId;
    
    /**
     * Warehouse name
     */
    private String warehouseName;
    
    /**
     * Current stock
     */
    private Integer currentStock;
    
    /**
     * Warning level
     */
    private Integer warningLevel;
    
    /**
     * Safety stock level
     */
    private Integer safetyLevel;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}