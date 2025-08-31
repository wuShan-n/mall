package com.mall.api.order.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    
    @Schema(description = "Order ID", example = "1")
    private Long orderId;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Refund type", example = "1")
    private Integer refundType;
    
    @Schema(description = "Refund type name", example = "Refund Only")
    private String refundTypeName;
    
    @Schema(description = "Refund amount", example = "100.00")
    private BigDecimal refundAmount;
    
    @Schema(description = "Refund reason", example = "Product damaged")
    private String refundReason;
    
    @Schema(description = "Description", example = "The screen is broken")
    private String description;
    
    @Schema(description = "Proof images")
    private List<String> proofImages;
    
    @Schema(description = "Status", example = "1")
    private Integer status;
    
    @Schema(description = "Status name", example = "Processing")
    private String statusName;
    
    @Schema(description = "Handle note", example = "Approved for refund")
    private String handleNote;
    
    @Schema(description = "Handle time", example = "2024-01-02 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime handleTime;
    
    @Schema(description = "Refund time", example = "2024-01-03 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;
    
    @Schema(description = "Create time", example = "2024-01-01 15:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
