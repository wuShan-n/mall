package com.mall.common.nacos.discovery;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service instance manager
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ServiceInstanceManager {
    
    private final NacosServiceManager nacosServiceManager;
    private final NacosDiscoveryProperties properties;
    private final DiscoveryClient discoveryClient;
    
    /**
     * Get all healthy instances of a service
     */
    public List<Instance> getHealthyInstances(String serviceName) {
        try {
            NamingService namingService = nacosServiceManager.getNamingService(properties.getNacosProperties());
            return namingService.selectInstances(serviceName, properties.getGroup(), true);
        } catch (NacosException e) {
            log.error("Failed to get healthy instances for service: {}", serviceName, e);
            return List.of();
        }
    }
    
    /**
     * Get all instances of a service
     */
    public List<Instance> getAllInstances(String serviceName) {
        try {
            NamingService namingService = nacosServiceManager.getNamingService(properties.getNacosProperties());
            return namingService.getAllInstances(serviceName, properties.getGroup());
        } catch (NacosException e) {
            log.error("Failed to get all instances for service: {}", serviceName, e);
            return List.of();
        }
    }
    
    /**
     * Register service instance manually
     */
    public void registerInstance(String serviceName, String ip, int port, Map<String, String> metadata) {
        try {
            Instance instance = new Instance();
            instance.setServiceName(serviceName);
            instance.setIp(ip);
            instance.setPort(port);
            instance.setHealthy(true);
            instance.setWeight(properties.getWeight());
            instance.setClusterName(properties.getClusterName());
            instance.setMetadata(metadata);
            instance.setEphemeral(properties.isEphemeral());
            
            NamingService namingService = nacosServiceManager.getNamingService(properties.getNacosProperties());
            namingService.registerInstance(serviceName, properties.getGroup(), instance);
            
            log.info("Registered instance: {}:{}:{}", serviceName, ip, port);
        } catch (NacosException e) {
            log.error("Failed to register instance: {}:{}:{}", serviceName, ip, port, e);
        }
    }
    
    /**
     * Deregister service instance
     */
    public void deregisterInstance(String serviceName, String ip, int port) {
        try {
            NamingService namingService = nacosServiceManager.getNamingService(properties.getNacosProperties());
            namingService.deregisterInstance(serviceName, properties.getGroup(), ip, port);
            
            log.info("Deregistered instance: {}:{}:{}", serviceName, ip, port);
        } catch (NacosException e) {
            log.error("Failed to deregister instance: {}:{}:{}", serviceName, ip, port, e);
        }
    }
    
    /**
     * Get service instances using Spring Cloud DiscoveryClient
     */
    public List<ServiceInstance> getServiceInstances(String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }
    
    /**
     * Get all registered services
     */
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }
    
    /**
     * Subscribe to service changes
     */
    public void subscribeService(String serviceName, ServiceChangeListener listener) {
        try {
            NamingService namingService = nacosServiceManager.getNamingService(properties.getNacosProperties());
            namingService.subscribe(serviceName, properties.getGroup(), event -> {
                if (event instanceof NamingEvent) {
                    NamingEvent namingEvent = (NamingEvent) event;
                    List<Instance> instances = namingEvent.getInstances();
                    listener.onServiceChange(serviceName, instances);
                }
            });
            
            log.info("Subscribed to service changes: {}", serviceName);
        } catch (NacosException e) {
            log.error("Failed to subscribe to service: {}", serviceName, e);
        }
    }
    
    /**
     * Service change listener interface
     */
    public interface ServiceChangeListener {
        void onServiceChange(String serviceName, List<Instance> instances);
    }
}