package com.mall.search.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;

/**
 * Spring Data Elasticsearch配置类
 * 使用Spring Boot自动配置的简化配置
 *
 * @author mall
 */
@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.mall.search.repository")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    @Value("${elasticsearch.host:localhost}")
    private String host;

    @Value("${elasticsearch.port:9200}")
    private int port;

    @Value("${elasticsearch.username:}")
    private String username;

    @Value("${elasticsearch.password:}")
    private String password;

    @Value("${elasticsearch.connection-timeout:5000}")
    private int connectionTimeout;

    @Value("${elasticsearch.socket-timeout:30000}")
    private int socketTimeout;

    @Value("${elasticsearch.scheme:http}")
    private String scheme;

    @Override
    public ClientConfiguration clientConfiguration() {
        log.info("配置Elasticsearch客户端 - {}:{}", host, port);
        
        ClientConfiguration.TerminalClientConfigurationBuilder builder = ClientConfiguration.builder()
                .connectedTo(host + ":" + port)
                .withConnectTimeout(Duration.ofMillis(connectionTimeout))
                .withSocketTimeout(Duration.ofMillis(socketTimeout));

        // 如果提供了用户名和密码，则添加身份验证
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            log.info("为Elasticsearch使用基本身份验证");
            builder.withBasicAuth(username, password);
        }

        ClientConfiguration config = builder.build();
        log.info("Elasticsearch客户端配置完成");
        return config;
    }
}