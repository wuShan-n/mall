package com.mall.order.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.order.infrastructure.persistence.po.RefundPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 退款Mapper
 */
@Mapper
public interface RefundMapper extends BaseMapper<RefundPO> {
    
    /**
     * 检查订单是否有进行中的退款
     */
    @Select("SELECT COUNT(*) FROM oms_order_refund WHERE order_no = #{orderNo} AND status IN (0, 1, 2)")
    Integer countActiveRefund(@Param("orderNo") String orderNo);
}
