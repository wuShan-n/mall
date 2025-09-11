

package com.mall.search.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * Hot keyword entity
 * 
 * @author mall
 */
@Data
@TableName("hot_keyword")
public class HotKeyword {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String keyword;
    
    private Long categoryId;
    
    private String categoryName;
    
    private Long searchCount;
    
    private Long clickCount;
    
    private Long conversionCount;
    
    private Integer rank;
    
    private Integer previousRank;
    
    private Boolean isHot;
    
    private Boolean isNew;
    
    private String icon;
    
    private String color;
    
    private Integer status;
    
    private LocalDateTime statisticsDate;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    private Integer deleted;
}