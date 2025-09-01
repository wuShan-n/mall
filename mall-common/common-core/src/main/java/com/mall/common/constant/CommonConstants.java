package com.mall.common.constant;

/**
 * Common constants
 */
public interface CommonConstants {

    /**
     * Success flag
     */
    String SUCCESS = "success";

    /**
     * Failure flag
     */
    String FAIL = "fail";

    /**
     * Yes
     */
    Integer YES = 1;

    /**
     * No
     */
    Integer NO = 0;

    /**
     * Deleted flag
     */
    Integer DELETED = 1;

    /**
     * Not deleted flag
     */
    Integer NOT_DELETED = 0;

    /**
     * Default sort field
     */
    String DEFAULT_ORDER_BY = "create_time";

    /**
     * Ascending order
     */
    String ORDER_ASC = "asc";

    /**
     * Descending order
     */
    String ORDER_DESC = "desc";

    /**
     * Tree root ID
     */
    Long TREE_ROOT_ID = 0L;

    /**
     * System user ID
     */
    Long SYSTEM_USER_ID = 0L;
}