package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Order payment request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order payment request")
public class OrderPaymentRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @NotNull(message = "Payment type cannot be null")
    @Schema(description = "Payment type: 1-Alipay, 2-WeChat", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Return URL", example = "https://example.com/return")
    private String returnUrl;
    
    @Schema(description = "Notify URL", example = "https://example.com/notify")
    private String notifyUrl;
}