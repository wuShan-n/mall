package com.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.order.entity.OrderOperateHistory;
import com.mall.order.mapper.OrderOperateHistoryMapper;
import com.mall.order.service.OrderOperateHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单操作记录服务实现类
 */
@Slf4j
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory> 
        implements OrderOperateHistoryService {
    
    @Override
    public void saveOperateHistory(Long orderId, String orderNo, String operateType, 
                                  String operateMan, Integer orderStatus, String note) {
        OrderOperateHistory history = new OrderOperateHistory();
        history.setOrderId(orderId);
        history.setOrderNo(orderNo);
        history.setOperateType(operateType);
        history.setOperateMan(operateMan);
        history.setOrderStatus(orderStatus);
        history.setNote(note);
        
        this.save(history);
        log.info("Saved operate history: {}", history);
    }
    
    @Override
    public List<OrderOperateHistory> getByOrderId(Long orderId) {
        return baseMapper.selectByOrderId(orderId);
    }
    
    @Override
    public List<OrderOperateHistory> getByOrderNo(String orderNo) {
        return baseMapper.selectByOrderNo(orderNo);
    }
}