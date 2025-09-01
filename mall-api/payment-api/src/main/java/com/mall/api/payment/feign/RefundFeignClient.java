package com.mall.api.payment.feign;

import com.mall.api.payment.constant.PaymentConstants;
import com.mall.api.payment.dto.request.*;
import com.mall.api.payment.dto.response.*;
import com.mall.common.result.Result;
import com.mall.common.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Refund Feign client for HTTP calls
 */
@FeignClient(name = PaymentConstants.SERVICE_NAME, path = PaymentConstants.API_PREFIX + "/refund")
public interface RefundFeignClient {

    @PostMapping("/apply")
    Result<RefundResultVO> applyRefund(@RequestBody RefundApplyRequest request);

    @PostMapping("/callback")
    Result<Void> processRefundCallback(@RequestBody RefundCallbackRequest request);

    @GetMapping("/{refundNo}")
    Result<RefundVO> queryRefundByNo(@PathVariable("refundNo") String refundNo);

    @GetMapping("/payment/{paymentNo}")
    Result<List<RefundVO>> queryRefundsByPaymentNo(@PathVariable("paymentNo") String paymentNo);

    @GetMapping("/order/{orderNo}")
    Result<List<RefundVO>> queryRefundsByOrderNo(@PathVariable("orderNo") String orderNo);

    @PostMapping("/query")
    Result<PageResult<RefundVO>> queryRefunds(@RequestBody RefundQueryRequest request);

    @PostMapping("/{refundNo}/cancel")
    Result<Void> cancelRefund(@PathVariable("refundNo") String refundNo);

    @PostMapping("/{refundNo}/retry")
    Result<RefundResultVO> retryRefund(@PathVariable("refundNo") String refundNo);

    @GetMapping("/{refundNo}/status")
    Result<RefundVO> checkRefundStatus(@PathVariable("refundNo") String refundNo);

    @GetMapping("/{refundNo}/detail")
    Result<RefundDetailVO> getRefundDetail(@PathVariable("refundNo") String refundNo);
}
