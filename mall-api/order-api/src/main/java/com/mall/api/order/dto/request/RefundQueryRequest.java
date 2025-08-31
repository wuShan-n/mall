package com.mall.api.order.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Refund query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund query request")
public class RefundQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Refund number", example = "REF202401010001")
    private String refundNo;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Refund type", example = "1")
    private Integer refundType;
    
    @Schema(description = "Refund status", example = "1")
    private Integer status;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
}