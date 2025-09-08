package com.mall.order.interfaces.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.order.application.service.OrderCreationApplicationService;
import com.mall.order.application.service.OrderPaymentApplicationService;
import com.mall.order.application.service.OrderLifecycleApplicationService;
import com.mall.order.application.service.OrderQueryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Map;

/**
 * 订单REST控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "订单管理", description = "订单相关接口")
public class OrderController {
    
    private final OrderCreationApplicationService orderCreationApplicationService;
    private final OrderPaymentApplicationService orderPaymentApplicationService;
    private final OrderLifecycleApplicationService orderLifecycleApplicationService;
    private final OrderQueryApplicationService orderQueryApplicationService;
    
    @PostMapping("/create")
    @Operation(summary = "创建订单")
    @SaCheckLogin
    public Result<OrderVO> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        Long userId = StpUtil.getLoginIdAsLong();
        OrderVO order = orderCreationApplicationService.createOrder(userId, request);
        return Result.success(order);
    }
    
    @PostMapping("/settlement")
    @Operation(summary = "获取订单结算信息")
    @SaCheckLogin
    public Result<OrderSettlementVO> getSettlement(@Valid @RequestBody OrderSettlementRequest request) {
        Long userId = StpUtil.getLoginIdAsLong();
        // TODO: 实现结算信息获取
        return Result.success();
    }
    
    @PostMapping("/pay")
    @Operation(summary = "支付订单")
    @SaCheckLogin
    public Result<OrderPaymentVO> payOrder(@Valid @RequestBody OrderPaymentRequest request) {
        OrderPaymentVO payment = orderPaymentApplicationService.initiatePayment(request);
        return Result.success(payment);
    }
    
    @PostMapping("/payment-callback")
    @Operation(summary = "支付回调")
    public Result<Void> paymentCallback(@RequestParam String orderNo,
                                        @RequestParam String transactionId) {
        orderPaymentApplicationService.handlePaymentCallback(orderNo, transactionId);
        return Result.success();
    }
    
    @PostMapping("/cancel")
    @Operation(summary = "取消订单")
    @SaCheckLogin
    public Result<Void> cancelOrder(@Valid @RequestBody OrderCancelRequest request) {
        Long userId = StpUtil.getLoginIdAsLong();
        orderLifecycleApplicationService.cancelOrder(userId, request);
        return Result.success();
    }
    
    @PostMapping("/ship")
    @Operation(summary = "发货")
    public Result<Void> shipOrder(@Valid @RequestBody OrderShipRequest request) {
        orderLifecycleApplicationService.shipOrder(request);
        return Result.success();
    }
    
    @PostMapping("/confirm-receipt")
    @Operation(summary = "确认收货")
    @SaCheckLogin
    public Result<Void> confirmReceipt(@RequestParam String orderNo) {
        Long userId = StpUtil.getLoginIdAsLong();
        orderLifecycleApplicationService.confirmReceipt(userId, orderNo);
        return Result.success();
    }
    
    @GetMapping("/{orderNo}")
    @Operation(summary = "获取订单详情")
    @SaCheckLogin
    public Result<OrderDetailVO> getOrderDetail(@PathVariable String orderNo) {
        OrderDetailVO detail = orderQueryApplicationService.getOrderDetailForUser(orderNo, StpUtil.getLoginIdAsLong());
        return Result.success(detail);
    }
    
    @GetMapping("/user/{userId}")
    @Operation(summary = "获取用户订单")
    @SaCheckLogin
    public Result<PageResult<OrderVO>> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size) {
        
        PageResult<OrderVO> result = orderQueryApplicationService.getUserOrders(userId, status, current, size);
        return Result.success(result);
    }
    
    @GetMapping("/user/{userId}/count")
    @Operation(summary = "获取用户订单统计")
    @SaCheckLogin
    public Result<Map<Integer, Long>> getOrderCountByStatus(@PathVariable Long userId) {
        Map<Integer, Long> counts = orderQueryApplicationService.getOrderCountByStatus(userId);
        return Result.success(counts);
    }
}