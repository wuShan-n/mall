package com.mall.api.order.dto.response;

import com.mall.api.user.dto.response.AddressVO;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * Order settlement view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order settlement information")
public class OrderSettlementVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Settlement items")
    private List<SettlementItemVO> items;
    
    @Schema(description = "Total amount", example = "1000.00")
    private BigDecimal totalAmount;
    
    @Schema(description = "Product amount", example = "900.00")
    private BigDecimal productAmount;
    
    @Schema(description = "Freight amount", example = "10.00")
    private BigDecimal freightAmount;
    
    @Schema(description = "Discount amount", example = "50.00")
    private BigDecimal discountAmount;
    
    @Schema(description = "Coupon discount", example = "30.00")
    private BigDecimal couponDiscount;
    
    @Schema(description = "Points discount", example = "20.00")
    private BigDecimal pointsDiscount;
    
    @Schema(description = "Pay amount", example = "960.00")
    private BigDecimal payAmount;
    
    @Schema(description = "Available coupons")
    private List<CouponVO> availableCoupons;
    
    @Schema(description = "User addresses")
    private List<AddressVO> addresses;
    
    @Schema(description = "Default address")
    private AddressVO defaultAddress;
    
    @Schema(description = "User points", example = "1000")
    private Integer userPoints;
    
    @Schema(description = "Max usable points", example = "500")
    private Integer maxUsablePoints;
    
    @Schema(description = "Points exchange rate", example = "0.01")
    private BigDecimal pointsExchangeRate;
}