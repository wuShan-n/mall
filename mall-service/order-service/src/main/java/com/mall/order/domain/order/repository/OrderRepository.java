package com.mall.order.domain.order.repository;

import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.OrderStatus;
import com.mall.order.domain.order.valueobject.UserId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 订单仓储接口
 * DDD核心：仓储接口定义在领域层，实现在基础设施层
 */
public interface OrderRepository {
    
    /**
     * 保存订单
     */
    Order save(Order order);
    
    /**
     * 更新订单
     */
    Order update(Order order);
    
    /**
     * 根据订单号查找订单
     */
    Optional<Order> findByOrderNo(OrderNo orderNo);
    
    /**
     * 根据ID查找订单
     */
    Optional<Order> findById(Long id);
    
    /**
     * 根据用户ID查找订单
     */
    List<Order> findByUserId(UserId userId);
    
    /**
     * 根据用户ID和状态查找订单
     */
    List<Order> findByUserIdAndStatus(UserId userId, OrderStatus status);
    
    /**
     * 查找超时未支付的订单
     */
    List<Order> findTimeoutOrders(LocalDateTime timeoutTime);
    
    /**
     * 查找待自动确认收货的订单
     */
    List<Order> findOrdersToAutoConfirm(LocalDateTime confirmTime);
    
    /**
     * 查找待自动完成的订单
     */
    List<Order> findOrdersToAutoComplete(LocalDateTime completeTime);
    
    /**
     * 删除订单（软删除）
     */
    void delete(Order order);
    
    /**
     * 统计用户各状态订单数量
     */
    Long countByUserIdAndStatus(UserId userId, OrderStatus status);
}