//package com.mall.search.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
//import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
//import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
//
//import java.time.Duration;
//
///**
// * Elasticsearch configuration
// *
// * @author mall
// */
//@Slf4j
//@Configuration
//public class ElasticsearchConfig extends ElasticsearchConfiguration {
//
//    @Value("${elasticsearch.host:localhost}")
//    private String host;
//
//    @Value("${elasticsearch.port:9200}")
//    private int port;
//
//    @Value("${elasticsearch.username:}")
//    private String username;
//
//    @Value("${elasticsearch.password:}")
//    private String password;
//
//    @Value("${elasticsearch.connection-timeout:5000}")
//    private int connectionTimeout;
//
//    @Value("${elasticsearch.socket-timeout:30000}")
//    private int socketTimeout;
//
//    @Override
//    public ClientConfiguration clientConfiguration() {
//        ClientConfiguration.TerminalClientConfigurationBuilder builder = ClientConfiguration.builder()
//                .connectedTo(host + ":" + port)
//                .withConnectTimeout(Duration.ofMillis(connectionTimeout))
//                .withSocketTimeout(Duration.ofMillis(socketTimeout));
//
//        // Add authentication if username and password are provided
//        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
//            builder.withBasicAuth(username, password);
//        }
//
//        return builder.build();
//    }
//
//    @Bean
//    public RestClient restClient() {
//        RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, "http"));
//
//        // Set timeouts
//        builder.setRequestConfigCallback(requestConfigBuilder ->
//            requestConfigBuilder
//                .setConnectTimeout(connectionTimeout)
//                .setSocketTimeout(socketTimeout)
//        );
//
//        // Set authentication if provided
//        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
//            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(
//                AuthScope.ANY,
//                new UsernamePasswordCredentials(username, password)
//            );
//
//            builder.setHttpClientConfigCallback(httpClientBuilder ->
//                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
//            );
//        }
//
//        return builder.build();
//    }
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient(RestClient restClient) {
//        // Create custom ObjectMapper for better date handling
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        // Create the transport with Rest client
//        ElasticsearchTransport transport = new RestClientTransport(
//            restClient,
//            new JacksonJsonpMapper(objectMapper)
//        );
//
//        return new ElasticsearchClient(transport);
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchOperations(
//            ElasticsearchClient elasticsearchClient,
//            ElasticsearchConverter elasticsearchConverter) {
//        return new ElasticsearchTemplate(elasticsearchClient, elasticsearchConverter);
//    }
//
//    @Bean
//    public ElasticsearchConverter elasticsearchConverter(
//            SimpleElasticsearchMappingContext mappingContext) {
//        return new MappingElasticsearchConverter(mappingContext);
//    }
//
//    @Bean
//    public SimpleElasticsearchMappingContext elasticsearchMappingContext() {
//        return new SimpleElasticsearchMappingContext();
//    }
//}