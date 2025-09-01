//package com.mall.common.mybatis.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Druid DataSource configuration
// */
//@Slf4j
//@Configuration
//@ConditionalOnClass(DruidDataSource.class)
//public class DruidDataSourceConfig {
//
//    /**
//     * Primary DataSource
//     */
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.druid")
//    public DataSource dataSource() {
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//
//        try {
//            // Initialize filters
//            dataSource.setFilters("stat,wall,slf4j");
//
//            // Configure connection properties
//            dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000");
//
//            // Initialize DataSource
//            dataSource.init();
//
//            log.info("Druid DataSource initialized successfully");
//        } catch (SQLException e) {
//            log.error("Failed to initialize Druid DataSource", e);
//        }
//
//        return dataSource;
//    }
//
//    /**
//     * Druid monitoring servlet configuration
//     */
//    @Bean
//    @ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true")
//    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
//        ServletRegistrationBean<StatViewServlet> servletRegistrationBean =
//                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//        // IP whitelist (leave empty to allow all)
//        servletRegistrationBean.addInitParameter("allow", "");
//
//        // IP blacklist (deny has priority over allow)
//        servletRegistrationBean.addInitParameter("deny", "");
//
//        // Admin username
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//
//        // Admin password
//        servletRegistrationBean.addInitParameter("loginPassword", "admin123");
//
//        // Enable reset data function
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//
//        return servletRegistrationBean;
//    }
//
//    /**
//     * Druid web statistics filter
//     */
//    @Bean
//    @ConditionalOnProperty(name = "spring.datasource.druid.web-stat-filter.enabled", havingValue = "true")
//    public FilterRegistrationBean<WebStatFilter> druidWebStatFilter() {
//        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
//                new FilterRegistrationBean<>(new WebStatFilter());
//
//        // Add filter rules
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        // Add exclusions
//        Map<String, String> initParams = new HashMap<>();
//        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.setInitParameters(initParams);
//
//        return filterRegistrationBean;
//    }
//}