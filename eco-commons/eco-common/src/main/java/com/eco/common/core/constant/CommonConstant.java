package com.eco.common.core.constant;

/**
 * 通用常量
 */
public interface CommonConstant {

    /**
     * 成功标记
     */
    Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    Integer FAIL = 500;

    /**
     * 默认页码
     */
    Long DEFAULT_PAGE = 1L;

    /**
     * 默认页大小
     */
    Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 最大页大小
     */
    Long MAX_PAGE_SIZE = 1000L;

    /**
     * 升序
     */
    String ASC = "asc";

    /**
     * 降序
     */
    String DESC = "desc";

    /**
     * 树形根节点ID
     */
    Long TREE_ROOT_ID = 0L;

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLED = 1;

    /**
     * 删除标记 - 未删除
     */
    Integer DEL_FLAG_NORMAL = 0;

    /**
     * 删除标记 - 已删除
     */
    Integer DEL_FLAG_DELETED = 1;
}
