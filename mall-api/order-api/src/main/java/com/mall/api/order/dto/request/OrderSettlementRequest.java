package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Order settlement request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order settlement request")
public class OrderSettlementRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Items cannot be empty")
    @Schema(description = "Settlement items")
    private List<OrderItemRequest> items;
    
    @Schema(description = "Address ID", example = "1")
    private Long addressId;
    
    @Schema(description = "Coupon ID", example = "1")
    private Long couponId;
    
    @Schema(description = "Use points", example = "100")
    private Integer usePoints;
}