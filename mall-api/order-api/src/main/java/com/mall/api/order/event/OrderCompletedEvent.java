package com.mall.api.order.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order completed event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCompletedEvent implements Serializable {
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
     * Total amount
     */
    private BigDecimal totalAmount;
    
    /**
     * Points to award
     */
    private Integer awardPoints;
    
    /**
     * Growth value to award
     */
    private Integer awardGrowth;
    
    /**
     * Complete time
     */
    private LocalDateTime completeTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}