package com.mall.order.interfaces.assembler;

import com.mall.api.order.dto.response.OrderDetailVO;
import com.mall.api.order.dto.response.OrderItemVO;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单装配器
 * 负责领域对象和DTO之间的转换
 */
@Component
public class OrderAssembler {
    
    /**
     * 转换为OrderVO
     */
    public OrderVO toOrderVO(Order order) {
        OrderVO vo = new OrderVO();
        vo.setId(order.getId());
        vo.setOrderNo(order.getOrderNo().getValue());
        vo.setUserId(order.getUserId().getValue());
        vo.setTotalAmount(order.getTotalAmount().getAmount());
        vo.setProductAmount(order.getProductAmount().getAmount());
        vo.setFreightAmount(order.getFreightAmount().getAmount());
        vo.setDiscountAmount(order.getDiscountAmount().getAmount());
        vo.setPayAmount(order.getPayAmount().getAmount());
        vo.setStatus(order.getStatus().getCode());
        vo.setStatusName(order.getStatus().getDescription());
        vo.setPayType(order.getPaymentType().getCode());
        vo.setPayTypeName(order.getPaymentType().getDescription());
        
        // 设置收货地址
        if (order.getAddress() != null) {
            vo.setReceiverName(order.getAddress().getReceiverName());
            vo.setReceiverPhone(order.getAddress().getReceiverPhone());
            vo.setReceiverProvince(order.getAddress().getProvince());
            vo.setReceiverCity(order.getAddress().getCity());
            vo.setReceiverDistrict(order.getAddress().getDistrict());
            vo.setReceiverAddress(order.getAddress().getDetailAddress());
        }
        
        vo.setRemark(order.getRemark());
        vo.setPaymentTime(order.getPaymentTime());
        vo.setCreateTime(order.getCreateTime());
        
        // 设置操作权限
        vo.setCanCancel(order.getStatus().canCancel());
        vo.setCanPay(order.getStatus().canPay());
        vo.setCanRefund(order.getStatus().canRefund());
        
        return vo;
    }
    
    /**
     * 转换为OrderDetailVO
     */
    public OrderDetailVO toOrderDetailVO(Order order) {
        OrderDetailVO vo = new OrderDetailVO();
        
        // 复制基本信息
        OrderVO orderVO = toOrderVO(order);
        vo.setId(orderVO.getId());
        vo.setOrderNo(orderVO.getOrderNo());
        vo.setUserId(orderVO.getUserId());
        vo.setTotalAmount(orderVO.getTotalAmount());
        vo.setProductAmount(orderVO.getProductAmount());
        vo.setFreightAmount(orderVO.getFreightAmount());
        vo.setDiscountAmount(orderVO.getDiscountAmount());
        vo.setPayAmount(orderVO.getPayAmount());
        vo.setStatus(orderVO.getStatus());
        vo.setStatusName(orderVO.getStatusName());
        
        // 转换订单项
        List<OrderItemVO> itemVOs = order.getOrderItems().stream()
            .map(this::toOrderItemVO)
            .collect(Collectors.toList());
        vo.setOrderItems(itemVOs);
        
        // TODO: 设置其他详情信息
        
        return vo;
    }
    
    /**
     * 转换为OrderItemVO
     */
    private OrderItemVO toOrderItemVO(OrderItem item) {
        OrderItemVO vo = new OrderItemVO();
        vo.setId(item.getId());
        vo.setSkuId(item.getSkuId());
        vo.setProductName(item.getSkuName());
        vo.setProductImage(item.getSkuImage());
        vo.setProductSpecs(item.getSkuSpecs());
        vo.setPrice(item.getPrice());
        vo.setQuantity(item.getQuantity());
        vo.setTotalAmount(item.getTotalAmount());
        vo.setDiscountAmount(item.getDiscountAmount());
        vo.setRealAmount(item.getRealAmount());
        return vo;
    }
}