package com.mall.order.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单项实体
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    
    private Long id;
    private Long skuId;
    private String skuName;
    private String skuImage;
    private String skuSpecs;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal realAmount;
    
    /**
     * 计算小计金额
     */
    public BigDecimal calculateAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    
    /**
     * 是否可退款
     */
    public boolean canRefund() {
        // 业务规则：未发货或已收货的商品可以退款
        return true;
    }
}
