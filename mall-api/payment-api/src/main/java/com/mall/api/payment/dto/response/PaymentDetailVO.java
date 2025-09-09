package com.mall.api.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Payment detail view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment detail information")
public class PaymentDetailVO extends PaymentVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Callback content")
    private String callbackContent;
    
    @Schema(description = "Client IP", example = "192.168.1.1")
    private String clientIp;
    
    @Schema(description = "Device info", example = "iPhone 15")
    private String deviceInfo;
    
    @Schema(description = "Return URL", example = "https://example.com/return")
    private String returnUrl;
    
    @Schema(description = "Notify URL", example = "https://example.com/notify")
    private String notifyUrl;
    
    @Schema(description = "Extra parameters")
    private String extraParams;
    
    @Schema(description = "Refund records")
    private List<RefundVO> refundRecords;
    
    @Schema(description = "Transaction records")
    private List<TransactionVO> transactionRecords;
}