package com.mall.order.application.service;

import com.mall.api.order.dto.response.OrderDetailVO;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.common.result.PageResult;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.OrderStatus;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.interfaces.assembler.OrderAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单查询应用服务
 * 专门处理订单查询相关的用例
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderQueryApplicationService {
    
    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;
    
    /**
     * 获取订单详情
     */
    @Transactional(readOnly = true)
    public OrderDetailVO getOrderDetail(String orderNo) {
        log.debug("Getting order detail for orderNo: {}", orderNo);
        
        try {
            Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
                    .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));
            
            return orderAssembler.toOrderDetailVO(order);
            
        } catch (Exception e) {
            log.error("Failed to get order detail for orderNo: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 获取订单详情（验证用户权限）
     */
    @Transactional(readOnly = true)
    public OrderDetailVO getOrderDetailForUser(String orderNo, Long userId) {
        log.debug("Getting order detail for orderNo: {}, userId: {}", orderNo, userId);
        
        try {
            Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
                    .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));
            
            // 验证用户权限
            if (!order.getUserId().getValue().equals(userId)) {
                throw new IllegalArgumentException("无权查看此订单");
            }
            
            return orderAssembler.toOrderDetailVO(order);
            
        } catch (Exception e) {
            log.error("Failed to get order detail for orderNo: {}, userId: {}", orderNo, userId, e);
            throw e;
        }
    }
    
    /**
     * 分页查询用户订单
     */
    @Transactional(readOnly = true)
    public PageResult<OrderVO> getUserOrders(Long userId, Integer status, Integer page, Integer size) {
        log.debug("Getting user orders for userId: {}, status: {}, page: {}, size: {}", 
                 userId, status, page, size);
        
        try {
            List<Order> orders = queryUserOrders(userId, status);
            
            // 简单内存分页处理
            PageResult<OrderVO> pageResult = performInMemoryPaging(orders, page, size);
            
            log.debug("Found {} orders for user: {}", pageResult.getTotal(), userId);
            
            return pageResult;
            
        } catch (Exception e) {
            log.error("Failed to get user orders for userId: {}, status: {}", userId, status, e);
            throw e;
        }
    }
    
    /**
     * 获取用户订单状态统计
     */
    @Transactional(readOnly = true)
    public Map<Integer, Long> getOrderCountByStatus(Long userId) {
        log.debug("Getting order count by status for userId: {}", userId);
        
        try {
            Map<Integer, Long> countMap = new HashMap<>();
            UserId userIdVO = new UserId(userId);
            
            for (OrderStatus status : OrderStatus.values()) {
                Long count = orderRepository.countByUserIdAndStatus(userIdVO, status);
                countMap.put(status.getCode(), count);
            }
            
            log.debug("Order count by status for user {}: {}", userId, countMap);
            
            return countMap;
            
        } catch (Exception e) {
            log.error("Failed to get order count by status for userId: {}", userId, e);
            throw e;
        }
    }
    
    /**
     * 获取订单简要信息
     */
    @Transactional(readOnly = true)
    public OrderVO getOrderSummary(String orderNo) {
        log.debug("Getting order summary for orderNo: {}", orderNo);
        
        try {
            Order order = orderRepository.findByOrderNo(new OrderNo(orderNo))
                    .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));
            
            return orderAssembler.toOrderVO(order);
            
        } catch (Exception e) {
            log.error("Failed to get order summary for orderNo: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 批量获取订单信息
     */
    @Transactional(readOnly = true)
    public List<OrderVO> getOrdersBatch(List<String> orderNos) {
        log.debug("Getting orders batch for orderNos: {}", orderNos);
        
        try {
            if (orderNos == null || orderNos.isEmpty()) {
                return List.of();
            }
            
            List<OrderNo> orderNoList = orderNos.stream()
                    .map(OrderNo::new)
                    .collect(Collectors.toList());
            
            // 由于没有批量查询方法，改为逐个查询
            List<Order> orders = new ArrayList<>();
            for (OrderNo orderNo : orderNoList) {
                Optional<Order> order = orderRepository.findByOrderNo(orderNo);
                order.ifPresent(orders::add);
            }
            
            return orders.stream()
                    .map(orderAssembler::toOrderVO)
                    .collect(Collectors.toList());
            
        } catch (Exception e) {
            log.error("Failed to get orders batch for orderNos: {}", orderNos, e);
            throw e;
        }
    }
    
    /**
     * 检查订单是否存在
     */
    @Transactional(readOnly = true)
    public boolean existsOrder(String orderNo) {
        log.debug("Checking if order exists: {}", orderNo);
        
        try {
            return orderRepository.findByOrderNo(new OrderNo(orderNo)).isPresent();
            
        } catch (Exception e) {
            log.error("Failed to check if order exists: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 根据条件查询用户订单
     */
    private List<Order> queryUserOrders(Long userId, Integer status) {
        UserId userIdVO = new UserId(userId);
        
        if (status != null) {
            OrderStatus orderStatus = OrderStatus.fromCode(status);
            return orderRepository.findByUserIdAndStatus(userIdVO, orderStatus);
        } else {
            return orderRepository.findByUserId(userIdVO);
        }
    }
    
    /**
     * 执行内存分页
     */
    private PageResult<OrderVO> performInMemoryPaging(List<Order> orders, Integer page, Integer size) {
        // 总记录数
        long total = orders.size();
        
        // 计算分页范围
        int start = (page - 1) * size;
        int end = Math.min(start + size, orders.size());
        
        // 获取当前页数据
        List<Order> pageOrders = (start < orders.size()) ? 
                orders.subList(start, end) : 
                List.of();
        
        // 转换为VO
        List<OrderVO> orderVOs = pageOrders.stream()
                .map(orderAssembler::toOrderVO)
                .collect(Collectors.toList());
        
        return PageResult.of((long) page, (long) size, total, orderVOs);
    }
}