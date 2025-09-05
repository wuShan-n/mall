package com.mall.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("inv_stock_record")
public class StockRecord {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    private Long skuId;
    private Long warehouseId;
    private Integer operationType;
    private Integer changeQuantity;
    private Integer stockBefore;
    private Integer stockAfter;
    private String businessType;
    private String businessNo;
    private String remark;
    private Long operatorId;
    private String operatorName;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
