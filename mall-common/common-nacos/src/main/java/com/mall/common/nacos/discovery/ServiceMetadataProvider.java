package com.mall.common.nacos.discovery;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Service metadata provider
 */
@Slf4j
@Component
public class ServiceMetadataProvider {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Value("${spring.profiles.active:default}")
    private String profile;
    
    @Value("${spring.application.version:1.0.0}")
    private String version;
    
    /**
     * Get service metadata
     */
    public Map<String, String> getMetadata() {
        Map<String, String> metadata = new HashMap<>();
        
        metadata.put("application", applicationName);
        metadata.put("profile", profile);
        metadata.put("version", version);
        metadata.put("startTime", String.valueOf(System.currentTimeMillis()));
        
        try {
            InetAddress addr = InetAddress.getLocalHost();
            metadata.put("hostname", addr.getHostName());
            metadata.put("ip", addr.getHostAddress());
        } catch (Exception e) {
            log.warn("Failed to get host information", e);
        }
        
        // Add JVM information
        metadata.put("jvm.version", System.getProperty("java.version"));
        metadata.put("jvm.vendor", System.getProperty("java.vendor"));
        metadata.put("os.name", System.getProperty("os.name"));
        metadata.put("os.version", System.getProperty("os.version"));
        
        return metadata;
    }
}