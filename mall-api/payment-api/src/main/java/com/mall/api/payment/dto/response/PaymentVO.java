package com.mall.api.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Payment view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment information")
public class PaymentVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment ID", example = "1")
    private Long id;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Payment type name", example = "Alipay")
    private String paymentTypeName;
    
    @Schema(description = "Payment amount", example = "100.00")
    private BigDecimal paymentAmount;
    
    @Schema(description = "Currency", example = "CNY")
    private String currency;
    
    @Schema(description = "Status", example = "1")
    private Integer status;
    
    @Schema(description = "Status name", example = "Paid")
    private String statusName;
    
    @Schema(description = "Third party transaction ID", example = "2024010122001")
    private String thirdPartyNo;
    
    @Schema(description = "Payment time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;
    
    @Schema(description = "Expire time", example = "2024-01-01 10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
    
    @Schema(description = "Callback time", example = "2024-01-01 10:00:30")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime callbackTime;
    
    @Schema(description = "Create time", example = "2024-01-01 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}