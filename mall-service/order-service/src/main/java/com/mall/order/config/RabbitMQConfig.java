package com.mall.order.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitMQConfig {
    
    // Exchange定义
    public static final String ORDER_EXCHANGE = "mall.order.exchange";
    public static final String ORDER_DLX_EXCHANGE = "mall.order.dlx.exchange";
    public static final String ORDER_DELAY_EXCHANGE = "mall.order.delay.exchange";
    
    // Queue定义
    public static final String ORDER_CREATE_QUEUE = "mall.order.create.queue";
    public static final String ORDER_PAID_QUEUE = "mall.order.paid.queue";
    public static final String ORDER_CANCEL_QUEUE = "mall.order.cancel.queue";
    public static final String ORDER_TIMEOUT_QUEUE = "mall.order.timeout.queue";
    public static final String ORDER_DLX_QUEUE = "mall.order.dlx.queue";
    
    // RoutingKey定义
    public static final String ORDER_CREATE_KEY = "order.created";
    public static final String ORDER_PAID_KEY = "order.paid";
    public static final String ORDER_CANCEL_KEY = "order.cancelled";
    public static final String ORDER_TIMEOUT_KEY = "order.timeout";
    
    /**
     * 消息转换器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    /**
     * RabbitTemplate配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        // 设置消息发送确认
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.err.println("消息发送失败: " + cause);
            }
        });
        return template;
    }
    
    /**
     * 订单交换机
     */
    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE, true, false);
    }
    
    /**
     * 死信交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(ORDER_DLX_EXCHANGE, true, false);
    }
    
    /**
     * 延迟交换机（用于订单超时处理）
     */
    @Bean
    public CustomExchange delayExchange() {
        return new CustomExchange(ORDER_DELAY_EXCHANGE, "x-delayed-message", true, false,
                Map.of("x-delayed-type", "topic"));
    }
    
    /**
     * 订单创建队列
     */
    @Bean
    public Queue orderCreateQueue() {
        return QueueBuilder.durable(ORDER_CREATE_QUEUE)
                .withArgument("x-dead-letter-exchange", ORDER_DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", "dlx")
                .build();
    }
    
    /**
     * 订单支付队列
     */
    @Bean
    public Queue orderPaidQueue() {
        return QueueBuilder.durable(ORDER_PAID_QUEUE)
                .withArgument("x-dead-letter-exchange", ORDER_DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", "dlx")
                .build();
    }
    
    /**
     * 订单取消队列
     */
    @Bean
    public Queue orderCancelQueue() {
        return QueueBuilder.durable(ORDER_CANCEL_QUEUE).build();
    }
    
    /**
     * 订单超时队列
     */
    @Bean
    public Queue orderTimeoutQueue() {
        return QueueBuilder.durable(ORDER_TIMEOUT_QUEUE).build();
    }
    
    /**
     * 死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable(ORDER_DLX_QUEUE).build();
    }
    
    /**
     * 绑定订单创建队列到交换机
     */
    @Bean
    public Binding bindingOrderCreate() {
        return BindingBuilder
                .bind(orderCreateQueue())
                .to(orderExchange())
                .with(ORDER_CREATE_KEY);
    }
    
    /**
     * 绑定订单支付队列到交换机
     */
    @Bean
    public Binding bindingOrderPaid() {
        return BindingBuilder
                .bind(orderPaidQueue())
                .to(orderExchange())
                .with(ORDER_PAID_KEY);
    }
    
    /**
     * 绑定订单取消队列到交换机
     */
    @Bean
    public Binding bindingOrderCancel() {
        return BindingBuilder
                .bind(orderCancelQueue())
                .to(orderExchange())
                .with(ORDER_CANCEL_KEY);
    }
    
    /**
     * 绑定死信队列到死信交换机
     */
    @Bean
    public Binding bindingDlx() {
        return BindingBuilder
                .bind(dlxQueue())
                .to(dlxExchange())
                .with("dlx");
    }
    
    /**
     * 绑定订单超时队列到延迟交换机
     */
    @Bean
    public Binding bindingOrderTimeout() {
        return BindingBuilder
                .bind(orderTimeoutQueue())
                .to(delayExchange())
                .with(ORDER_TIMEOUT_KEY)
                .noargs();
    }
}