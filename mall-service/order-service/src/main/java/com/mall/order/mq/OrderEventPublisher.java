package com.mall.order.mq;

import com.mall.api.order.event.*;
import com.mall.common.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 订单事件发布器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    // Exchange和RoutingKey常量
    private static final String ORDER_EXCHANGE = "mall.order.exchange";
    private static final String ORDER_CREATED_ROUTING_KEY = "order.created";
    private static final String ORDER_PAID_ROUTING_KEY = "order.paid";
    private static final String ORDER_CANCELLED_ROUTING_KEY = "order.cancelled";
    private static final String ORDER_SHIPPED_ROUTING_KEY = "order.shipped";
    private static final String ORDER_COMPLETED_ROUTING_KEY = "order.completed";
    private static final String REFUND_APPROVED_ROUTING_KEY = "refund.approved";
    
    /**
     * 发布订单创建事件
     */
    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        try {
            log.info("Publishing order created event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CREATED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order created event", e);
        }
    }
    
    /**
     * 发布订单支付事件
     */
    public void publishOrderPaidEvent(OrderPaidEvent event) {
        try {
            log.info("Publishing order paid event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_PAID_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order paid event", e);
        }
    }
    
    /**
     * 发布订单取消事件
     */
    public void publishOrderCancelledEvent(OrderCancelledEvent event) {
        try {
            log.info("Publishing order cancelled event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CANCELLED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order cancelled event", e);
        }
    }
    
    /**
     * 发布订单发货事件
     */
    public void publishOrderShippedEvent(OrderShippedEvent event) {
        try {
            log.info("Publishing order shipped event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_SHIPPED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order shipped event", e);
        }
    }
    
    /**
     * 发布订单完成事件
     */
    public void publishOrderCompletedEvent(OrderCompletedEvent event) {
        try {
            log.info("Publishing order completed event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_COMPLETED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order completed event", e);
        }
    }
    
    /**
     * 发布退款批准事件
     */
    public void publishRefundApprovedEvent(RefundApprovedEvent event) {
        try {
            log.info("Publishing refund approved event: {}", event.getRefundNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, REFUND_APPROVED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish refund approved event", e);
        }
    }
}