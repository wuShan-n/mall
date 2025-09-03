package com.mall.api.inventory.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Stock locked event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLockedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Order number
     */
    private String orderNo;
    
    /**
     * User ID
     */
    private Long userId;
    
    /**
     * Lock items
     */
    private List<LockItem> lockItems;
    
    /**
     * Lock expire time
     */
    private LocalDateTime expireTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LockItem implements Serializable {
        private Long skuId;
        private Long warehouseId;
        private Integer lockedQuantity;
        private Long lockId;
    }
}