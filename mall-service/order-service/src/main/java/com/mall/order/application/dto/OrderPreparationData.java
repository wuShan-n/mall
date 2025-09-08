package com.mall.order.application.dto;

import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 订单创建准备数据
 * 包含验证和准备阶段的所有数据
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPreparationData {
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 用户信息
     */
    private UserVO userInfo;
    
    /**
     * 收货地址信息
     */
    private AddressVO addressInfo;
    
    /**
     * 构建好的订单地址
     */
    private OrderAddress orderAddress;
    
    /**
     * SKU映射表
     */
    private Map<Long, SkuVO> skuMap;
    
    /**
     * 构建好的订单项列表
     */
    private List<OrderItem> orderItems;
    
    /**
     * 支付方式
     */
    private Integer paymentType;
    
    /**
     * 订单备注
     */
    private String remark;
    
    /**
     * 优惠券ID
     */
    private Long couponId;
    
    /**
     * 使用积分
     */
    private Integer usePoints;
}