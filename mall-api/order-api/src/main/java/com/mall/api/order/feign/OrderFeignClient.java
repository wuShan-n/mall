package com.mall.api.order.feign;

import com.mall.api.order.constant.OrderConstants;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Order Feign client for HTTP calls
 */
@FeignClient(name = OrderConstants.SERVICE_NAME, path = OrderConstants.API_PREFIX)
public interface OrderFeignClient {
    
    @PostMapping("/create")
    Result<OrderVO> createOrder(@RequestHeader("userId") Long userId,
                                @RequestBody OrderCreateRequest request);
    
    @PostMapping("/settlement")
    Result<OrderSettlementVO> getSettlement(@RequestHeader("userId") Long userId,
                                           @RequestBody OrderSettlementRequest request);
    
    @PostMapping("/pay")
    Result<OrderPaymentVO> payOrder(@RequestBody OrderPaymentRequest request);
    
    @PostMapping("/payment-callback")
    Result<Void> paymentCallback(@RequestParam("orderNo") String orderNo,
                                 @RequestParam("transactionId") String transactionId);
    
    @PostMapping("/cancel")
    Result<Void> cancelOrder(@RequestHeader("userId") Long userId,
                            @RequestBody OrderCancelRequest request);
    
    @PostMapping("/ship")
    Result<Void> shipOrder(@RequestBody OrderShipRequest request);
    
    @PostMapping("/confirm-receipt")
    Result<Void> confirmReceipt(@RequestHeader("userId") Long userId,
                               @RequestParam("orderNo") String orderNo);
    
    @PostMapping("/complete")
    Result<Void> completeOrder(@RequestParam("orderNo") String orderNo);
    
    @DeleteMapping("/{orderNo}")
    Result<Void> deleteOrder(@RequestHeader("userId") Long userId,
                            @PathVariable("orderNo") String orderNo);
    
    @GetMapping("/{orderNo}")
    Result<OrderVO> getOrderByNo(@PathVariable("orderNo") String orderNo);
    
    @GetMapping("/{orderNo}/detail")
    Result<OrderDetailVO> getOrderDetail(@PathVariable("orderNo") String orderNo);
    
    @PostMapping("/query")
    Result<PageResult<OrderVO>> queryOrders(@RequestBody OrderQueryRequest request);
    
    @GetMapping("/user/{userId}")
    Result<PageResult<OrderVO>> getUserOrders(@PathVariable("userId") Long userId,
                                             @RequestParam(value = "status", required = false) Integer status,
                                             @RequestParam(value = "current", defaultValue = "1") Long current,
                                             @RequestParam(value = "size", defaultValue = "10") Long size);
    
    @GetMapping("/user/{userId}/count")
    Result<Map<Integer, Long>> getOrderCountByStatus(@PathVariable("userId") Long userId);
    
    @GetMapping("/{orderNo}/items")
    Result<List<OrderItemVO>> getOrderItems(@PathVariable("orderNo") String orderNo);
    
    @PostMapping("/check-timeout")
    Result<Void> checkOrderTimeout();
    
    @PostMapping("/auto-confirm")
    Result<Void> autoConfirmReceipt();
    
    @PostMapping("/auto-complete")
    Result<Void> autoCompleteOrder();
    
    @GetMapping("/statistics")
    Result<OrderStatisticsVO> getOrderStatistics();
    
    @GetMapping("/statistics/user/{userId}")
    Result<OrderStatisticsVO> getUserOrderStatistics(@PathVariable("userId") Long userId);
}
