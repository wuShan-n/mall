package com.mall.api.inventory.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Batch stock update request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Batch stock update request")
public class BatchStockUpdateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Stock update list cannot be empty")
    @Valid
    @Schema(description = "Stock update list")
    private List<StockUpdateRequest> stockUpdates;
    
    @Schema(description = "Is transactional", example = "true")
    private Boolean transactional = true;
}