package com.mall.api.order.dto.response;

import java.math.BigDecimal;

public class CustomerOrderVO {
    private Long userId;
    private String username;
    private Integer orderCount;
    private BigDecimal totalAmount;
}