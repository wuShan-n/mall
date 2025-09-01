package com.mall.api.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund approved event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundApprovedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Refund ID
     */
    private Long refundId;
    
    /**
     * Refund number
     */
    private String refundNo;
    
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
     * Refund amount
     */
    private BigDecimal refundAmount;
    
    /**
     * Refund type
     */
    private Integer refundType;
    
    /**
     * Approve time
     */
    private LocalDateTime approveTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}