package com.mall.common.nacos.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Nacos configuration manager wrapper
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NacosConfigHelper {

    private final NacosConfigManager nacosConfigManager;

    /**
     * Get configuration
     */
    public String getConfig(String dataId, String group) {
        try {
            return nacosConfigManager.getConfigService()
                    .getConfig(dataId, group, 5000);
        } catch (NacosException e) {
            log.error("Failed to get config, dataId: {}, group: {}", dataId, group, e);
            return null;
        }
    }

    /**
     * Publish configuration
     */
    public boolean publishConfig(String dataId, String group, String content) {
        try {
            return nacosConfigManager.getConfigService()
                    .publishConfig(dataId, group, content);
        } catch (NacosException e) {
            log.error("Failed to publish config, dataId: {}, group: {}", dataId, group, e);
            return false;
        }
    }

    /**
     * Remove configuration
     */
    public boolean removeConfig(String dataId, String group) {
        try {
            return nacosConfigManager.getConfigService()
                    .removeConfig(dataId, group);
        } catch (NacosException e) {
            log.error("Failed to remove config, dataId: {}, group: {}", dataId, group, e);
            return false;
        }
    }

    /**
     * Add configuration listener
     */
    public void addListener(String dataId, String group, Listener listener) {
        try {
            nacosConfigManager.getConfigService()
                    .addListener(dataId, group, listener);
            log.info("Added listener for dataId: {}, group: {}", dataId, group);
        } catch (NacosException e) {
            log.error("Failed to add listener, dataId: {}, group: {}", dataId, group, e);
        }
    }

    /**
     * Remove configuration listener
     */
    public void removeListener(String dataId, String group, Listener listener) {
        nacosConfigManager.getConfigService()
                .removeListener(dataId, group, listener);
        log.info("Removed listener for dataId: {}, group: {}", dataId, group);
    }
}