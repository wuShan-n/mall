package com.eco.common.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring上下文工具类
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 获取ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过class获取Bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        assertApplicationContext();
        return applicationContext.getBean(beanClass);
    }

    /**
     * 通过name和class获取Bean
     */
    public static <T> T getBean(String beanName, Class<T> beanClass) {
        assertApplicationContext();
        return applicationContext.getBean(beanName, beanClass);
    }

    private static void assertApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext未注入");
        }
    }
}
