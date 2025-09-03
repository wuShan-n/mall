package com.mall.order.controller;

import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "订单管理", description = "订单相关接口")
public class OrderController {
    
    private final OrderService orderService;
    
    @PostMapping("/create")
    @Operation(summary = "创建订单")
    public Result<OrderVO> createOrder(@RequestHeader("userId") Long userId,
                                       @Valid @RequestBody OrderCreateRequest request) {
        OrderVO order = orderService.createOrder(userId, request);
        return Result.success(order);
    }
    
    @PostMapping("/settlement")
    @Operation(summary = "获取订单结算信息")
    public Result<OrderSettlementVO> getSettlement(@RequestHeader("userId") Long userId,
                                                   @Valid @RequestBody OrderSettlementRequest request) {
        OrderSettlementVO settlement = orderService.getSettlement(userId, request);
        return Result.success(settlement);
    }
    
    @PostMapping("/pay")
    @Operation(summary = "支付订单")
    public Result<OrderPaymentVO> payOrder(@Valid @RequestBody OrderPaymentRequest request) {
        OrderPaymentVO payment = orderService.payOrder(request);
        return Result.success(payment);
    }
    
    @PostMapping("/payment-callback")
    @Operation(summary = "支付回调")
    public Result<Void> paymentCallback(@RequestParam String orderNo,
                                        @RequestParam String transactionId) {
        orderService.paymentCallback(orderNo, transactionId);
        return Result.success();
    }
    
    @PostMapping("/cancel")
    @Operation(summary = "取消订单")
    public Result<Void> cancelOrder(@RequestHeader("userId") Long userId,
                                   @Valid @RequestBody OrderCancelRequest request) {
        orderService.cancelOrder(userId, request);
        return Result.success();
    }
    
    @PostMapping("/ship")
    @Operation(summary = "发货")
    public Result<Void> shipOrder(@Valid @RequestBody OrderShipRequest request) {
        orderService.shipOrder(request);
        return Result.success();
    }
    
    @PostMapping("/confirm-receipt")
    @Operation(summary = "确认收货")
    public Result<Void> confirmReceipt(@RequestHeader("userId") Long userId,
                                       @RequestParam String orderNo) {
        orderService.confirmReceipt(userId, orderNo);
        return Result.success();
    }
    
    @PostMapping("/complete")
    @Operation(summary = "完成订单")
    public Result<Void> completeOrder(@RequestParam String orderNo) {
        orderService.completeOrder(orderNo);
        return Result.success();
    }
    
    @DeleteMapping("/{orderNo}")
    @Operation(summary = "删除订单")
    public Result<Void> deleteOrder(@RequestHeader("userId") Long userId,
                                   @PathVariable String orderNo) {
        orderService.deleteOrder(userId, orderNo);
        return Result.success();
    }
    
    @GetMapping("/{orderNo}")
    @Operation(summary = "获取订单信息")
    public Result<OrderVO> getOrderByNo(@PathVariable String orderNo) {
        OrderVO order = orderService.getOrderByNo(orderNo);
        return Result.success(order);
    }
    
    @GetMapping("/{orderNo}/detail")
    @Operation(summary = "获取订单详情")
    public Result<OrderDetailVO> getOrderDetail(@PathVariable String orderNo) {
        OrderDetailVO detail = orderService.getOrderDetail(orderNo);
        return Result.success(detail);
    }
    
    @PostMapping("/query")
    @Operation(summary = "查询订单列表")
    public Result<PageResult<OrderVO>> queryOrders(@Valid @RequestBody OrderQueryRequest request) {
        PageResult<OrderVO> result = orderService.queryOrders(request);
        return Result.success(result);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户订单")
    public Result<PageResult<OrderVO>> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        PageResult<OrderVO> result = orderService.getUserOrders(userId, status, current, size);
        return Result.success(result);
    }
    
    @GetMapping("/user/{userId}/count")
    @Operation(summary = "获取用户订单状态统计")
    public Result<Map<Integer, Long>> getOrderCountByStatus(@PathVariable Long userId) {
        Map<Integer, Long> counts = orderService.getOrderCountByStatus(userId);
        return Result.success(counts);
    }
    
    @GetMapping("/{orderNo}/items")
    @Operation(summary = "获取订单商品项")
    public Result<List<OrderItemVO>> getOrderItems(@PathVariable String orderNo) {
        List<OrderItemVO> items = orderService.getOrderItems(orderNo);
        return Result.success(items);
    }
    
    @GetMapping("/statistics")
    @Operation(summary = "获取订单统计")
    public Result<OrderStatisticsVO> getOrderStatistics() {
        OrderStatisticsVO statistics = orderService.getOrderStatistics();
        return Result.success(statistics);
    }
    
    @GetMapping("/statistics/user/{userId}")
    @Operation(summary = "获取用户订单统计")
    public Result<OrderStatisticsVO> getUserOrderStatistics(@PathVariable Long userId) {
        OrderStatisticsVO statistics = orderService.getUserOrderStatistics(userId);
        return Result.success(statistics);
    }
}