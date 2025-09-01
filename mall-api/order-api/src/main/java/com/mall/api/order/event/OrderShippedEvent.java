package com.mall.api.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Order shipped event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShippedEvent implements Serializable {
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
     * Delivery company
     */
    private String deliveryCompany;
    
    /**
     * Delivery tracking number
     */
    private String deliveryNo;
    
    /**
     * Ship time
     */
    private LocalDateTime shipTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}
