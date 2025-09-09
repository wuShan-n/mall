package com.mall.api.payment.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Transaction view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Transaction record")
public class TransactionVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Transaction ID", example = "1")
    private Long id;
    
    @Schema(description = "Transaction number", example = "TXN202401010001")
    private String transactionNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Transaction type", example = "1")
    private Integer transactionType;
    
    @Schema(description = "Transaction type name", example = "Payment")
    private String transactionTypeName;
    
    @Schema(description = "Amount", example = "100.00")
    private BigDecimal amount;
    
    @Schema(description = "Balance before", example = "1000.00")
    private BigDecimal balanceBefore;
    
    @Schema(description = "Balance after", example = "900.00")
    private BigDecimal balanceAfter;
    
    @Schema(description = "Business type", example = "ORDER_PAYMENT")
    private String businessType;
    
    @Schema(description = "Business number", example = "ORD202401010001")
    private String businessNo;
    
    @Schema(description = "Remark", example = "Order payment")
    private String remark;
    
    @Schema(description = "Create time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}