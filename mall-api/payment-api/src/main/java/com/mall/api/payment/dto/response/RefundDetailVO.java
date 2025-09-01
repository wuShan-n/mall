package com.mall.api.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Refund detail view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund detail information")
public class RefundDetailVO extends RefundVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment info")
    private PaymentVO paymentInfo;
    
    @Schema(description = "Refund description", example = "Product has quality issues")
    private String description;
    
    @Schema(description = "Operator ID", example = "1")
    private Long operatorId;
    
    @Schema(description = "Operator name", example = "Admin")
    private String operatorName;
    
    @Schema(description = "Callback content")
    private String callbackContent;
    
    @Schema(description = "Notify URL", example = "https://example.com/refund/notify")
    private String notifyUrl;
}