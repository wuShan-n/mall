package com.mall.api.order.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
class ReturnAddressVO implements Serializable {
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private String postalCode;
}