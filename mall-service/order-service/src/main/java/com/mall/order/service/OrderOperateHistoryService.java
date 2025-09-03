package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.order.entity.OrderOperateHistory;
import java.util.List;

/**
 * 订单操作记录服务接口
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistory> {
    
    /**
     * 保存操作记录
     */
    void saveOperateHistory(Long orderId, String orderNo, String operateType, String operateMan, Integer orderStatus, String note);
    
    /**
     * 获取操作记录
     */
    List<OrderOperateHistory> getByOrderId(Long orderId);
    
    /**
     * 获取操作记录
     */
    List<OrderOperateHistory> getByOrderNo(String orderNo);
}
