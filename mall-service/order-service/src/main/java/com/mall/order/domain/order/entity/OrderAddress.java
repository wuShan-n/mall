package com.mall.order.domain.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 收货地址值对象
 * DDD核心：值对象是不可变的，通过值比较相等性
 */
@Getter
@AllArgsConstructor
public class OrderAddress {
    
    private final String receiverName;
    private final String receiverPhone;
    private final String province;
    private final String city;
    private final String district;
    private final String detailAddress;
    private final String postalCode;
    
    /**
     * 获取完整地址
     */
    public String getFullAddress() {
        return province + city + district + detailAddress;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        OrderAddress that = (OrderAddress) o;
        
        return receiverName.equals(that.receiverName) &&
               receiverPhone.equals(that.receiverPhone) &&
               province.equals(that.province) &&
               city.equals(that.city) &&
               district.equals(that.district) &&
               detailAddress.equals(that.detailAddress);
    }
    
    @Override
    public int hashCode() {
        int result = receiverName.hashCode();
        result = 31 * result + receiverPhone.hashCode();
        result = 31 * result + province.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + district.hashCode();
        result = 31 * result + detailAddress.hashCode();
        return result;
    }
}