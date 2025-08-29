package com.eco.starter.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j配置
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "eco.swagger", name = "enabled", havingValue = "true", matchIfMissing = true)
public class Knife4jConfig {

    private final EcoProperties ecoProperties;

    @Bean
    public OpenAPI openAPI() {
        EcoProperties.SwaggerProperties swagger = ecoProperties.getSwagger();

        return new OpenAPI()
            .info(new Info()
                .title(swagger.getTitle())
                .description(swagger.getDescription())
                .version(swagger.getVersion())
                .contact(new Contact()
                    .name(swagger.getAuthor())
                    .email(swagger.getEmail())));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("default")
            .pathsToMatch("/**")
            .pathsToExclude("/error", "/actuator/**")
            .build();
    }
}
