package com.mall.common.mybatis.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.base.PageRequest;
import com.mall.common.constant.CommonConstants;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MyBatis-Plus utility class
 */
public class MybatisUtils {
    
    private MybatisUtils() {
        throw new IllegalStateException("Utility class");
    }
    
    /**
     * Create page object from PageRequest
     */
    public static <T> Page<T> createPage(PageRequest request) {
        Page<T> page = new Page<>(request.getCurrent(), request.getSize());
        
        // Handle sorting
        if (StringUtils.hasText(request.getOrderBy())) {
            String orderBy = camelToUnderscore(request.getOrderBy());
            boolean isAsc = CommonConstants.ORDER_ASC.equalsIgnoreCase(request.getOrderDirection());
            
            if (orderBy.contains(",")) {
                // Multiple sort fields
                List<OrderItem> orderItems = Arrays.stream(orderBy.split(","))
                    .map(field -> isAsc ? OrderItem.asc(field.trim()) : OrderItem.desc(field.trim()))
                    .collect(Collectors.toList());
                page.setOrders(orderItems);
            } else {
                // Single sort field
                page.addOrder(isAsc ? OrderItem.asc(orderBy) : OrderItem.desc(orderBy));
            }
        } else {
            // Default sort by create_time desc
            page.addOrder(OrderItem.desc("create_time"));
        }
        
        return page;
    }
    
    /**
     * Convert camelCase to underscore
     */
    public static String camelToUnderscore(String camelCase) {
        if (!StringUtils.hasText(camelCase)) {
            return camelCase;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (i > 0) {
                    result.append("_");
                }
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Add like condition if value is not empty
     */
    public static <T> void likeIfPresent(LambdaQueryWrapper<T> wrapper, 
                                         boolean condition, 
                                         Object val, 
                                         com.baomidou.mybatisplus.core.toolkit.support.SFunction<T, ?> column) {
        if (condition && val != null && StringUtils.hasText(val.toString())) {
            wrapper.like(column, val);
        }
    }
    
    /**
     * Add equals condition if value is not null
     */
    public static <T> void eqIfPresent(LambdaQueryWrapper<T> wrapper,
                                       Object val,
                                       com.baomidou.mybatisplus.core.toolkit.support.SFunction<T, ?> column) {
        if (val != null) {
            wrapper.eq(column, val);
        }
    }
    
    /**
     * Add between condition if both values are not null
     */
    public static <T> void betweenIfPresent(LambdaQueryWrapper<T> wrapper,
                                            Object val1,
                                            Object val2,
                                            com.baomidou.mybatisplus.core.toolkit.support.SFunction<T, ?> column) {
        if (val1 != null && val2 != null) {
            wrapper.between(column, val1, val2);
        } else if (val1 != null) {
            wrapper.ge(column, val1);
        } else if (val2 != null) {
            wrapper.le(column, val2);
        }
    }
    
    /**
     * Create QueryWrapper with deleted = 0 condition
     */
    public static <T> QueryWrapper<T> notDeleted() {
        return new QueryWrapper<T>().eq("deleted", CommonConstants.NOT_DELETED);
    }
    

}