package com.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.entity.OrderRefund;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

/**
 * 订单退款Mapper接口
 */
public interface OrderRefundMapper extends BaseMapper<OrderRefund> {
    
    /**
     * 根据退款编号查询
     */
    @Select("SELECT * FROM oms_order_refund WHERE refund_no = #{refundNo}")
    OrderRefund selectByRefundNo(@Param("refundNo") String refundNo);
    
    /**
     * 根据订单ID查询退款记录
     */
    @Select("SELECT * FROM oms_order_refund WHERE order_id = #{orderId}")
    List<OrderRefund> selectByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 根据订单号查询退款记录
     */
    @Select("SELECT * FROM oms_order_refund WHERE order_no = #{orderNo}")
    List<OrderRefund> selectByOrderNo(@Param("orderNo") String orderNo);
    
    /**
     * 统计退款数据
     */
    Map<String, Object> selectRefundStatistics();
}