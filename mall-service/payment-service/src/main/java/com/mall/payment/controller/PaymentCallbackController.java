package com.mall.payment.controller;

import com.mall.api.payment.dto.request.PaymentCallbackRequest;
import com.mall.api.payment.dto.request.RefundCallbackRequest;
import com.mall.api.payment.dubbo.PaymentDubboService;
import com.mall.api.payment.dubbo.RefundDubboService;
import com.mall.api.payment.enums.PaymentTypeEnum;
import com.mall.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Payment callback controller for third-party payment notifications
 */
@Slf4j
@RestController
@RequestMapping("/callback")
@RequiredArgsConstructor
@Tag(name = "Payment Callback", description = "Payment callback APIs")
public class PaymentCallbackController {
    
    private final PaymentDubboService paymentDubboService;
    private final RefundDubboService refundDubboService;
    
    @PostMapping("/alipay/notify")
    @Operation(summary = "Alipay payment notify callback")
    public String alipayNotify(HttpServletRequest request) {
        log.info("Received Alipay payment notify");
        
        try {
            // Parse Alipay notification parameters
            Map<String, String> params = parseRequestParams(request);
            
            // Build callback request
            PaymentCallbackRequest callbackRequest = new PaymentCallbackRequest();
            callbackRequest.setPaymentNo(params.get("out_trade_no"));
            callbackRequest.setTransactionId(params.get("trade_no"));
            callbackRequest.setPaymentType(PaymentTypeEnum.ALIPAY.getCode());
            callbackRequest.setStatus(params.get("trade_status"));
            callbackRequest.setPaymentAmount(new BigDecimal(params.get("total_amount")));
            callbackRequest.setActualAmount(new BigDecimal(params.get("receipt_amount")));
            callbackRequest.setPaymentTime(LocalDateTime.now()); // Parse from params in production
            callbackRequest.setSignature(params.get("sign"));
            callbackRequest.setCallbackParams(params);
            
            // Verify signature
            Result<Boolean> verifyResult = paymentDubboService.verifySignature(callbackRequest);
            if (!verifyResult.isSuccess() || !verifyResult.getData()) {
                log.error("Invalid Alipay signature");
                return "fail";
            }
            
            // Process callback
            Result<Void> result = paymentDubboService.processCallback(callbackRequest);
            
            if (result.isSuccess()) {
                return "success";
            } else {
                return "fail";
            }
            
        } catch (Exception e) {
            log.error("Error processing Alipay notify", e);
            return "fail";
        }
    }
    
    @GetMapping("/alipay/return")
    @Operation(summary = "Alipay payment return callback")
    public String alipayReturn(HttpServletRequest request) {
        log.info("Received Alipay payment return");
        
        try {
            // Parse return parameters
            Map<String, String> params = parseRequestParams(request);
            String orderNo = params.get("out_trade_no");
            
            // Redirect to order detail page or payment result page
            return "redirect:/order/detail?orderNo=" + orderNo;
            
        } catch (Exception e) {
            log.error("Error processing Alipay return", e);
            return "redirect:/payment/error";
        }
    }
    
    @PostMapping("/wechat/notify")
    @Operation(summary = "WeChat payment notify callback")
    public Map<String, String> wechatNotify(@RequestBody String xmlData) {
        log.info("Received WeChat payment notify");
        
        Map<String, String> response = new HashMap<>();
        
        try {
            // TODO: Parse WeChat XML notification
            // This is a simplified implementation
            
            response.put("return_code", "SUCCESS");
            response.put("return_msg", "OK");
            
        } catch (Exception e) {
            log.error("Error processing WeChat notify", e);
            response.put("return_code", "FAIL");
            response.put("return_msg", "Error processing notification");
        }
        
        return response;
    }
    
    @PostMapping("/alipay/refund/notify")
    @Operation(summary = "Alipay refund notify callback")
    public String alipayRefundNotify(HttpServletRequest request) {
        log.info("Received Alipay refund notify");
        
        try {
            // Parse Alipay refund notification parameters
            Map<String, String> params = parseRequestParams(request);
            
            // Build refund callback request
            RefundCallbackRequest callbackRequest = new RefundCallbackRequest();
            callbackRequest.setRefundNo(params.get("out_biz_no"));
            callbackRequest.setThirdPartyRefundNo(params.get("trade_no"));
            callbackRequest.setPaymentType(PaymentTypeEnum.ALIPAY.getCode());
            callbackRequest.setStatus(params.get("refund_status"));
            callbackRequest.setRefundAmount(new BigDecimal(params.get("refund_amount")));
            callbackRequest.setRefundTime(LocalDateTime.now()); // Parse from params in production
            callbackRequest.setSignature(params.get("sign"));
            callbackRequest.setCallbackParams(params);
            
            // Process refund callback
            Result<Void> result = refundDubboService.processRefundCallback(callbackRequest);
            
            if (result.isSuccess()) {
                return "success";
            } else {
                return "fail";
            }
            
        } catch (Exception e) {
            log.error("Error processing Alipay refund notify", e);
            return "fail";
        }
    }
    
    private Map<String, String> parseRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";
            
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            
            params.put(name, valueStr);
        }
        
        return params;
    }
}