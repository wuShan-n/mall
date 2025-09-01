package com.mall.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * User context holder for thread-local storage
 */
@UtilityClass
public class UserContextHolder {

    private static final TransmittableThreadLocal<UserContext> CONTEXT_HOLDER = new TransmittableThreadLocal<>();

    /**
     * Set user context
     */
    public static void setContext(UserContext context) {
        CONTEXT_HOLDER.set(context);
    }

    /**
     * Get user context
     */
    public static UserContext getContext() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * Get current user ID
     */
    public static Long getUserId() {
        UserContext context = getContext();
        return context != null ? context.getUserId() : null;
    }

    /**
     * Get current username
     */
    public static String getUsername() {
        UserContext context = getContext();
        return context != null ? context.getUsername() : null;
    }

    /**
     * Clear context
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}