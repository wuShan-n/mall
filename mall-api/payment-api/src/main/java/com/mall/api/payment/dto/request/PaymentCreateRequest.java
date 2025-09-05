package com.mall.api.payment.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment create request")
public class PaymentCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Business order number", example = "ORD202401010001")
    private String orderNo;
    
    @NotNull(message = "User ID cannot be null")
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @NotNull(message = "Payment type cannot be null")
    @Schema(description = "Payment type: 1-Alipay, 2-WeChat, 3-UnionPay", example = "1")
    private Integer paymentType;
    
    @NotNull(message = "Payment amount cannot be null")
    @DecimalMin(value = "0.01", message = "Payment amount must be greater than 0")
    @Schema(description = "Payment amount", example = "100.00")
    private BigDecimal paymentAmount;
    
    @Schema(description = "Currency", example = "CNY")
    private String currency = "CNY";
    
    @Schema(description = "Payment subject", example = "Order Payment")
    private String subject;
    
    @Schema(description = "Payment description", example = "Payment for order ORD202401010001")
    private String description;
    
    @Schema(description = "Client IP", example = "192.168.1.1")
    private String clientIp;
    
    @Schema(description = "Return URL", example = "https://example.com/return")
    private String returnUrl;
    
    @Schema(description = "Notify URL", example = "https://example.com/notify")
    private String notifyUrl;
    
    @Schema(description = "Expire time", example = "2024-01-01 10:30:00")
    private LocalDateTime expireTime;
    
    @Schema(description = "Extra parameters")
    private String extraParams;
}