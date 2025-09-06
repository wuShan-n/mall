package com.mall.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.infrastructure.persistence.po.OrderOperateHistoryPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作记录Mapper
 */
@Mapper
public interface OrderOperateHistoryMapper extends BaseMapper<OrderOperateHistoryPO> {
}