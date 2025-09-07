package com.mall.order.infrastructure.repository.converter;

import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.valueobject.*;
import com.mall.order.infrastructure.persistence.po.OrderItemPO;
import com.mall.order.infrastructure.persistence.po.OrderPO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单转换器
 * 负责领域对象和持久化对象之间的转换
 */
@Component
public class OrderConverter {
    
    /**
     * 领域对象转PO
     */
    public OrderPO toPO(Order order) {
        OrderPO po = new OrderPO();
        po.setOrderNo(order.getOrderNo().getValue());
        po.setUserId(order.getUserId().getValue());
        po.setTotalAmount(order.getTotalAmount().getAmount());
        po.setProductAmount(order.getProductAmount().getAmount());
        po.setFreightAmount(order.getFreightAmount().getAmount());
        po.setDiscountAmount(order.getDiscountAmount().getAmount());
        po.setPayAmount(order.getPayAmount().getAmount());
        po.setPaymentType(order.getPaymentType().getCode());
        po.setPaymentTime(order.getPaymentTime());
        po.setPaymentNo(order.getPaymentNo());
        po.setStatus(order.getStatus().getCode());
        
        // 设置收货地址
        if (order.getAddress() != null) {
            po.setReceiverName(order.getAddress().getReceiverName());
            po.setReceiverPhone(order.getAddress().getReceiverPhone());
            po.setReceiverProvince(order.getAddress().getProvince());
            po.setReceiverCity(order.getAddress().getCity());
            po.setReceiverDistrict(order.getAddress().getDistrict());
            po.setReceiverAddress(order.getAddress().getDetailAddress());
            po.setReceiverPostalCode(order.getAddress().getPostalCode());
        }
        
        po.setRemark(order.getRemark());
        po.setCreateTime(order.getCreateTime());
        po.setUpdateTime(order.getUpdateTime());
        
        return po;
    }
    
    /**
     * 订单项转PO列表
     */
    public List<OrderItemPO> toItemPOs(List<OrderItem> items, Long orderId, String orderNo) {
        List<OrderItemPO> pos = new ArrayList<>();
        for (OrderItem item : items) {
            OrderItemPO po = new OrderItemPO();
            po.setOrderId(orderId);
            po.setOrderNo(orderNo);
            po.setSkuId(item.getSkuId());
            po.setProductName(item.getSkuName());
            po.setProductImage(item.getSkuImage());
            po.setProductSpecs(item.getSkuSpecs());
            po.setPrice(item.getPrice());
            po.setQuantity(item.getQuantity());
            po.setTotalAmount(item.getTotalAmount());
            po.setDiscountAmount(item.getDiscountAmount());
            po.setRealAmount(item.getRealAmount());
            pos.add(po);
        }
        return pos;
    }
    
    /**
     * PO转领域对象
     */
    public Order toDomain(OrderPO po, List<OrderItemPO> itemPOs) {
        // 转换订单项
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemPO itemPO : itemPOs) {
            OrderItem item = OrderItem.builder()
                .id(itemPO.getId())
                .skuId(itemPO.getSkuId())
                .skuName(itemPO.getProductName())
                .skuImage(itemPO.getProductImage())
                .skuSpecs(itemPO.getProductSpecs())
                .price(itemPO.getPrice())
                .quantity(itemPO.getQuantity())
                .totalAmount(itemPO.getTotalAmount())
                .discountAmount(itemPO.getDiscountAmount())
                .realAmount(itemPO.getRealAmount())
                .build();
            items.add(item);
        }
        
        // 转换收货地址
        OrderAddress address = new OrderAddress(
            po.getReceiverName(),
            po.getReceiverPhone(),
            po.getReceiverProvince(),
            po.getReceiverCity(),
            po.getReceiverDistrict(),
            po.getReceiverAddress(),
            po.getReceiverPostalCode()
        );
        
        // 构建订单对象
        Order order = Order.builder()
            .id(po.getId())
            .orderNo(new OrderNo(po.getOrderNo()))
            .userId(new UserId(po.getUserId()))
            .status(OrderStatus.fromCode(po.getStatus()))
            .totalAmount(new Money(po.getTotalAmount()))
            .productAmount(new Money(po.getProductAmount()))
            .freightAmount(new Money(po.getFreightAmount()))
            .discountAmount(new Money(po.getDiscountAmount()))
            .payAmount(new Money(po.getPayAmount()))
            .paymentType(PaymentType.fromCode(po.getPaymentType()))
            .paymentTime(po.getPaymentTime())
            .paymentNo(po.getPaymentNo())
            .address(address)
            .orderItems(items)
            .remark(po.getRemark())
            .createTime(po.getCreateTime())
            .updateTime(po.getUpdateTime())
            .build();
        
        return order;
    }
}