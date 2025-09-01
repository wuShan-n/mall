package com.mall.api.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Refund view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund information")
public class RefundVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Refund ID", example = "1")
    private Long id;
    
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @Schema(description = "Payment number", example = "PAY202401010001")
    private String paymentNo;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Refund amount", example = "50.00")
    private BigDecimal refundAmount;
    
    @Schema(description = "Refund reason", example = "Product quality issue")
    private String refundReason;
    
    @Schema(description = "Status", example = "1")
    private Integer status;
    
    @Schema(description = "Status name", example = "Refunded")
    private String statusName;
    
    @Schema(description = "Third party refund number", example = "REF2024010122001")
    private String thirdPartyRefundNo;
    
    @Schema(description = "Refund time", example = "2024-01-02 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;
    
    @Schema(description = "Create time", example = "2024-01-01 15:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
