package com.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.entity.OrderRefundReason;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 退款原因Mapper接口
 */
public interface OrderRefundReasonMapper extends BaseMapper<OrderRefundReason> {
    
    /**
     * 查询启用的退款原因
     */
    @Select("SELECT * FROM oms_order_refund_reason WHERE status = 1 ORDER BY sort")
    List<OrderRefundReason> selectActiveReasons();
}