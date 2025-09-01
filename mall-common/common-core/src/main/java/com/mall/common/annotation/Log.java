package com.mall.common.annotation;


import java.lang.annotation.*;

/**
 * Operation log annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * Module name
     */
    String module() default "";

    /**
     * Operation type
     */
    OperationType type() default OperationType.OTHER;

    /**
     * Operation description
     */
    String desc() default "";

    /**
     * Save request parameters
     */
    boolean saveParams() default true;

    /**
     * Save response result
     */
    boolean saveResult() default true;

    enum OperationType {
        /**
         * Query
         */
        QUERY,

        /**
         * Create
         */
        CREATE,

        /**
         * Update
         */
        UPDATE,

        /**
         * Delete
         */
        DELETE,

        /**
         * Import
         */
        IMPORT,

        /**
         * Export
         */
        EXPORT,

        /**
         * Login
         */
        LOGIN,

        /**
         * Logout
         */
        LOGOUT,

        /**
         * Other
         */
        OTHER
    }
}
