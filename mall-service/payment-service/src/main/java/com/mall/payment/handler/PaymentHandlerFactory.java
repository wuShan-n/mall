package com.mall.payment.handler;

import com.mall.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Payment handler factory
 */
@Component
public class PaymentHandlerFactory {
    
    @Autowired
    private List<PaymentHandler> handlers;
    
    private final Map<Integer, PaymentHandler> handlerMap = new HashMap<>();
    
    @PostConstruct
    public void init() {
        for (PaymentHandler handler : handlers) {
            handlerMap.put(handler.getPaymentType(), handler);
        }
    }
    
    /**
     * Get payment handler by type
     */
    public PaymentHandler getHandler(Integer paymentType) {
        PaymentHandler handler = handlerMap.get(paymentType);
        if (handler == null) {
            throw new BusinessException("Unsupported payment type: " + paymentType);
        }
        return handler;
    }
}