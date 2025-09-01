package com.mall.common.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.mall.common.mybatis.handler.MallTenantHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


/**
 * Multi-tenant configuration
 */
@Configuration
@ConditionalOnProperty(name = "mybatis-plus.tenant.enabled", havingValue = "true")
public class TenantConfig {
    
    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;
    
    @Autowired
    private MallTenantHandler tenantHandler;
    
    @PostConstruct
    public void addTenantInterceptor() {
        TenantLineInnerInterceptor tenantInterceptor = new TenantLineInnerInterceptor();
        tenantInterceptor.setTenantLineHandler(tenantHandler);
        
        // Add tenant interceptor as the first interceptor
        mybatisPlusInterceptor.getInterceptors().add(0, tenantInterceptor);
    }
}