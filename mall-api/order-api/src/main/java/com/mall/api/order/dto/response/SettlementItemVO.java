package com.mall.api.order.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
class SettlementItemVO implements Serializable {
    private Long skuId;
    private String productName;
    private String productImage;
    private String productSpecs;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalAmount;
    private Integer stock;
}