package com.mall.common.nacos.discovery;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Nacos discovery properties
 */
@Data
@ConfigurationProperties(prefix = "spring.cloud.nacos.discovery")
public class NacosDiscoveryProperties {
    
    /**
     * Nacos server address
     */
    private String serverAddr = "localhost:8848";
    
    /**
     * Service name
     */
    private String service;
    
    /**
     * Service namespace
     */
    private String namespace;
    
    /**
     * Service group
     */
    private String group = "DEFAULT_GROUP";
    
    /**
     * Service cluster name
     */
    private String clusterName = "DEFAULT";
    
    /**
     * Service weight
     */
    private float weight = 1.0f;
    
    /**
     * Register enabled
     */
    private boolean registerEnabled = true;
    
    /**
     * Instance enabled
     */
    private boolean instanceEnabled = true;
    
    /**
     * Ephemeral instance
     */
    private boolean ephemeral = true;
    
    /**
     * Service metadata
     */
    private Map<String, String> metadata;
    
    /**
     * Heart beat interval (ms)
     */
    private Long heartBeatInterval = 5000L;
    
    /**
     * Heart beat timeout (ms)
     */
    private Long heartBeatTimeout = 15000L;
    
    /**
     * IP delete timeout (ms)
     */
    private Long ipDeleteTimeout = 30000L;
}