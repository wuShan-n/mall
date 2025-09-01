package com.mall.api.product.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Stock update request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Stock update request")
public class StockUpdateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "SKU ID cannot be null")
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Quantity to add/deduct", example = "10")
    private Integer quantity;
    
    @NotNull(message = "Operation type cannot be null")
    @Schema(description = "Operation type: 1-Add, 2-Deduct", example = "1")
    private Integer operationType;
    
    @Schema(description = "Business type", example = "PURCHASE")
    private String businessType;
    
    @Schema(description = "Business number", example = "PO202401010001")
    private String businessNo;
    
    @Schema(description = "Remark", example = "Purchase order stock in")
    private String remark;
}
