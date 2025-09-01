package com.mall.common.mybatis.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.mall.common.mybatis.injector.CustomSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SQL injector configuration
 */
@Configuration
public class SqlInjectorConfig {
    
    @Bean
    public ISqlInjector sqlInjector() {
        return new CustomSqlInjector();
    }
}