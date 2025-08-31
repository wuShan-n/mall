package com.mall.api.order.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order paid event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {
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
     * Payment amount
     */
    private BigDecimal paymentAmount;
    
    /**
     * Payment type
     */
    private Integer paymentType;
    
    /**
     * Transaction ID
     */
    private String transactionId;
    
    /**
     * Payment time
     */
    private LocalDateTime paymentTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}