package com.mall.order.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 订单操作记录持久化对象
 */
@Data
@TableName("oms_order_operate_history")
public class OrderOperateHistoryPO {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private Long orderId;
    
    private String orderNo;
    
    private String operateType;
    
    private String operateMan;
    
    private Integer orderStatus;
    
    private String note;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}