package com.mall.api.order.feign.fallback;

import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.api.order.feign.OrderFeignClient;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Order Feign client fallback factory
 */
@Slf4j
@Component
public class OrderFeignFallbackFactory implements FallbackFactory<OrderFeignClient> {
    
    @Override
    public OrderFeignClient create(Throwable cause) {
        log.error("Order service call failed", cause);
        
        return new OrderFeignClient() {
            @Override
            public Result<OrderVO> createOrder(Long userId, OrderCreateRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderSettlementVO> getSettlement(Long userId, OrderSettlementRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderPaymentVO> payOrder(OrderPaymentRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> paymentCallback(String orderNo, String transactionId) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> cancelOrder(Long userId, OrderCancelRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> shipOrder(OrderShipRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> confirmReceipt(Long userId, String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> completeOrder(String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> deleteOrder(Long userId, String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderVO> getOrderByNo(String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderDetailVO> getOrderDetail(String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<PageResult<OrderVO>> queryOrders(OrderQueryRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<PageResult<OrderVO>> getUserOrders(Long userId, Integer status, Long current, Long size) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Map<Integer, Long>> getOrderCountByStatus(Long userId) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<List<OrderItemVO>> getOrderItems(String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> checkOrderTimeout() {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> autoConfirmReceipt() {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<Void> autoCompleteOrder() {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderStatisticsVO> getOrderStatistics() {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
            
            @Override
            public Result<OrderStatisticsVO> getUserOrderStatistics(Long userId) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Order service unavailable");
            }
        };
    }
}