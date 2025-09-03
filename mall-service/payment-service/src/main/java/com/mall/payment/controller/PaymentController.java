package com.mall.payment.controller;

import com.mall.api.payment.dto.request.*;
import com.mall.api.payment.dto.response.*;
import com.mall.api.payment.dubbo.PaymentDubboService;
import com.mall.api.payment.dubbo.RefundDubboService;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Payment REST controller
 */
@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@Tag(name = "Payment Management", description = "Payment management APIs")
public class PaymentController {
    
    private final PaymentDubboService paymentDubboService;
    private final RefundDubboService refundDubboService;
    
    @PostMapping("/create")
    @Operation(summary = "Create payment order")
    public Result<PaymentResultVO> createPayment(@Valid @RequestBody PaymentCreateRequest request) {
        return paymentDubboService.createPayment(request);
    }
    
    @PostMapping("/callback")
    @Operation(summary = "Process payment callback")
    public Result<Void> processCallback(@RequestBody PaymentCallbackRequest request) {
        return paymentDubboService.processCallback(request);
    }
    
    @PostMapping("/notify")
    @Operation(summary = "Process payment notify")
    public Result<Void> processNotify(@RequestBody PaymentNotifyRequest request) {
        return paymentDubboService.processNotify(request);
    }
    
    @GetMapping("/{paymentNo}")
    @Operation(summary = "Query payment by payment number")
    public Result<PaymentVO> queryPaymentByNo(@PathVariable String paymentNo) {
        return paymentDubboService.queryPaymentByNo(paymentNo);
    }
    
    @GetMapping("/order/{orderNo}")
    @Operation(summary = "Query payment by order number")
    public Result<PaymentVO> queryPaymentByOrderNo(@PathVariable String orderNo) {
        return paymentDubboService.queryPaymentByOrderNo(orderNo);
    }
    
    @PostMapping("/query")
    @Operation(summary = "Query payments with pagination")
    public Result<PageResult<PaymentVO>> queryPayments(@RequestBody PaymentQueryRequest request) {
        return paymentDubboService.queryPayments(request);
    }
    
    @PostMapping("/{paymentNo}/cancel")
    @Operation(summary = "Cancel payment")
    public Result<Void> cancelPayment(@PathVariable String paymentNo) {
        return paymentDubboService.cancelPayment(paymentNo);
    }
    
    @PostMapping("/{paymentNo}/close")
    @Operation(summary = "Close payment")
    public Result<Void> closePayment(@PathVariable String paymentNo) {
        return paymentDubboService.closePayment(paymentNo);
    }
    
    @GetMapping("/{paymentNo}/status")
    @Operation(summary = "Check payment status")
    public Result<PaymentVO> checkPaymentStatus(@PathVariable String paymentNo) {
        return paymentDubboService.checkPaymentStatus(paymentNo);
    }
    
    @GetMapping("/{paymentNo}/url")
    @Operation(summary = "Get payment URL")
    public Result<String> getPaymentUrl(@PathVariable String paymentNo) {
        return paymentDubboService.getPaymentUrl(paymentNo);
    }
    
    @GetMapping("/fee/calculate")
    @Operation(summary = "Calculate payment fee")
    public Result<BigDecimal> calculatePaymentFee(@RequestParam Integer paymentType,
                                                   @RequestParam BigDecimal amount) {
        return paymentDubboService.calculatePaymentFee(paymentType, amount);
    }
    
    @PostMapping("/verify/signature")
    @Operation(summary = "Verify payment signature")
    public Result<Boolean> verifySignature(@RequestBody PaymentCallbackRequest request) {
        return paymentDubboService.verifySignature(request);
    }
    
    // Refund APIs
    
    @PostMapping("/refund/apply")
    @Operation(summary = "Apply for refund")
    public Result<RefundResultVO> applyRefund(@Valid @RequestBody RefundApplyRequest request) {
        return refundDubboService.applyRefund(request);
    }
    
    @PostMapping("/refund/callback")
    @Operation(summary = "Process refund callback")
    public Result<Void> processRefundCallback(@RequestBody RefundCallbackRequest request) {
        return refundDubboService.processRefundCallback(request);
    }
    
    @GetMapping("/refund/{refundNo}")
    @Operation(summary = "Query refund by refund number")
    public Result<RefundVO> queryRefundByNo(@PathVariable String refundNo) {
        return refundDubboService.queryRefundByNo(refundNo);
    }
    
    @GetMapping("/refund/payment/{paymentNo}")
    @Operation(summary = "Query refunds by payment number")
    public Result<List<RefundVO>> queryRefundsByPaymentNo(@PathVariable String paymentNo) {
        return refundDubboService.queryRefundsByPaymentNo(paymentNo);
    }
    
    @GetMapping("/refund/order/{orderNo}")
    @Operation(summary = "Query refunds by order number")
    public Result<List<RefundVO>> queryRefundsByOrderNo(@PathVariable String orderNo) {
        return refundDubboService.queryRefundsByOrderNo(orderNo);
    }
    
    @PostMapping("/refund/query")
    @Operation(summary = "Query refunds with pagination")
    public Result<PageResult<RefundVO>> queryRefunds(@RequestBody RefundQueryRequest request) {
        return refundDubboService.queryRefunds(request);
    }
    
    @PostMapping("/refund/{refundNo}/cancel")
    @Operation(summary = "Cancel refund")
    public Result<Void> cancelRefund(@PathVariable String refundNo) {
        return refundDubboService.cancelRefund(refundNo);
    }
    
    @PostMapping("/refund/{refundNo}/retry")
    @Operation(summary = "Retry refund")
    public Result<RefundResultVO> retryRefund(@PathVariable String refundNo) {
        return refundDubboService.retryRefund(refundNo);
    }
    
    @GetMapping("/refund/{refundNo}/status")
    @Operation(summary = "Check refund status")
    public Result<RefundVO> checkRefundStatus(@PathVariable String refundNo) {
        return refundDubboService.checkRefundStatus(refundNo);
    }
    
    @GetMapping("/refund/{refundNo}/detail")
    @Operation(summary = "Get refund detail")
    public Result<RefundDetailVO> getRefundDetail(@PathVariable String refundNo) {
        return refundDubboService.getRefundDetail(refundNo);
    }
}