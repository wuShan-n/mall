package com.mall.common.mybatis.annotation;

import com.mall.common.mybatis.datasource.DynamicDataSource;

import java.lang.annotation.*;

/**
 * DataSource annotation for specifying which data source to use
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    
    /**
     * Data source type
     */
    DynamicDataSource.DataSourceType value() default DynamicDataSource.DataSourceType.MASTER;
    
    /**
     * Data source name (for multiple slaves)
     */
    String name() default "";
}