package com.mall.order.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存锁定项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockLockItem {
    
    private Long skuId;
    private Integer quantity;
    
    public static StockLockItem of(Long skuId, Integer quantity) {
        return new StockLockItem(skuId, quantity);
    }
}