package com.mall.order.infrastructure.messaging;

import com.mall.api.order.event.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 订单事件发布器
 * 负责发布订单相关的领域事件到消息队列
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventPublisher {
    
    private final RabbitTemplate rabbitTemplate;
    
    // 交换机和路由键定义
    private static final String ORDER_EXCHANGE = "order.exchange";
    private static final String ORDER_CREATED_ROUTING_KEY = "order.created";
    private static final String ORDER_PAID_ROUTING_KEY = "order.paid";
    private static final String ORDER_CANCELLED_ROUTING_KEY = "order.cancelled";
    private static final String ORDER_COMPLETED_ROUTING_KEY = "order.completed";
    private static final String REFUND_APPROVED_ROUTING_KEY = "refund.approved";
    
    /**
     * 发布订单创建事件
     */
    public void publishOrderCreated(OrderCreatedEvent event) {
        try {
            log.info("Publishing order created event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CREATED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order created event: {}", event.getOrderNo(), e);
        }
    }
    
    /**
     * 发布订单支付成功事件
     */
    public void publishOrderPaid(OrderPaidEvent event) {
        try {
            log.info("Publishing order paid event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_PAID_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order paid event: {}", event.getOrderNo(), e);
        }
    }
    
    /**
     * 发布订单取消事件
     */
    public void publishOrderCancelled(OrderCancelledEvent event) {
        try {
            log.info("Publishing order cancelled event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_CANCELLED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order cancelled event: {}", event.getOrderNo(), e);
        }
    }
    
    /**
     * 发布订单完成事件
     */
    public void publishOrderCompleted(OrderCompletedEvent event) {
        try {
            log.info("Publishing order completed event: {}", event.getOrderNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, ORDER_COMPLETED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish order completed event: {}", event.getOrderNo(), e);
        }
    }
    
    /**
     * 发布退款审批通过事件
     */
    public void publishRefundApproved(RefundApprovedEvent event) {
        try {
            log.info("Publishing refund approved event: {}", event.getRefundNo());
            rabbitTemplate.convertAndSend(ORDER_EXCHANGE, REFUND_APPROVED_ROUTING_KEY, event);
        } catch (Exception e) {
            log.error("Failed to publish refund approved event: {}", event.getRefundNo(), e);
        }
    }
}