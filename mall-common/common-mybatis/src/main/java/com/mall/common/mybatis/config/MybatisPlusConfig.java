package com.mall.common.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.mall.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus configuration
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan("com.mall.**.mapper")
public class MybatisPlusConfig {

    /**
     * MyBatis-Plus interceptor configuration
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        
        // Pagination plugin
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(1000L); // Maximum records per page
        paginationInnerInterceptor.setOverflow(false); // Don't cycle to first page when overflow
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        
        // Optimistic lock plugin
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        
        // Prevent full table update/delete
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        
        return interceptor;
    }

    /**
     * Auto-fill handler for common fields
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                log.debug("Start insert fill...");
                
                // Fill create time
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
                
                // Fill update time
                this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
                
                // Fill creator ID
                Long userId = UserContextHolder.getUserId();
                if (userId != null) {
                    this.strictInsertFill(metaObject, "createBy", Long.class, userId);
                    this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
                }
                
                // Fill default values
                this.strictInsertFill(metaObject, "deleted", Integer.class, 0);
                this.strictInsertFill(metaObject, "version", Integer.class, 0);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                log.debug("Start update fill...");
                
                // Fill update time
                this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
                
                // Fill updater ID
                Long userId = UserContextHolder.getUserId();
                if (userId != null) {
                    this.strictUpdateFill(metaObject, "updateBy", Long.class, userId);
                }
            }
        };
    }
}