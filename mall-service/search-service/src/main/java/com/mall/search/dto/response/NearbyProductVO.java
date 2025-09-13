package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 附近商品VO
 */
@Value
@SuperBuilder
@Schema(description = "附近商品")
public class NearbyProductVO extends ProductSimpleVO {
    @Schema(description = "距离(米)")
    Double distance;

    @Schema(description = "店铺名称")
    String storeName;

    @Schema(description = "店铺地址")
    String storeAddress;

    @Schema(description = "预计送达时间")
    String estimatedDeliveryTime;

    @Schema(description = "配送费")
    BigDecimal deliveryFee;
}