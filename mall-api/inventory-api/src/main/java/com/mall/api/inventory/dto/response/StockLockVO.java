package com.mall.api.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Stock lock view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock lock information")
public class StockLockVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Lock ID", example = "1")
    private Long id;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Locked quantity", example = "5")
    private Integer lockedQuantity;
    
    @Schema(description = "Lock status: 1-Locked, 2-Released, 3-Deducted", example = "1")
    private Integer lockStatus;
    
    @Schema(description = "Lock time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lockTime;
    
    @Schema(description = "Expire time", example = "2024-01-01 10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
    
    @Schema(description = "Release time", example = "2024-01-01 10:15:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;
}