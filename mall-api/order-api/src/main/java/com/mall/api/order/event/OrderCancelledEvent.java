package com.mall.api.order.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order cancelled event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelledEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Order ID
     */
    private Long orderId;
    
    /**
     * Order number
     */
    private String orderNo;
    
    /**
     * User ID
     */
    private Long userId;
    
    /**
     * Cancel reason
     */
    private String cancelReason;
    
    /**
     * Cancel type (1-User cancel, 2-System cancel, 3-Admin cancel)
     */
    private Integer cancelType;
    
    /**
     * SKUs to release stock
     */
    private List<StockReleaseInfo> stockReleaseList;
    
    /**
     * Points to return
     */
    private Integer returnPoints;
    
    /**
     * Coupon to return
     */
    private Long returnCouponId;
    
    /**
     * Cancel time
     */
    private LocalDateTime cancelTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StockReleaseInfo implements Serializable {
        private Long skuId;
        private Integer quantity;
    }
}
