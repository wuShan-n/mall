package com.mall.api.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Stock record view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock operation record")
public class StockRecordVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Record ID", example = "1")
    private Long id;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "SKU code", example = "SKU001")
    private String skuCode;
    
    @Schema(description = "SKU name", example = "iPhone 15 Pro")
    private String skuName;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long warehouseId;
    
    @Schema(description = "Warehouse name", example = "Beijing Main Warehouse")
    private String warehouseName;
    
    @Schema(description = "Operation type: 1-Add, 2-Deduct, 3-Lock, 4-Unlock", example = "1")
    private Integer operationType;
    
    @Schema(description = "Operation type name", example = "Add Stock")
    private String operationTypeName;
    
    @Schema(description = "Change quantity", example = "+100")
    private Integer changeQuantity;
    
    @Schema(description = "Stock before", example = "900")
    private Integer stockBefore;
    
    @Schema(description = "Stock after", example = "1000")
    private Integer stockAfter;
    
    @Schema(description = "Business type", example = "PURCHASE")
    private String businessType;
    
    @Schema(description = "Business number", example = "PO202401010001")
    private String businessNo;
    
    @Schema(description = "Remark", example = "Purchase order stock in")
    private String remark;
    
    @Schema(description = "Operator ID", example = "1")
    private Long operatorId;
    
    @Schema(description = "Operator name", example = "Admin")
    private String operatorName;
    
    @Schema(description = "Operation time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationTime;
}