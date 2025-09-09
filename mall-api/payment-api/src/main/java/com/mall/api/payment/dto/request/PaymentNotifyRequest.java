package com.mall.api.payment.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Payment notify request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Payment notify request")
public class PaymentNotifyRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Notify data")
    private String notifyData;
    
    @Schema(description = "Notify parameters")
    private Map<String, String> notifyParams;
    
    @Schema(description = "Signature", example = "abc123...")
    private String signature;
    
    @Schema(description = "Sign type", example = "RSA2")
    private String signType;
    
    @Schema(description = "Charset", example = "UTF-8")
    private String charset;
}