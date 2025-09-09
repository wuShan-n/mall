package com.mall.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("inv_stock_lock")
public class StockLock {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    private Long skuId;
    private Long warehouseId;
    private String orderNo;
    private Long userId;
    private Integer lockedQuantity;
    private Integer lockStatus;
    private LocalDateTime lockTime;
    private LocalDateTime expireTime;
    private LocalDateTime releaseTime;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}