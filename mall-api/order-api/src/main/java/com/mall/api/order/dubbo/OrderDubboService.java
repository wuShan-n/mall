package com.mall.api.order.dubbo;

import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.util.List;
import java.util.Map;

/**
 * Order Dubbo RPC service interface
 */
public interface OrderDubboService {
    
    /**
     * Create order
     */
    Result<OrderVO> createOrder(Long userId, OrderCreateRequest request);
    
    /**
     * Get order settlement info
     */
    Result<OrderSettlementVO> getSettlement(Long userId, OrderSettlementRequest request);
    
    /**
     * Pay order
     */
    Result<OrderPaymentVO> payOrder(OrderPaymentRequest request);
    
    /**
     * Payment callback
     */
    Result<Void> paymentCallback(String orderNo, String transactionId);
    
    /**
     * Cancel order
     */
    Result<Void> cancelOrder(Long userId, OrderCancelRequest request);
    
    /**
     * Ship order
     */
    Result<Void> shipOrder(OrderShipRequest request);
    
    /**
     * Confirm receipt
     */
    Result<Void> confirmReceipt(Long userId, String orderNo);
    
    /**
     * Complete order
     */
    Result<Void> completeOrder(String orderNo);
    
    /**
     * Delete order
     */
    Result<Void> deleteOrder(Long userId, String orderNo);
    
    /**
     * Get order by order number
     */
    Result<OrderVO> getOrderByNo(String orderNo);
    
    /**
     * Get order detail
     */
    Result<OrderDetailVO> getOrderDetail(String orderNo);
    
    /**
     * Query orders with pagination
     */
    Result<PageResult<OrderVO>> queryOrders(OrderQueryRequest request);
    
    /**
     * Get user orders
     */
    Result<PageResult<OrderVO>> getUserOrders(Long userId, Integer status, PageRequest pageRequest);
    
    /**
     * Get order count by status
     */
    Result<Map<Integer, Long>> getOrderCountByStatus(Long userId);
    
    /**
     * Get order items
     */
    Result<List<OrderItemVO>> getOrderItems(String orderNo);
    
    /**
     * Check order timeout
     */
    Result<Void> checkOrderTimeout();
    
    /**
     * Auto confirm receipt
     */
    Result<Void> autoConfirmReceipt();
    
    /**
     * Auto complete order
     */
    Result<Void> autoCompleteOrder();
    
    /**
     * Get order statistics
     */
    Result<OrderStatisticsVO> getOrderStatistics();
    
    /**
     * Get user order statistics
     */
    Result<OrderStatisticsVO> getUserOrderStatistics(Long userId);
}
