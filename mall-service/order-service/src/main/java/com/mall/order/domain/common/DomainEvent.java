package com.mall.order.domain.common;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 领域事件基类
 * DDD核心：所有领域事件的基类，提供基本的事件属性
 */
public abstract class DomainEvent {
    
    private final String eventId;
    private final LocalDateTime eventTime;
    private final String eventType;
    
    protected DomainEvent() {
        this.eventId = UUID.randomUUID().toString();
        this.eventTime = LocalDateTime.now();
        this.eventType = this.getClass().getSimpleName();
    }
    
    public String getEventId() {
        return eventId;
    }
    
    public LocalDateTime getEventTime() {
        return eventTime;
    }
    
    public String getEventType() {
        return eventType;
    }
}