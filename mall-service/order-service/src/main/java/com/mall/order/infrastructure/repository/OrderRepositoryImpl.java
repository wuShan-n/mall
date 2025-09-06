package com.mall.order.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.OrderStatus;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.infrastructure.persistence.mapper.OrderItemMapper;
import com.mall.order.infrastructure.persistence.mapper.OrderMapper;
import com.mall.order.infrastructure.persistence.po.OrderItemPO;
import com.mall.order.infrastructure.persistence.po.OrderPO;
import com.mall.order.infrastructure.repository.converter.OrderConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 订单仓储实现
 * DDD核心：仓储实现负责领域对象和持久化对象的转换
 */
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderConverter orderConverter;
    
    @Override
    @Transactional
    public Order save(Order order) {
        // 1. 转换为持久化对象
        OrderPO orderPO = orderConverter.toPO(order);
        
        // 2. 保存订单主表
        orderMapper.insert(orderPO);
        
        // 3. 保存订单项
        List<OrderItemPO> itemPOs = orderConverter.toItemPOs(order.getOrderItems(), orderPO.getId(), order.getOrderNo().getValue());
        for (OrderItemPO itemPO : itemPOs) {
            orderItemMapper.insert(itemPO);
        }
        
        // 4. 设置ID并返回
        order.setId(orderPO.getId());
        return order;
    }
    
    @Override
    @Transactional
    public Order update(Order order) {
        // 1. 转换为持久化对象
        OrderPO orderPO = orderConverter.toPO(order);
        orderPO.setId(order.getId());
        
        // 2. 更新订单主表
        orderMapper.updateById(orderPO);
        
        // 3. 更新订单项（如需要）
        // 这里简化处理，实际可能需要处理订单项的增删改
        
        return order;
    }
    
    @Override
    public Optional<Order> findByOrderNo(OrderNo orderNo) {
        LambdaQueryWrapper<OrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderPO::getOrderNo, orderNo.getValue());
        
        OrderPO orderPO = orderMapper.selectOne(wrapper);
        if (orderPO == null) {
            return Optional.empty();
        }
        
        // 查询订单项
        List<OrderItemPO> itemPOs = findOrderItems(orderPO.getId());
        
        // 转换为领域对象
        Order order = orderConverter.toDomain(orderPO, itemPOs);
        return Optional.of(order);
    }
    
    @Override
    public Optional<Order> findById(Long id) {
        OrderPO orderPO = orderMapper.selectById(id);
        if (orderPO == null) {
            return Optional.empty();
        }
        
        List<OrderItemPO> itemPOs = findOrderItems(id);
        Order order = orderConverter.toDomain(orderPO, itemPOs);
        return Optional.of(order);
    }
    
    @Override
    public List<Order> findByUserId(UserId userId) {
        LambdaQueryWrapper<OrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderPO::getUserId, userId.getValue())
               .eq(OrderPO::getDeleteStatus, 0)
               .orderByDesc(OrderPO::getCreateTime);
        
        List<OrderPO> orderPOs = orderMapper.selectList(wrapper);
        return orderPOs.stream()
            .map(po -> {
                List<OrderItemPO> itemPOs = findOrderItems(po.getId());
                return orderConverter.toDomain(po, itemPOs);
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findByUserIdAndStatus(UserId userId, OrderStatus status) {
        LambdaQueryWrapper<OrderPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderPO::getUserId, userId.getValue())
               .eq(OrderPO::getStatus, status.getCode())
               .eq(OrderPO::getDeleteStatus, 0)
               .orderByDesc(OrderPO::getCreateTime);
        
        List<OrderPO> orderPOs = orderMapper.selectList(wrapper);
        return orderPOs.stream()
            .map(po -> {
                List<OrderItemPO> itemPOs = findOrderItems(po.getId());
                return orderConverter.toDomain(po, itemPOs);
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findTimeoutOrders(LocalDateTime timeoutTime) {
        List<OrderPO> orderPOs = orderMapper.findTimeoutOrders(timeoutTime);
        return orderPOs.stream()
            .map(po -> {
                List<OrderItemPO> itemPOs = findOrderItems(po.getId());
                return orderConverter.toDomain(po, itemPOs);
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findOrdersToAutoConfirm(LocalDateTime confirmTime) {
        List<OrderPO> orderPOs = orderMapper.findOrdersToAutoConfirm(confirmTime);
        return orderPOs.stream()
            .map(po -> {
                List<OrderItemPO> itemPOs = findOrderItems(po.getId());
                return orderConverter.toDomain(po, itemPOs);
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> findOrdersToAutoComplete(LocalDateTime completeTime) {
        List<OrderPO> orderPOs = orderMapper.findOrdersToAutoComplete(completeTime);
        return orderPOs.stream()
            .map(po -> {
                List<OrderItemPO> itemPOs = findOrderItems(po.getId());
                return orderConverter.toDomain(po, itemPOs);
            })
            .collect(Collectors.toList());
    }
    
    @Override
    public void delete(Order order) {
        OrderPO orderPO = new OrderPO();
        orderPO.setId(order.getId());
        orderPO.setDeleteStatus(1);
        orderMapper.updateById(orderPO);
    }
    
    @Override
    public Long countByUserIdAndStatus(UserId userId, OrderStatus status) {
        return orderMapper.countByUserIdAndStatus(userId.getValue(), status.getCode());
    }
    
    /**
     * 查询订单项
     */
    private List<OrderItemPO> findOrderItems(Long orderId) {
        LambdaQueryWrapper<OrderItemPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItemPO::getOrderId, orderId);
        return orderItemMapper.selectList(wrapper);
    }
}
