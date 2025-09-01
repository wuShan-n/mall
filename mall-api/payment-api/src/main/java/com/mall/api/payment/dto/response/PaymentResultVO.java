package com.mall.api.payment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Payment result view object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Payment result")
public class PaymentResultVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Payment URL or form", example = "https://payment.alipay.com/...")
    private String paymentUrl;
    
    @Schema(description = "Payment form HTML", example = "<form>...</form>")
    private String paymentForm;
    
    @Schema(description = "QR code URL", example = "https://example.com/qrcode.png")
    private String qrCodeUrl;
    
    @Schema(description = "Payment parameters")
    private String paymentParams;
    
    @Schema(description = "Need redirect", example = "true")
    private Boolean needRedirect;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
}