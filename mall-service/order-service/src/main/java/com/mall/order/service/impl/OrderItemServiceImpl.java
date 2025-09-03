package com.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.order.entity.OrderItem;
import com.mall.order.mapper.OrderItemMapper;
import com.mall.order.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单商品项服务实现类
 */
@Slf4j
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    
    @Override
    @Transactional
    public boolean saveBatch(Long orderId, String orderNo, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return true;
        }
        
        for (OrderItem item : items) {
            item.setOrderId(orderId);
            item.setOrderNo(orderNo);
        }
        
        return this.saveBatch(items);
    }
    
    @Override
    public List<OrderItem> getByOrderId(Long orderId) {
        return baseMapper.selectByOrderId(orderId);
    }
    
    @Override
    public List<OrderItem> getByOrderNo(String orderNo) {
        return baseMapper.selectByOrderNo(orderNo);
    }
}