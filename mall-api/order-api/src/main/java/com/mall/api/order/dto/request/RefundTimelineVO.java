package com.mall.api.order.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
class RefundTimelineVO implements Serializable {
    private String title;
    private String description;
    private LocalDateTime time;
    private String operator;
}