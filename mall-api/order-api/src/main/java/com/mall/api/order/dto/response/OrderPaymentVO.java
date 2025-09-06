package com.mall.api.order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order payment view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order payment information")
@Builder
public class OrderPaymentVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment ID", example = "1")
    private Long id;
    
    @Schema(description = "Order ID", example = "1")
    private Long orderId;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Payment type name", example = "Alipay")
    private String paymentTypeName;
    
    @Schema(description = "Payment amount", example = "960.00")
    private BigDecimal paymentAmount;
    
    @Schema(description = "Payment status", example = "1")
    private Integer paymentStatus;
    
    @Schema(description = "Payment status name", example = "Paid")
    private String paymentStatusName;
    
    @Schema(description = "Payment time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;
    
    @Schema(description = "Third party transaction number", example = "2024010122001")
    private String transactionId;
    
    @Schema(description = "Payment URL", example = "https://payment.alipay.com/...")
    private String paymentUrl;
}