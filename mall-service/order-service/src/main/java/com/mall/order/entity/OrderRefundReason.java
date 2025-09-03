package com.mall.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 退款原因实体类
 */
@Data
@TableName("oms_order_refund_reason")
public class OrderRefundReason {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String name;
    private Integer sort;
    private Integer status;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}