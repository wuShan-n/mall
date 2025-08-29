package com.eco.starter.autoconfigure;

import com.eco.starter.config.EcoProperties;
import com.eco.starter.config.JacksonConfig;
import com.eco.starter.config.WebMvcConfig;
import com.eco.starter.config.AsyncConfig;
import com.eco.starter.config.Knife4jConfig;
import com.eco.starter.handler.GlobalExceptionHandler;
import com.eco.starter.interceptor.TraceInterceptor;
import com.eco.starter.aspect.LogAspect;
import com.eco.starter.filter.XssFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Eco自动配置类
 */
@AutoConfiguration
@EnableConfigurationProperties(EcoProperties.class)
@ComponentScan(basePackages = "com.eco.common.core.util")
@Import({
    JacksonConfig.class,
    WebMvcConfig.class,
    AsyncConfig.class,
    Knife4jConfig.class,
    GlobalExceptionHandler.class,
    TraceInterceptor.class,
    LogAspect.class
})
@ConditionalOnProperty(prefix = "eco", name = "enabled", havingValue = "true", matchIfMissing = true)
public class EcoAutoConfiguration {

}
