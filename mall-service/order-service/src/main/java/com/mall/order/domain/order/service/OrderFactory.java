package com.mall.order.domain.order.service;

import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.valueobject.PaymentType;
import com.mall.order.domain.order.valueobject.UserId;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单工厂
 * DDD核心：复杂对象的创建逻辑封装在工厂中
 */
@Component
public class OrderFactory {
    
    /**
     * 创建订单
     */
    public Order createOrder(Long userId, 
                            List<OrderItem> orderItems,
                            OrderAddress address,
                            Integer paymentType,
                            String remark) {
        
        // 创建值对象
        UserId userIdVO = new UserId(userId);
        PaymentType paymentTypeVO = PaymentType.fromCode(paymentType);
        
        // 使用聚合根的静态工厂方法创建订单
        Order order = Order.create(userIdVO, orderItems, address, paymentTypeVO);
        
        // 设置其他信息
        if (remark != null) {
            order.setRemark(remark);
        }
        
        return order;
    }
}