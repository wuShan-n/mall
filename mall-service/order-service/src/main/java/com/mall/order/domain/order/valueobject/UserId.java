package com.mall.order.domain.order.valueobject;

import lombok.Getter;

import java.util.Objects;

/**
 * 用户ID值对象
 */
@Getter
public class UserId {
    
    private final Long value;
    
    public UserId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("用户ID不能为空且必须大于0");
        }
        this.value = value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
