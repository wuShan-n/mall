package com.mall.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.api.config.listener.Listener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.concurrent.Executor;

/**
 * 动态路由服务
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicRouteService {

    private final RouteDefinitionWriter routeDefinitionWriter;
    private final ApplicationEventPublisher publisher;
    private final NacosConfigManager nacosConfigManager;

    private static final String ROUTE_DATA_ID = "gateway-routes.json";
    private static final String ROUTE_GROUP = "DEFAULT_GROUP";

    @PostConstruct
    public void init() {
        loadRoutes();
        addRouteListener();
    }

    /**
     * 加载路由配置
     */
    private void loadRoutes() {
        try {
            String content = nacosConfigManager.getConfigService()
                    .getConfig(ROUTE_DATA_ID, ROUTE_GROUP, 5000);
            if (content != null) {
                List<RouteDefinition> routes = JSON.parseArray(content, RouteDefinition.class);
                routes.forEach(this::addRoute);
                log.info("Loaded {} routes from Nacos", routes.size());
            }
        } catch (Exception e) {
            log.error("Failed to load routes from Nacos", e);
        }
    }

    /**
     * 添加路由监听器
     */
    private void addRouteListener() {
        try {
            nacosConfigManager.getConfigService().addListener(ROUTE_DATA_ID, ROUTE_GROUP, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("Routes config changed, reloading...");
                    List<RouteDefinition> routes = JSON.parseArray(configInfo, RouteDefinition.class);
                    updateRoutes(routes);
                }
            });
        } catch (Exception e) {
            log.error("Failed to add route listener", e);
        }
    }

    /**
     * 更新路由
     */
    private void updateRoutes(List<RouteDefinition> routes) {
        routes.forEach(this::updateRoute);
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    /**
     * 添加路由
     */
    private void addRoute(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    }

    /**
     * 更新路由
     */
    private void updateRoute(RouteDefinition definition) {
        routeDefinitionWriter.delete(Mono.just(definition.getId())).subscribe();
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
    }
}