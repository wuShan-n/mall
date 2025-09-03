package com.mall.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 订单操作记录实体类
 */
@Data
@TableName("oms_order_operate_history")
public class OrderOperateHistory {
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
