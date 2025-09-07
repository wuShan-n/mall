package com.mall.order.infrastructure.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitMQConfig {
    
    // 订单相关
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_PAID_QUEUE = "order.paid.queue";
    public static final String ORDER_CANCELLED_QUEUE = "order.cancelled.queue";
    public static final String ORDER_COMPLETED_QUEUE = "order.completed.queue";
    
    // 退款相关
    public static final String REFUND_APPROVED_QUEUE = "refund.approved.queue";
    
    /**
     * 创建订单交换机
     */
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE, true, false);
    }
    
    /**
     * 创建订单创建队列
     */
    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE)
            .withArgument("x-message-ttl", 3600000) // 1小时过期
            .build();
    }
    
    /**
     * 创建订单支付队列
     */
    @Bean
    public Queue orderPaidQueue() {
        return QueueBuilder.durable(ORDER_PAID_QUEUE).build();
    }
    
    /**
     * 创建订单取消队列
     */
    @Bean
    public Queue orderCancelledQueue() {
        return QueueBuilder.durable(ORDER_CANCELLED_QUEUE).build();
    }
    
    /**
     * 创建订单完成队列
     */
    @Bean
    public Queue orderCompletedQueue() {
        return QueueBuilder.durable(ORDER_COMPLETED_QUEUE).build();
    }
    
    /**
     * 创建退款审批队列
     */
    @Bean
    public Queue refundApprovedQueue() {
        return QueueBuilder.durable(REFUND_APPROVED_QUEUE).build();
    }
    
    /**
     * 绑定订单创建队列
     */
    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder
            .bind(orderCreatedQueue())
            .to(orderExchange())
            .with("order.created");
    }
    
    /**
     * 绑定订单支付队列
     */
    @Bean
    public Binding orderPaidBinding() {
        return BindingBuilder
            .bind(orderPaidQueue())
            .to(orderExchange())
            .with("order.paid");
    }
    
    /**
     * 绑定订单取消队列
     */
    @Bean
    public Binding orderCancelledBinding() {
        return BindingBuilder
            .bind(orderCancelledQueue())
            .to(orderExchange())
            .with("order.cancelled");
    }
    
    /**
     * 绑定订单完成队列
     */
    @Bean
    public Binding orderCompletedBinding() {
        return BindingBuilder
            .bind(orderCompletedQueue())
            .to(orderExchange())
            .with("order.completed");
    }
    
    /**
     * 绑定退款审批队列
     */
    @Bean
    public Binding refundApprovedBinding() {
        return BindingBuilder
            .bind(refundApprovedQueue())
            .to(orderExchange())
            .with("refund.approved");
    }
    
    /**
     * 配置消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    /**
     * 配置RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
