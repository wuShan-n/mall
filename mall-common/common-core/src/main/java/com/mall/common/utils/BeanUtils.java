package com.mall.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Bean utility class
 */
public class BeanUtils {
    
    private BeanUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Copy properties from source to target
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        return BeanUtil.copyProperties(source, targetClass);
    }
    
    /**
     * Copy properties to existing target
     */
    public static void copy(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtil.copyProperties(source, target);
    }
    
    /**
     * Copy list
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        if (CollUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return BeanUtil.copyToList(sourceList, targetClass);
    }
    
    /**
     * Copy list with supplier
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Supplier<T> targetSupplier) {
        if (CollUtil.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (S source : sourceList) {
            T target = targetSupplier.get();
            BeanUtil.copyProperties(source, target);
            targetList.add(target);
        }
        return targetList;
    }
}