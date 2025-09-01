package com.mall.common.nacos.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.stereotype.Component;

/**
 * Dynamic configuration listener
 */
@Slf4j
@Component
public class DynamicConfigListener {
    
    @Autowired
    private ContextRefresher contextRefresher;
    
    /**
     * Listen for application configuration changes
     */
    @NacosConfigListener(dataId = "${spring.application.name}.yaml", groupId = "${spring.cloud.nacos.config.group:DEFAULT_GROUP}")
    public void onApplicationConfigChange(String config) {
        log.info("Application configuration changed, refreshing context...");
        contextRefresher.refresh();
        log.info("Context refresh completed");
    }
    
    /**
     * Listen for shared configuration changes
     */
    @NacosConfigListener(dataId = "shared-config.yaml", groupId = "SHARED_GROUP")
    public void onSharedConfigChange(String config) {
        log.info("Shared configuration changed: {}", config);
        contextRefresher.refresh();
    }
}