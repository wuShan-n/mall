package com.mall.common.mybatis.aspect;

import com.mall.common.mybatis.annotation.DataSource;
import com.mall.common.mybatis.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * DataSource aspect for dynamic data source switching
 */
@Slf4j
@Aspect
@Order(-1) // Execute before @Transactional
@Component
public class DataSourceAspect {
    
    @Pointcut("@annotation(com.mall.common.mybatis.annotation.DataSource) || " +
              "@within(com.mall.common.mybatis.annotation.DataSource)")
    public void dataSourcePointCut() {
    }
    
    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = point.getTarget().getClass();
        
        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource == null) {
            dataSource = targetClass.getAnnotation(DataSource.class);
        }
        
        if (dataSource != null) {
            DynamicDataSource.setDataSourceType(dataSource.value());
            log.debug("Set DataSource to {} for method {}", dataSource.value(), method.getName());
        }
        
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSourceType();
            log.debug("Clear DataSource");
        }
    }
}