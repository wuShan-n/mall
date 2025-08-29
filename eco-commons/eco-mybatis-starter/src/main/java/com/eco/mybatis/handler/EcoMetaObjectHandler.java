package com.eco.mybatis.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充处理器
 */
@Slf4j
@Component
public class EcoMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("开始插入填充...");

        // 创建时间
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);

        // 更新时间
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);

        // 创建人（从上下文获取当前用户ID）
        Long userId = getCurrentUserId();
        this.strictInsertFill(metaObject, "createBy", () -> userId, Long.class);
        this.strictInsertFill(metaObject, "updateBy", () -> userId, Long.class);

        // 删除标记
        this.strictInsertFill(metaObject, "deleted", () -> 0, Integer.class);

        // 版本号
        this.strictInsertFill(metaObject, "version", () -> 1, Integer.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("开始更新填充...");

        // 更新时间
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);

        // 更新人
        Long userId = getCurrentUserId();
        this.strictUpdateFill(metaObject, "updateBy", () -> userId, Long.class);
    }

    /**
     * 获取当前用户ID（需要集成Sa-Token后实现）
     */
    private Long getCurrentUserId() {
        try {
            // 这里需要根据实际的认证框架来获取用户ID
            // 例如使用Sa-Token: StpUtil.getLoginIdAsLong()
            String userId = System.getProperty("current.user.id");
            if (StrUtil.isNotBlank(userId)) {
                return Long.parseLong(userId);
            }
        } catch (Exception e) {
            log.debug("获取当前用户ID失败", e);
        }
        return 0L;
    }
}
