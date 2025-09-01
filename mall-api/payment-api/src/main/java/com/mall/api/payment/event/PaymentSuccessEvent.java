package com.mall.api.payment.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment success event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSuccessEvent implements Serializable {
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
     * Third party transaction ID
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