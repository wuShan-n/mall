package com.mall.api.payment.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund failed event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundFailedEvent implements Serializable {
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
     * Payment number
     */
    private String paymentNo;
    
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
     * Failure reason
     */
    private String failureReason;
    
    /**
     * Error code
     */
    private String errorCode;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}