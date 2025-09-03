package com.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 订单Mapper接口
 */
public interface OrderMapper extends BaseMapper<Order> {
    
    /**
     * 根据订单号查询订单
     */
    @Select("SELECT * FROM oms_order WHERE order_no = #{orderNo}")
    Order selectByOrderNo(@Param("orderNo") String orderNo);
    
    /**
     * 查询超时未支付的订单
     */
    @Select("SELECT * FROM oms_order WHERE status = 0 AND expire_time < NOW()")
    List<Order> selectTimeoutOrders();
    
    /**
     * 查询待自动确认的订单
     */
    @Select("SELECT * FROM oms_order WHERE status = 2 AND DATE_ADD(delivery_time, INTERVAL auto_confirm_day DAY) < NOW()")
    List<Order> selectAutoConfirmOrders();
    
    /**
     * 统计订单数据
     */
    Map<String, Object> selectOrderStatistics(@Param("startTime") LocalDateTime startTime,
                                               @Param("endTime") LocalDateTime endTime);
    
    /**
     * 统计用户订单信息
     */
    Map<String, Object> selectUserOrderStatistics(@Param("userId") Long userId);
}