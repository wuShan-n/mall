package com.mall.api.payment.feign;

import com.mall.api.payment.constant.PaymentConstants;
import com.mall.api.payment.dto.request.PaymentCallbackRequest;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.request.PaymentNotifyRequest;
import com.mall.api.payment.dto.request.PaymentQueryRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dto.response.PaymentVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Payment Feign client for HTTP calls
 */
@FeignClient(name = PaymentConstants.SERVICE_NAME, path = PaymentConstants.API_PREFIX)
public interface PaymentFeignClient {

    @PostMapping("/create")
    Result<PaymentResultVO> createPayment(@RequestBody PaymentCreateRequest request);

    @PostMapping("/callback")
    Result<Void> processCallback(@RequestBody PaymentCallbackRequest request);

    @PostMapping("/notify")
    Result<Void> processNotify(@RequestBody PaymentNotifyRequest request);

    @GetMapping("/{paymentNo}")
    Result<PaymentVO> queryPaymentByNo(@PathVariable("paymentNo") String paymentNo);

    @GetMapping("/order/{orderNo}")
    Result<PaymentVO> queryPaymentByOrderNo(@PathVariable("orderNo") String orderNo);

    @PostMapping("/query")
    Result<PageResult<PaymentVO>> queryPayments(@RequestBody PaymentQueryRequest request);

    @PostMapping("/{paymentNo}/cancel")
    Result<Void> cancelPayment(@PathVariable("paymentNo") String paymentNo);

    @PostMapping("/{paymentNo}/close")
    Result<Void> closePayment(@PathVariable("paymentNo") String paymentNo);

    @GetMapping("/{paymentNo}/status")
    Result<PaymentVO> checkPaymentStatus(@PathVariable("paymentNo") String paymentNo);

    @GetMapping("/{paymentNo}/url")
    Result<String> getPaymentUrl(@PathVariable("paymentNo") String paymentNo);

    @GetMapping("/fee/calculate")
    Result<BigDecimal> calculatePaymentFee(@RequestParam("paymentType") Integer paymentType,
            @RequestParam("amount") BigDecimal amount);

    @PostMapping("/verify/signature")
    Result<Boolean> verifySignature(@RequestBody PaymentCallbackRequest request);
}
