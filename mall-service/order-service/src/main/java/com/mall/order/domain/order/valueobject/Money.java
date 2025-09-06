package com.mall.order.domain.order.valueobject;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 金额值对象
 * DDD核心：值对象封装了金额的业务规则
 */
@Getter
public class Money {
    
    private final BigDecimal amount;
    
    public Money(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("金额不能为空");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("金额不能为负数");
        }
        // 保留两位小数
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
    
    public Money(String amount) {
        this(new BigDecimal(amount));
    }
    
    /**
     * 加法
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }
    
    /**
     * 减法
     */
    public Money subtract(Money other) {
        BigDecimal result = this.amount.subtract(other.amount);
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("减法结果不能为负数");
        }
        return new Money(result);
    }
    
    /**
     * 乘法
     */
    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }
    
    /**
     * 比较是否大于
     */
    public boolean isGreaterThan(Money other) {
        return this.amount.compareTo(other.amount) > 0;
    }
    
    /**
     * 比较是否小于
     */
    public boolean isLessThan(Money other) {
        return this.amount.compareTo(other.amount) < 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount);
    }
    
    @Override
    public int hashCode() {
        return amount.hashCode();
    }
    
    @Override
    public String toString() {
        return amount.toString();
    }
}