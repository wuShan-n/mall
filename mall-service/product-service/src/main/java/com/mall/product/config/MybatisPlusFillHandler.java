package com.mall.product.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Mybatis-Plus MetaObjectHandler Configuration
 * Handles automatic population of "createTime", "updateTime", "createBy", and "updateBy" fields.
 */
@Slf4j
@Component
public class MybatisPlusFillHandler implements MetaObjectHandler {


    /**
     * This method is called during an INSERT operation.
     * It populates the createTime, updateTime, createBy, and updateBy fields.
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("Mybatis-Plus auto-fill on INSERT starting...");
        
        // setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        // The third parameter, metaObject, is the object containing the data to be inserted.

        // Set createTime to the current time
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        
        // Set updateTime to the current time
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        // You need a mechanism to get the current user's ID.
        // This is often retrieved from a security context (e.g., Spring Security's SecurityContextHolder)
        // or a ThreadLocal variable. For this example, we'll use a placeholder.
        Long currentUserId = getCurrentUserId(); // Replace with your actual user retrieval logic

        // Set createBy to the current user's ID
        this.strictInsertFill(metaObject, "createBy", Long.class, currentUserId);
        
        // Set updateBy to the current user's ID
        this.strictInsertFill(metaObject, "updateBy", Long.class, currentUserId);
    }

    /**
     * This method is called during an UPDATE operation.
     * It populates the updateTime and updateBy fields.
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("Mybatis-Plus auto-fill on UPDATE starting...");
        
        // Set updateTime to the current time
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        // Get the current user's ID
        Long currentUserId = getCurrentUserId(); // Replace with your actual user retrieval logic
        
        // Set updateBy to the current user's ID
        this.strictUpdateFill(metaObject, "updateBy", Long.class, currentUserId);
    }

    /**
     * Placeholder method to retrieve the current user's ID.
     * Implement this method according to your application's security and session management.
     *
     * @return The ID of the currently logged-in user, or null if not available.
     */
    private Long getCurrentUserId() {
        return 1L; // Placeholder: Replace with actual logic
    }
}