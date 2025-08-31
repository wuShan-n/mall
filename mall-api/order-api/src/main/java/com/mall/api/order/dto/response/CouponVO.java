package com.mall.api.order.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
class CouponVO implements Serializable {
    private Long couponId;
    private String couponName;
    private BigDecimal discountAmount;
    private BigDecimal minPurchaseAmount;
    private LocalDateTime expireTime;
}