package com.mall.payment.converter;

import com.mall.api.payment.dto.response.PaymentVO;
import com.mall.api.payment.dto.response.RefundVO;
import com.mall.api.payment.enums.PaymentStatusEnum;
import com.mall.api.payment.enums.PaymentTypeEnum;
import com.mall.api.payment.enums.RefundStatusEnum;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.entity.RefundOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Payment entity converter
 */
@Mapper(componentModel = "spring")
public interface PaymentConverter {
    
    @Mapping(target = "paymentTypeName", source = "paymentType", qualifiedByName = "paymentTypeName")
    @Mapping(target = "statusName", source = "status", qualifiedByName = "paymentStatusName")
    PaymentVO toVO(PaymentOrder entity);
    
    List<PaymentVO> toVOList(List<PaymentOrder> entities);
    
    @Mapping(target = "statusName", source = "status", qualifiedByName = "refundStatusName")
    RefundVO toRefundVO(RefundOrder entity);
    
    List<RefundVO> toRefundVOList(List<RefundOrder> entities);
    
    @Named("paymentTypeName")
    default String mapPaymentTypeName(Integer paymentType) {
        if (paymentType == null) {
            return null;
        }
        PaymentTypeEnum typeEnum = PaymentTypeEnum.of(paymentType);
        return typeEnum != null ? typeEnum.getDesc() : null;
    }
    
    @Named("paymentStatusName")
    default String mapPaymentStatusName(Integer status) {
        if (status == null) {
            return null;
        }
        PaymentStatusEnum statusEnum = PaymentStatusEnum.of(status);
        return statusEnum != null ? statusEnum.getDesc() : null;
    }
    
    @Named("refundStatusName")
    default String mapRefundStatusName(Integer status) {
        if (status == null) {
            return null;
        }
        RefundStatusEnum statusEnum = RefundStatusEnum.of(status);
        return statusEnum != null ? statusEnum.getDesc() : null;
    }
}