package com.mall.common.nacos.discovery;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.NamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Service health indicator for monitoring
 */
@Component("nacosDiscovery")
@RequiredArgsConstructor
public class ServiceHealthIndicator implements HealthIndicator {
    
    private final NacosServiceManager nacosServiceManager;
    private final NacosDiscoveryProperties properties;
    
    @Override
    public Health health() {
        try {
            NamingService namingService = nacosServiceManager.getNamingService(properties);
            String serverStatus = namingService.getServerStatus();
            
            if ("UP".equals(serverStatus)) {
                return Health.up()
                        .withDetail("server", properties.getServerAddr())
                        .withDetail("namespace", properties.getNamespace())
                        .withDetail("group", properties.getGroup())
                        .withDetail("service", properties.getService())
                        .build();
            } else {
                return Health.down()
                        .withDetail("server", properties.getServerAddr())
                        .withDetail("status", serverStatus)
                        .build();
            }
        } catch (Exception e) {
            return Health.down()
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}