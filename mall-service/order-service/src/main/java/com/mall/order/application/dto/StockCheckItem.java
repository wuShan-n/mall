package com.mall.order.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存检查项
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCheckItem {
    
    private Long skuId;
    private Integer quantity;
    
    public static StockCheckItem of(Long skuId, Integer quantity) {
        return new StockCheckItem(skuId, quantity);
    }
}