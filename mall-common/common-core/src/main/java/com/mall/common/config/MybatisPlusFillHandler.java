package com.mall.common.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
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

        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        Long currentUserId = getCurrentUserId();

        this.strictInsertFill(metaObject, "createBy", Long.class, currentUserId);

        this.strictInsertFill(metaObject, "updateBy", Long.class, currentUserId);
    }


    @Override
    public void updateFill(MetaObject metaObject) {

        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        Long currentUserId = getCurrentUserId();
        this.strictUpdateFill(metaObject, "updateBy", Long.class, currentUserId);
    }

    private Long getCurrentUserId() {
        return StpUtil.getLoginIdAsLong(); // Placeholder: Replace with actual logic
    }
}