package com.mall.order.domain.order.service;

import com.mall.order.domain.order.valueobject.OrderNo;

/**
 * 库存领域服务接口
 * 用于处理库存相关的跨服务调用
 */
public interface InventoryDomainService {
    
    /**
     * 锁定库存
     */
    void lockStock(Long skuId, Integer quantity, OrderNo orderNo);
    
    /**
     * 释放库存
     */
    void releaseStock(OrderNo orderNo);
    
    /**
     * 扣减库存
     */
    void deductStock(OrderNo orderNo);
    
    /**
     * 检查库存
     */
    boolean checkStock(Long skuId, Integer quantity);
}
