package com.mall.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.service.PaymentOrderService;
import com.mall.payment.mapper.PaymentOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 21279
* @description 针对表【payment_order(支付订单表)】的数据库操作Service实现
* @createDate 2025-09-03 00:45:36
*/
@Service
public class PaymentOrderServiceImpl extends ServiceImpl<PaymentOrderMapper, PaymentOrder>
    implements PaymentOrderService{

}




