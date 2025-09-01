package com.mall.common.nacos.congfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Nacos configuration properties
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.nacos.config")
public class NacosConfigProperties {
    
    /**
     * Nacos server address
     */
    private String serverAddr = "localhost:8848";
    
    /**
     * Namespace for multi-environment isolation
     */
    private String namespace;
    
    /**
     * Configuration group
     */
    private String group = "DEFAULT_GROUP";
    
    /**
     * Configuration file extension
     */
    private String fileExtension = "yaml";
    
    /**
     * Configuration refresh enabled
     */
    private boolean refreshEnabled = true;
    
    /**
     * Shared configurations
     */
    private List<SharedConfig> sharedConfigs;
    
    /**
     * Extension configurations
     */
    private List<ExtensionConfig> extensionConfigs;
    
    @Data
    public static class SharedConfig {
        private String dataId;
        private String group = "DEFAULT_GROUP";
        private boolean refresh = true;
    }
    
    @Data
    public static class ExtensionConfig {
        private String dataId;
        private String group = "DEFAULT_GROUP";
        private boolean refresh = true;
    }
}
