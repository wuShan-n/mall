package com.mall.order.domain.order.valueobject;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Getter;

import java.util.Objects;

/**
 * 订单号值对象
 */
@Getter
public class OrderNo {
    
    private final String value;
    
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);
    
    public OrderNo(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("订单号不能为空");
        }
        this.value = value;
    }
    
    /**
     * 生成订单号
     * 格式：ORD + 日期 + 雪花ID后8位
     */
    public static OrderNo generate() {
        String date = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String id = String.valueOf(snowflake.nextId());
        String orderNo = "ORD" + date + id.substring(id.length() - 8);
        return new OrderNo(orderNo);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNo orderNo = (OrderNo) o;
        return Objects.equals(value, orderNo.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return value;
    }
}