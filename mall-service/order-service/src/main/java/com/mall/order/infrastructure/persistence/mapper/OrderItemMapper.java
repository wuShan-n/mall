package com.mall.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.infrastructure.persistence.po.OrderItemPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项Mapper
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItemPO> {
}