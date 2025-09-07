package com.mall.order.domain.order.event;

import com.mall.order.domain.common.DomainEvent;
import com.mall.order.domain.order.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单支付成功领域事件
 */
public class OrderPaidDomainEvent extends DomainEvent {
    
    private final Long orderId;
    private final String orderNo;
    private final Long userId;
    private final BigDecimal payAmount;
    private final Integer paymentType;
    private final String transactionId;
    private final LocalDateTime paymentTime;
    
    public OrderPaidDomainEvent(Order order, String transactionId) {
        super();
        this.orderId = order.getId();
        this.orderNo = order.getOrderNo().getValue();
        this.userId = order.getUserId().getValue();
        this.payAmount = order.getPayAmount().getAmount();
        this.paymentType = order.getPaymentType().getCode();
        this.transactionId = transactionId;
        this.paymentTime = order.getPaymentTime();
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public BigDecimal getPayAmount() {
        return payAmount;
    }
    
    public Integer getPaymentType() {
        return paymentType;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }
}