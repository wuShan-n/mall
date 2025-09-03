package com.mall.payment.config;

import cn.dev33.satoken.stp.StpInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Payment service configuration
 */
@Configuration
public class PaymentConfig {
    
    /**
     * Sa-Token permission configuration
     */
    @Bean
    public StpInterface stpInterface() {
        return new StpInterface() {
            @Override
            public List<String> getPermissionList(Object loginId, String loginType) {
                // TODO: Get permissions from database or cache
                return new ArrayList<>();
            }

            @Override
            public List<String> getRoleList(Object loginId, String loginType) {
                // TODO: Get roles from database or cache
                return new ArrayList<>();
            }
        };
    }
    
    /**
     * RabbitMQ configuration
     */
    @Bean
    public Queue paymentSuccessQueue() {
        return QueueBuilder.durable("payment.success.queue")
                .withArgument("x-message-ttl", 60000)
                .build();
    }
    
    @Bean
    public Queue paymentFailedQueue() {
        return QueueBuilder.durable("payment.failed.queue")
                .withArgument("x-message-ttl", 60000)
                .build();
    }
    
    @Bean
    public Queue refundSuccessQueue() {
        return QueueBuilder.durable("refund.success.queue")
                .withArgument("x-message-ttl", 60000)
                .build();
    }
    
    @Bean
    public Queue refundFailedQueue() {
        return QueueBuilder.durable("refund.failed.queue")
                .withArgument("x-message-ttl", 60000)
                .build();
    }
    
    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange("payment.exchange", true, false);
    }
    
    @Bean
    public DirectExchange refundExchange() {
        return new DirectExchange("refund.exchange", true, false);
    }
    
    @Bean
    public Binding paymentSuccessBinding(Queue paymentSuccessQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(paymentSuccessQueue)
                .to(paymentExchange)
                .with("payment.success");
    }
    
    @Bean
    public Binding paymentFailedBinding(Queue paymentFailedQueue, DirectExchange paymentExchange) {
        return BindingBuilder.bind(paymentFailedQueue)
                .to(paymentExchange)
                .with("payment.failed");
    }
    
    @Bean
    public Binding refundSuccessBinding(Queue refundSuccessQueue, DirectExchange refundExchange) {
        return BindingBuilder.bind(refundSuccessQueue)
                .to(refundExchange)
                .with("refund.success");
    }
    
    @Bean
    public Binding refundFailedBinding(Queue refundFailedQueue, DirectExchange refundExchange) {
        return BindingBuilder.bind(refundFailedQueue)
                .to(refundExchange)
                .with("refund.failed");
    }
    
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, 
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        template.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                // Handle message send failure
                System.err.println("Message send failed: " + cause);
            }
        });
        return template;
    }
}