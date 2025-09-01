package com.mall.common.mybatis.interceptor;

import com.mall.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Data audit interceptor for tracking data changes
 */
@Slf4j
@Component
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class DataAuditInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        
        if (parameter == null) {
            return invocation.proceed();
        }
        
        Long userId = UserContextHolder.getUserId();
        LocalDateTime now = LocalDateTime.now();
        
        if (SqlCommandType.INSERT == sqlCommandType) {
            setFieldValue(parameter, "createBy", userId);
            setFieldValue(parameter, "createTime", now);
            setFieldValue(parameter, "updateBy", userId);
            setFieldValue(parameter, "updateTime", now);
        } else if (SqlCommandType.UPDATE == sqlCommandType) {
            setFieldValue(parameter, "updateBy", userId);
            setFieldValue(parameter, "updateTime", now);
        }
        
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
    @Override
    public void setProperties(Properties properties) {
        // Configuration properties
    }
    
    /**
     * Set field value using reflection
     */
    private void setFieldValue(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            
            // Only set if field value is null
            if (field.get(obj) == null && value != null) {
                field.set(obj, value);
            }
        } catch (Exception e) {
            // Field might not exist in the entity
            log.trace("Could not set field {} for entity {}", fieldName, obj.getClass().getSimpleName());
        }
    }
}