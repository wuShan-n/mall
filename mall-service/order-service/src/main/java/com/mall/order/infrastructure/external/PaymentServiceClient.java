package com.mall.order.infrastructure.external;

import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dubbo.PaymentDubboService;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 支付服务客户端
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentServiceClient {
    
    @DubboReference(version = "1.0.0", check = false)
    private PaymentDubboService paymentDubboService;
    
    /**
     * 创建支付
     */
    public PaymentResultVO createPayment(String orderNo, BigDecimal amount, Integer paymentType) {
        try {
            PaymentCreateRequest request = new PaymentCreateRequest();
            request.setOrderNo(orderNo);
            request.setPaymentAmount(amount);
            request.setPaymentType(paymentType);
            request.setSubject("订单支付");
            request.setDescription("商品订单支付");
            
            Result<PaymentResultVO> result = paymentDubboService.createPayment(request);
            if (!result.isSuccess()) {
                throw new RuntimeException("创建支付失败: " + result.getMessage());
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Create payment failed: {}", e.getMessage(), e);
            throw new RuntimeException("创建支付失败", e);
        }
    }
}