package com.eco.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Eco配置属性
 */
@Data
@ConfigurationProperties(prefix = "eco")
public class EcoProperties {

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用版本
     */
    private String appVersion;

    /**
     * 环境
     */
    private String env = "dev";

    /**
     * 日志配置
     */
    private LogProperties log = new LogProperties();

    /**
     * 异步配置
     */
    private AsyncProperties async = new AsyncProperties();

    /**
     * XSS配置
     */
    private XssProperties xss = new XssProperties();

    /**
     * Swagger配置
     */
    private SwaggerProperties swagger = new SwaggerProperties();

    @Data
    public static class LogProperties {
        /**
         * 是否启用请求日志
         */
        private boolean enabled = true;

        /**
         * 是否打印请求参数
         */
        private boolean printParams = true;

        /**
         * 是否打印响应结果
         */
        private boolean printResult = true;

        /**
         * 慢请求阈值（毫秒）
         */
        private long slowThreshold = 3000;

        /**
         * 忽略的URL
         */
        private List<String> ignoreUrls = new ArrayList<>();
    }

    @Data
    public static class AsyncProperties {
        /**
         * 核心线程数
         */
        private int corePoolSize = 10;

        /**
         * 最大线程数
         */
        private int maxPoolSize = 50;

        /**
         * 队列容量
         */
        private int queueCapacity = 1000;

        /**
         * 线程名前缀
         */
        private String threadNamePrefix = "eco-async-";

        /**
         * 空闲线程存活时间（秒）
         */
        private int keepAliveSeconds = 60;
    }

    @Data
    public static class XssProperties {
        /**
         * 是否启用XSS过滤
         */
        private boolean enabled = true;

        /**
         * 排除的URL
         */
        private List<String> excludeUrls = new ArrayList<>();
    }

    @Data
    public static class SwaggerProperties {
        /**
         * 是否启用
         */
        private boolean enabled = true;

        /**
         * 标题
         */
        private String title = "Eco Platform API";

        /**
         * 描述
         */
        private String description = "Eco Platform API Documentation";

        /**
         * 版本
         */
        private String version = "1.0.0";

        /**
         * 作者
         */
        private String author = "Eco Team";

        /**
         * 邮箱
         */
        private String email = "eco@example.com";
    }
}
