package com.mall.api.payment.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment failed event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentFailedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Payment ID
     */
    private Long paymentId;
    
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
     * Payment amount
     */
    private BigDecimal paymentAmount;
    
    /**
     * Payment type
     */
    private Integer paymentType;
    
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