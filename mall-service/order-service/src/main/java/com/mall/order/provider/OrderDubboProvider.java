package com.mall.order.provider;

import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.api.order.dubbo.OrderDubboService;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Map;

/**
 * 订单Dubbo服务实现
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 30000)
@RequiredArgsConstructor
public class OrderDubboProvider implements OrderDubboService {
    
    private final OrderService orderService;
    
    @Override
    public Result<OrderVO> createOrder(Long userId, OrderCreateRequest request) {
        try {
            OrderVO order = orderService.createOrder(userId, request);
            return Result.success(order);
        } catch (Exception e) {
            log.error("Failed to create order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderSettlementVO> getSettlement(Long userId, OrderSettlementRequest request) {
        try {
            OrderSettlementVO settlement = orderService.getSettlement(userId, request);
            return Result.success(settlement);
        } catch (Exception e) {
            log.error("Failed to get settlement", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderPaymentVO> payOrder(OrderPaymentRequest request) {
        try {
            OrderPaymentVO payment = orderService.payOrder(request);
            return Result.success(payment);
        } catch (Exception e) {
            log.error("Failed to pay order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> paymentCallback(String orderNo, String transactionId) {
        try {
            orderService.paymentCallback(orderNo, transactionId);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to process payment callback", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> cancelOrder(Long userId, OrderCancelRequest request) {
        try {
            orderService.cancelOrder(userId, request);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to cancel order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> shipOrder(OrderShipRequest request) {
        try {
            orderService.shipOrder(request);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to ship order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> confirmReceipt(Long userId, String orderNo) {
        try {
            orderService.confirmReceipt(userId, orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to confirm receipt", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> completeOrder(String orderNo) {
        try {
            orderService.completeOrder(orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to complete order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> deleteOrder(Long userId, String orderNo) {
        try {
            orderService.deleteOrder(userId, orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to delete order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderVO> getOrderByNo(String orderNo) {
        try {
            OrderVO order = orderService.getOrderByNo(orderNo);
            return Result.success(order);
        } catch (Exception e) {
            log.error("Failed to get order by no", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderDetailVO> getOrderDetail(String orderNo) {
        try {
            OrderDetailVO detail = orderService.getOrderDetail(orderNo);
            return Result.success(detail);
        } catch (Exception e) {
            log.error("Failed to get order detail", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<PageResult<OrderVO>> queryOrders(OrderQueryRequest request) {
        try {
            PageResult<OrderVO> result = orderService.queryOrders(request);
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to query orders", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<PageResult<OrderVO>> getUserOrders(Long userId, Integer status, PageRequest pageRequest) {
        try {
            PageResult<OrderVO> result = orderService.getUserOrders(
                userId, status, pageRequest.getCurrent(), pageRequest.getSize());
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to get user orders", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Map<Integer, Long>> getOrderCountByStatus(Long userId) {
        try {
            Map<Integer, Long> counts = orderService.getOrderCountByStatus(userId);
            return Result.success(counts);
        } catch (Exception e) {
            log.error("Failed to get order count by status", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<List<OrderItemVO>> getOrderItems(String orderNo) {
        try {
            List<OrderItemVO> items = orderService.getOrderItems(orderNo);
            return Result.success(items);
        } catch (Exception e) {
            log.error("Failed to get order items", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> checkOrderTimeout() {
        try {
            orderService.checkOrderTimeout();
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to check order timeout", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> autoConfirmReceipt() {
        try {
            orderService.autoConfirmReceipt();
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to auto confirm receipt", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<Void> autoCompleteOrder() {
        try {
            orderService.autoCompleteOrder();
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to auto complete order", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderStatisticsVO> getOrderStatistics() {
        try {
            OrderStatisticsVO statistics = orderService.getOrderStatistics();
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("Failed to get order statistics", e);
            return Result.failed(e.getMessage());
        }
    }
    
    @Override
    public Result<OrderStatisticsVO> getUserOrderStatistics(Long userId) {
        try {
            OrderStatisticsVO statistics = orderService.getUserOrderStatistics(userId);
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("Failed to get user order statistics", e);
            return Result.failed(e.getMessage());
        }
    }
}