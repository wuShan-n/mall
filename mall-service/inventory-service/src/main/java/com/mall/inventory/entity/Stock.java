package com.mall.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("inv_stock")
public class Stock {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    private Long skuId;
    private Long warehouseId;
    private Integer totalStock;
    private Integer availableStock;
    private Integer lockedStock;
    private Integer inTransitStock;
    private Integer warnStock;
    private Integer safetyStock;
    private Integer status;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    private Integer deleted;

}