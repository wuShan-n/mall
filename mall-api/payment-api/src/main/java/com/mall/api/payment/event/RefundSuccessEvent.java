package com.mall.api.payment.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund success event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundSuccessEvent implements Serializable {
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
     * Third party refund number
     */
    private String thirdPartyRefundNo;
    
    /**
     * Refund time
     */
    private LocalDateTime refundTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
}