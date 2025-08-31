package com.mall.common.base;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base entity with common fields
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Primary key ID
     */
    private Long id;
    
    /**
     * Create time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    /**
     * Update time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    /**
     * Creator ID
     */
    private Long createBy;
    
    /**
     * Updater ID
     */
    private Long updateBy;
    
    /**
     * Logical delete flag (0: normal, 1: deleted)
     */
    @JsonIgnore
    private Integer deleted;
    
    /**
     * Version for optimistic lock
     */
    @JsonIgnore
    private Integer version;
}