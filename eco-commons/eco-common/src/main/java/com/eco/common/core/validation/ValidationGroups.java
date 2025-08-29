package com.eco.common.core.validation;

/**
 * 参数校验分组
 */
public interface ValidationGroups {

    /**
     * 新增
     */
    interface Create {}

    /**
     * 更新
     */
    interface Update {}

    /**
     * 删除
     */
    interface Delete {}

    /**
     * 查询
     */
    interface Query {}

    /**
     * 导入
     */
    interface Import {}

    /**
     * 导出
     */
    interface Export {}
}
