package com.eco.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MyBatis-Plus配置属性
 */
@Data
@ConfigurationProperties(prefix = "eco.mybatis")
public class MybatisPlusProperties {

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 是否开启SQL打印
     */
    private boolean sqlLog = true;

    /**
     * 分页最大限制
     */
    private Long maxLimit = 1000L;

    /**
     * 是否溢出处理
     */
    private boolean overflow = false;

    /**
     * 是否启用乐观锁
     */
    private boolean optimisticLockerEnabled = true;

    /**
     * 是否启用防全表更新删除
     */
    private boolean blockAttackEnabled = true;

    /**
     * 是否启用数据权限
     */
    private boolean dataPermissionEnabled = false;

    /**
     * 逻辑删除字段
     */
    private String logicDeleteField = "deleted";

    /**
     * 逻辑删除值
     */
    private Integer logicDeleteValue = 1;

    /**
     * 逻辑未删除值
     */
    private Integer logicNotDeleteValue = 0;
}
