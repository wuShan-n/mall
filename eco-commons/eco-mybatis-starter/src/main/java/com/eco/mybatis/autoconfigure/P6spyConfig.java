package com.eco.mybatis.autoconfigure;

import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * P6spy SQL打印配置
 */
@Configuration
@ConditionalOnProperty(prefix = "eco.mybatis", name = "sql-log", havingValue = "true")
public class P6spyConfig {

    @PostConstruct
    public void init() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(CustomSqlFormat.class.getName());
    }

    /**
     * 自定义SQL格式化
     */
    public static class CustomSqlFormat implements MessageFormattingStrategy {

        private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        @Override
        public String formatMessage(int connectionId, String now, long elapsed,
                                   String category, String prepared, String sql, String url) {
            return LocalDateTime.now().format(FORMATTER) +
                   " | 耗时: " + elapsed + "ms" +
                   " | SQL: " + sql.replaceAll("\\s+", " ");
        }
    }
}
