package com.mall.search.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Search history entity
 * 
 * @author mall
 */
@Data
@TableName("search_history")
public class SearchHistory {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long userId;
    
    private String keyword;
    
    private String searchType;
    
    private Integer resultCount;
    
    private Integer clickCount;
    
    private String searchSource;
    
    private String deviceType;
    
    private String ipAddress;
    
    private String sessionId;
    
    private String trackingId;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    private Integer deleted;
}

