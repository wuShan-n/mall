package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 附近商品VO
 */
@Schema(description = "附近商品")
public class NearbyProductVO extends ProductSimpleVO {
    @Schema(description = "距离(米)")
    private Double distance;
    
    @Schema(description = "店铺名称")
    private String storeName;
    
    @Schema(description = "店铺地址")
    private String storeAddress;
    
    @Schema(description = "预计送达时间")
    private String estimatedDeliveryTime;
    
    @Schema(description = "配送费")
    private BigDecimal deliveryFee;
}