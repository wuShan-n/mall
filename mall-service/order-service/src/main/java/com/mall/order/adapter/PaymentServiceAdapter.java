package com.mall.order.adapter;

import com.mall.api.payment.dubbo.PaymentDubboService;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dto.response.PaymentVO;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付服务适配器 封装支付相关的Dubbo调用，保持订单服务的独立性
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentServiceAdapter {

    @DubboReference(version = "1.0.0", group = "mall")
    private PaymentDubboService paymentDubboService;

    /**
     * 创建支付订单
     */
    public PaymentResultVO createPayment(String orderNo, Long userId, Integer paymentType,
            BigDecimal paymentAmount, String subject, String description,
            String returnUrl, String notifyUrl, LocalDateTime expireTime) {
        PaymentCreateRequest request = new PaymentCreateRequest();
        request.setOrderNo(orderNo);
        request.setUserId(userId);
        request.setPaymentType(paymentType);
        request.setPaymentAmount(paymentAmount);
        request.setCurrency("CNY");
        request.setSubject(subject);
        request.setDescription(description);
        request.setReturnUrl(returnUrl);
        request.setNotifyUrl(notifyUrl);
        request.setExpireTime(expireTime);

        Result<PaymentResultVO> result = paymentDubboService.createPayment(request);
        if (!result.isSuccess()) {
            throw new BusinessException("创建支付订单失败: " + result.getMessage());
        }

        log.info("成功创建支付订单: orderNo={}, paymentAmount={}", orderNo, paymentAmount);
        return result.getData();
    }

    /**
     * 查询支付状态
     */
    public PaymentVO queryPaymentByOrderNo(String orderNo) {
        Result<PaymentVO> result = paymentDubboService.queryPaymentByOrderNo(orderNo);
        if (!result.isSuccess()) {
            log.error("查询支付状态失败: orderNo={}, error={}", orderNo, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 查询支付状态（通过支付单号）
     */
    public PaymentVO queryPaymentByNo(String paymentNo) {
        Result<PaymentVO> result = paymentDubboService.queryPaymentByNo(paymentNo);
        if (!result.isSuccess()) {
            log.error("查询支付状态失败: paymentNo={}, error={}", paymentNo, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 取消支付
     */
    public void cancelPayment(String paymentNo) {
        try {
            Result<Void> result = paymentDubboService.cancelPayment(paymentNo);
            if (!result.isSuccess()) {
                log.error("取消支付失败: paymentNo={}, error={}", paymentNo, result.getMessage());
            } else {
                log.info("成功取消支付: paymentNo={}", paymentNo);
            }
        } catch (Exception e) {
            log.error("取消支付异常: paymentNo={}", paymentNo, e);
        }
    }

    /**
     * 关闭支付
     */
    public void closePayment(String paymentNo) {
        try {
            Result<Void> result = paymentDubboService.closePayment(paymentNo);
            if (!result.isSuccess()) {
                log.error("关闭支付失败: paymentNo={}, error={}", paymentNo, result.getMessage());
            } else {
                log.info("成功关闭支付: paymentNo={}", paymentNo);
            }
        } catch (Exception e) {
            log.error("关闭支付异常: paymentNo={}", paymentNo, e);
        }
    }

    /**
     * 检查支付状态
     */
    public PaymentVO checkPaymentStatus(String paymentNo) {
        Result<PaymentVO> result = paymentDubboService.checkPaymentStatus(paymentNo);
        if (!result.isSuccess()) {
            log.error("检查支付状态失败: paymentNo={}, error={}", paymentNo, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 计算支付手续费
     */
    public BigDecimal calculatePaymentFee(Integer paymentType, BigDecimal amount) {
        Result<BigDecimal> result = paymentDubboService.calculatePaymentFee(paymentType, amount);
        if (!result.isSuccess()) {
            log.error("计算支付手续费失败: paymentType={}, amount={}, error={}",
                    paymentType, amount, result.getMessage());
            return BigDecimal.ZERO;
        }
        return result.getData();
    }

    /**
     * 获取支付URL
     */
    public String getPaymentUrl(String paymentNo) {
        Result<String> result = paymentDubboService.getPaymentUrl(paymentNo);
        if (!result.isSuccess()) {
            log.error("获取支付URL失败: paymentNo={}, error={}", paymentNo, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 验证支付是否成功
     */
    public boolean isPaymentSuccess(String orderNo) {
        PaymentVO payment = queryPaymentByOrderNo(orderNo);
        if (payment == null) {
            return false;
        }

        // 假设状态码1表示支付成功
        return payment.getStatus() != null && payment.getStatus().equals(1);
    }

    /**
     * 验证支付金额
     */
    public boolean validatePaymentAmount(String orderNo, BigDecimal expectedAmount) {
        PaymentVO payment = queryPaymentByOrderNo(orderNo);
        if (payment == null) {
            return false;
        }

        return payment.getPaymentAmount() != null
                && payment.getPaymentAmount().compareTo(expectedAmount) == 0;
    }
}
