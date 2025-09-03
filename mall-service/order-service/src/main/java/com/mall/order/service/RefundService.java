package com.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.RefundVO;
import com.mall.common.result.PageResult;
import com.mall.order.entity.OrderRefund;
import java.util.List;

/**
 * 退款服务接口
 */
public interface RefundService extends IService<OrderRefund> {
    
    /**
     * 创建退款申请
     */
    RefundVO createRefund(Long userId, RefundCreateRequest request);
    
    /**
     * 取消退款申请
     */
    void cancelRefund(Long userId, String refundNo);
    
    /**
     * 处理退款申请
     */
    void processRefund(RefundProcessRequest request);
    
    /**
     * 批准退款
     */
    void approveRefund(String refundNo, String handleNote);
    
    /**
     * 拒绝退款
     */
    void rejectRefund(String refundNo, String handleNote);
    
    /**
     * 完成退款
     */
    void completeRefund(String refundNo);
    
    /**
     * 根据退款号获取退款信息
     */
    RefundVO getRefundByNo(String refundNo);
    
    /**
     * 获取退款详情
     */
    RefundDetailVO getRefundDetail(String refundNo);
    
    /**
     * 查询退款列表
     */
    PageResult<RefundVO> queryRefunds(RefundQueryRequest request);
    
    /**
     * 获取用户退款列表
     */
    PageResult<RefundVO> getUserRefunds(Long userId, Integer status, Long current, Long size);
    
    /**
     * 获取订单退款记录
     */
    List<RefundVO> getOrderRefunds(String orderNo);
    
    /**
     * 检查退款状态
     */
    RefundVO checkRefundStatus(String refundNo);
}