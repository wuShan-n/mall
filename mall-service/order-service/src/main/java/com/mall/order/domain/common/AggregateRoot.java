package com.mall.order.domain.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根基类
 * DDD核心：提供领域事件管理能力的聚合根基类
 */
public abstract class AggregateRoot {
    
    private List<DomainEvent> domainEvents = new ArrayList<>();
    
    /**
     * 添加领域事件
     */
    protected void addDomainEvent(DomainEvent event) {
        if (this.domainEvents == null) {
            this.domainEvents = new ArrayList<>();
        }
        this.domainEvents.add(event);
    }
    
    /**
     * 获取所有领域事件
     */
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }
    
    /**
     * 清除所有领域事件
     */
    public void clearDomainEvents() {
        if (this.domainEvents != null) {
            this.domainEvents.clear();
        }
    }
}