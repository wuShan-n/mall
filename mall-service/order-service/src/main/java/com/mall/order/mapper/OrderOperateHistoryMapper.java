package com.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.entity.OrderOperateHistory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 订单操作记录Mapper接口
 */
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistory> {
    
    /**
     * 根据订单ID查询操作记录
     */
    @Select("SELECT * FROM oms_order_operate_history WHERE order_id = #{orderId} ORDER BY create_time DESC")
    List<OrderOperateHistory> selectByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 根据订单号查询操作记录
     */
    @Select("SELECT * FROM oms_order_operate_history WHERE order_no = #{orderNo} ORDER BY create_time DESC")
    List<OrderOperateHistory> selectByOrderNo(@Param("orderNo") String orderNo);
}