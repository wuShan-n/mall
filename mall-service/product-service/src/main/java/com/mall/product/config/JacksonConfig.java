package com.mall.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary // 将这个 ObjectMapper 设置为首选的 Bean
    public ObjectMapper objectMapper() {
        // 1. 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 2. 创建并注册 JavaTimeModule
        objectMapper.registerModule(new JavaTimeModule());

        // 3. 禁用将日期序列化为时间戳的行为，使其输出为 "2024-09-02T14:30:00" 格式
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 您还可以在这里进行其他自定义配置...
        // 例如：忽略未知的 JSON 字段
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}