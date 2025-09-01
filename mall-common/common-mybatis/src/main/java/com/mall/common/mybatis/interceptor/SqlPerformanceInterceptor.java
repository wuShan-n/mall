package com.mall.common.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

/**
 * SQL performance analysis interceptor
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "mybatis-plus.performance.enabled", havingValue = "true")
@Intercepts({
    @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
    @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
    @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})
})
public class SqlPerformanceInterceptor implements Interceptor {
    
    private static final long SLOW_SQL_MILLIS = 1000L; // 1 second
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        
        long time = end - start;
        
        if (time >= SLOW_SQL_MILLIS) {
            log.warn("Slow SQL detected! Execution time: {} ms, SQL: {}", time, formatSql(sql));
        } else if (log.isDebugEnabled()) {
            log.debug("SQL execution time: {} ms, SQL: {}", time, formatSql(sql));
        }
        
        return result;
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
        // Configuration properties can be set here
    }
    
    /**
     * Format SQL for better readability
     */
    private String formatSql(String sql) {
        return sql.replaceAll("\\s+", " ").trim();
    }
}