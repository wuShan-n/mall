package com.mall.order.domain.refund.valueobject;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Getter;

import java.util.Objects;

/**
 * 退款单号值对象
 */
@Getter
public class RefundNo {
    
    private final String value;
    
    private static final Snowflake snowflake = IdUtil.getSnowflake(1, 1);
    
    public RefundNo(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("退款单号不能为空");
        }
        this.value = value;
    }
    
    /**
     * 生成退款单号
     * 格式：REF + 日期 + 雪花ID后8位
     */
    public static RefundNo generate() {
        String date = DateUtil.format(DateUtil.date(), "yyyyMMdd");
        String id = String.valueOf(snowflake.nextId());
        String refundNo = "REF" + date + id.substring(id.length() - 8);
        return new RefundNo(refundNo);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefundNo refundNo = (RefundNo) o;
        return Objects.equals(value, refundNo.value);
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
