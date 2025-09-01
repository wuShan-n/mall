package com.mall.api.payment.feign.fallback;

import com.mall.api.payment.dto.request.*;
import com.mall.api.payment.dto.response.*;
import com.mall.api.payment.feign.PaymentFeignClient;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * Payment Feign client fallback factory
 */
@Slf4j
@Component
public class PaymentFeignFallbackFactory implements FallbackFactory<PaymentFeignClient> {
    
    @Override
    public PaymentFeignClient create(Throwable cause) {
        log.error("Payment service call failed", cause);
        
        return new PaymentFeignClient() {
            public Result<PaymentResultVO> createPayment(PaymentCreateRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<Void> processCallback(PaymentCallbackRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<Void> processNotify(PaymentNotifyRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<PaymentVO> queryPaymentByNo(String paymentNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<PaymentVO> queryPaymentByOrderNo(String orderNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<PageResult<PaymentVO>> queryPayments(PaymentQueryRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<Void> cancelPayment(String paymentNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }

            @Override
            public Result<Void> closePayment(String paymentNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }
            
            @Override
            public Result<PaymentVO> checkPaymentStatus(String paymentNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }
            
            @Override
            public Result<String> getPaymentUrl(String paymentNo) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }
            
            @Override
            public Result<BigDecimal> calculatePaymentFee(Integer paymentType, BigDecimal amount) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }
            
            @Override
            public Result<Boolean> verifySignature(PaymentCallbackRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "Payment service unavailable");
            }
        };
    }
}