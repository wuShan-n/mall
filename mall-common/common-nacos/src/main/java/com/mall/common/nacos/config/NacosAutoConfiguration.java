package com.mall.common.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.mall.common.nacos.discovery.LoadBalancerConfiguration;

import com.mall.common.nacos.discovery.ServiceInstanceManager;
import com.mall.common.nacos.discovery.ServiceMetadataProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Nacos auto configuration
 */
@Configuration
@ConditionalOnClass(NacosConfigManager.class)
@ComponentScan(basePackages = "com.mall.common.nacos")
@Import({LoadBalancerConfiguration.class})
public class NacosAutoConfiguration {
    
    @Bean
    @RefreshScope
    @ConditionalOnProperty(
        prefix = "spring.cloud.nacos.config",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
    )
    public DynamicConfigListener dynamicConfigListener() {
        return new DynamicConfigListener();
    }
    
    @Bean
    @ConditionalOnProperty(
        prefix = "spring.cloud.nacos.discovery",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
    )
    public ServiceInstanceManager serviceInstanceManager(
            NacosServiceManager nacosServiceManager,
            NacosDiscoveryProperties properties,
            DiscoveryClient discoveryClient) {
        return new ServiceInstanceManager(nacosServiceManager, properties, discoveryClient);
    }
    
    @Bean
    public ServiceMetadataProvider serviceMetadataProvider() {
        return new ServiceMetadataProvider();
    }
}