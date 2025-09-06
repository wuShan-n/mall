package com.mall.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.infrastructure.persistence.po.OrderPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单Mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderPO> {
    
    /**
     * 查找超时未支付的订单
     */
    @Select("SELECT * FROM oms_order WHERE status = 0 AND create_time <= #{timeoutTime}")
    List<OrderPO> findTimeoutOrders(@Param("timeoutTime") LocalDateTime timeoutTime);
    
    /**
     * 查找待自动确认收货的订单
     */
    @Select("SELECT * FROM oms_order WHERE status = 2 AND delivery_time <= #{confirmTime}")
    List<OrderPO> findOrdersToAutoConfirm(@Param("confirmTime") LocalDateTime confirmTime);
    
    /**
     * 查找待自动完成的订单
     */
    @Select("SELECT * FROM oms_order WHERE status = 3 AND receive_time <= #{completeTime}")
    List<OrderPO> findOrdersToAutoComplete(@Param("completeTime") LocalDateTime completeTime);
    
    /**
     * 统计用户各状态订单数量
     */
    @Select("SELECT COUNT(*) FROM oms_order WHERE user_id = #{userId} AND status = #{status} AND delete_status = 0")
    Long countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Integer status);
}