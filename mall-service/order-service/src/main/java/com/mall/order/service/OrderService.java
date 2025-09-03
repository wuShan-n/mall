package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.result.PageResult;
import com.mall.order.entity.Order;
import java.util.List;
import java.util.Map;

/**
 * 订单服务接口
 */
public interface OrderService extends IService<Order> {
    
    /**
     * 创建订单
     */
    OrderVO createOrder(Long userId, OrderCreateRequest request);
    
    /**
     * 获取订单结算信息
     */
    OrderSettlementVO getSettlement(Long userId, OrderSettlementRequest request);
    
    /**
     * 支付订单
     */
    OrderPaymentVO payOrder(OrderPaymentRequest request);
    
    /**
     * 支付回调处理
     */
    void paymentCallback(String orderNo, String transactionId);
    
    /**
     * 取消订单
     */
    void cancelOrder(Long userId, OrderCancelRequest request);
    
    /**
     * 发货
     */
    void shipOrder(OrderShipRequest request);
    
    /**
     * 确认收货
     */
    void confirmReceipt(Long userId, String orderNo);
    
    /**
     * 完成订单
     */
    void completeOrder(String orderNo);
    
    /**
     * 删除订单
     */
    void deleteOrder(Long userId, String orderNo);
    
    /**
     * 根据订单号获取订单
     */
    OrderVO getOrderByNo(String orderNo);
    
    /**
     * 获取订单详情
     */
    OrderDetailVO getOrderDetail(String orderNo);
    
    /**
     * 查询订单列表
     */
    PageResult<OrderVO> queryOrders(OrderQueryRequest request);
    
    /**
     * 获取用户订单
     */
    PageResult<OrderVO> getUserOrders(Long userId, Integer status, Long current, Long size);
    
    /**
     * 获取订单状态统计
     */
    Map<Integer, Long> getOrderCountByStatus(Long userId);
    
    /**
     * 获取订单项
     */
    List<OrderItemVO> getOrderItems(String orderNo);
    
    /**
     * 检查订单超时
     */
    void checkOrderTimeout();
    
    /**
     * 自动确认收货
     */
    void autoConfirmReceipt();
    
    /**
     * 自动完成订单
     */
    void autoCompleteOrder();
    
    /**
     * 获取订单统计
     */
    OrderStatisticsVO getOrderStatistics();
    
    /**
     * 获取用户订单统计
     */
    OrderStatisticsVO getUserOrderStatistics(Long userId);
}