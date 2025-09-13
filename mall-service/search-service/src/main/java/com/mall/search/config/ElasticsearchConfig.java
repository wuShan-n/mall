package com.mall.search.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Arrays;

/**
 * Elasticsearch 配置类
 * 配置原生 Elasticsearch Java Client
 */
@Slf4j
@Configuration
public class ElasticsearchConfig {
    
    @Value("${spring.elasticsearch.uris:http://localhost:9200}")
    private String[] uris;
    
    @Value("${spring.elasticsearch.username:}")
    private String username;
    
    @Value("${spring.elasticsearch.password:}")
    private String password;
    
    @Value("${spring.elasticsearch.connection-timeout:5000}")
    private int connectionTimeout;
    
    @Value("${spring.elasticsearch.socket-timeout:60000}")
    private int socketTimeout;
    
    @Value("${spring.elasticsearch.max-conn-total:100}")
    private int maxConnTotal;
    
    @Value("${spring.elasticsearch.max-conn-per-route:10}")
    private int maxConnPerRoute;
    
    @Value("${spring.elasticsearch.io-thread-count:4}")
    private int ioThreadCount;
    
    /**
     * 配置 ObjectMapper
     */
    @Bean
    public ObjectMapper elasticsearchObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 配置时间模块
        objectMapper.registerModule(new JavaTimeModule());
        // 忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 允许空对象
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper;
    }

    
    /**
     * 配置 ElasticsearchTransport
     */
    @Bean
    @Qualifier("elasticsearchObjectMapper")
    public ElasticsearchTransport elasticsearchTransport(RestClient restClient, 
                                                         ObjectMapper objectMapper) {
        return new RestClientTransport(
            restClient,
            new JacksonJsonpMapper(objectMapper)
        );
    }
    
    /**
     * 配置 ElasticsearchClient
     */
    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        ElasticsearchClient client = new ElasticsearchClient(transport);
        log.info("ElasticsearchClient initialized successfully");
        return client;
    }


}