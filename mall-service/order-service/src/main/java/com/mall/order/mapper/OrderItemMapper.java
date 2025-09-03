package com.mall.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * 订单商品项Mapper接口
 */
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    
    /**
     * 根据订单ID查询订单项
     */
    @Select("SELECT * FROM oms_order_item WHERE order_id = #{orderId}")
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);
    
    /**
     * 根据订单号查询订单项
     */
    @Select("SELECT * FROM oms_order_item WHERE order_no = #{orderNo}")
    List<OrderItem> selectByOrderNo(@Param("orderNo") String orderNo);
    
    /**
     * 批量插入订单项
     */
    int insertBatch(@Param("items") List<OrderItem> items);
}
