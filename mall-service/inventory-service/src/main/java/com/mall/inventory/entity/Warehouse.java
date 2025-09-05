package com.mall.inventory.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("inv_warehouse")
public class Warehouse {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    private String warehouseName;
    private String warehouseCode;
    private Integer warehouseType;
    private String contactPerson;
    private String contactPhone;
    private String province;
    private String city;
    private String district;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer status;
    private String remark;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    private Integer deleted;
}