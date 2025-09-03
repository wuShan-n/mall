package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderItem;
import java.util.List;

/**
 * 订单商品项服务接口
 */
public interface OrderItemService extends IService<OrderItem> {
    
    /**
     * 批量保存订单项
     */
    boolean saveBatch(Long orderId, String orderNo, List<OrderItem> items);
    
    /**
     * 根据订单ID获取订单项
     */
    List<OrderItem> getByOrderId(Long orderId);
    
    /**
     * 根据订单号获取订单项
     */
    List<OrderItem> getByOrderNo(String orderNo);
}
