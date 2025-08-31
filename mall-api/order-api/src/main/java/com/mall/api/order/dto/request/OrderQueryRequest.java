package com.mall.api.order.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order query request")
public class OrderQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Order status", example = "1")
    private Integer status;
    
    @Schema(description = "Payment type", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Source type", example = "2")
    private Integer sourceType;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
    
    @Schema(description = "Minimum amount", example = "0.00")
    private BigDecimal minAmount;
    
    @Schema(description = "Maximum amount", example = "10000.00")
    private BigDecimal maxAmount;
    
    @Schema(description = "Product name", example = "iPhone")
    private String productName;
    
    @Schema(description = "Receiver name", example = "John")
    private String receiverName;
    
    @Schema(description = "Receiver phone", example = "138")
    private String receiverPhone;
}