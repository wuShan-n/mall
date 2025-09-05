package com.mall.inventory.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    // Exchange
    public static final String INVENTORY_EXCHANGE = "inventory.exchange";
    
    // Queues
    public static final String ORDER_CREATED_QUEUE = "inventory.order.created.queue";
    public static final String ORDER_PAID_QUEUE = "inventory.order.paid.queue";
    public static final String ORDER_CANCELLED_QUEUE = "inventory.order.cancelled.queue";
    public static final String ORDER_RETURNED_QUEUE = "inventory.order.returned.queue";
    
    // Routing Keys
    public static final String ORDER_CREATED_KEY = "order.created";
    public static final String ORDER_PAID_KEY = "order.paid";
    public static final String ORDER_CANCELLED_KEY = "order.cancelled";
    public static final String ORDER_RETURNED_KEY = "order.returned";
    
    @Bean
    public TopicExchange inventoryExchange() {
        return new TopicExchange(INVENTORY_EXCHANGE);
    }
    
    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(ORDER_CREATED_QUEUE, true);
    }
    
    @Bean
    public Queue orderPaidQueue() {
        return new Queue(ORDER_PAID_QUEUE, true);
    }
    
    @Bean
    public Queue orderCancelledQueue() {
        return new Queue(ORDER_CANCELLED_QUEUE, true);
    }
    
    @Bean
    public Queue orderReturnedQueue() {
        return new Queue(ORDER_RETURNED_QUEUE, true);
    }
    
    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder.bind(orderCreatedQueue()).to(inventoryExchange()).with(ORDER_CREATED_KEY);
    }
    
    @Bean
    public Binding orderPaidBinding() {
        return BindingBuilder.bind(orderPaidQueue()).to(inventoryExchange()).with(ORDER_PAID_KEY);
    }
    
    @Bean
    public Binding orderCancelledBinding() {
        return BindingBuilder.bind(orderCancelledQueue()).to(inventoryExchange()).with(ORDER_CANCELLED_KEY);
    }
    
    @Bean
    public Binding orderReturnedBinding() {
        return BindingBuilder.bind(orderReturnedQueue()).to(inventoryExchange()).with(ORDER_RETURNED_KEY);
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}