package com.mall.api.inventory.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Stock changed event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockChangedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * SKU ID
     */
    private Long skuId;
    
    /**
     * Warehouse ID
     */
    private Long warehouseId;
    
    /**
     * Operation type
     */
    private Integer operationType;
    
    /**
     * Change quantity
     */
    private Integer changeQuantity;
    
    /**
     * Stock before
     */
    private Integer stockBefore;
    
    /**
     * Stock after
     */
    private Integer stockAfter;
    
    /**
     * Business type
     */
    private String businessType;
    
    /**
     * Business number
     */
    private String businessNo;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}
