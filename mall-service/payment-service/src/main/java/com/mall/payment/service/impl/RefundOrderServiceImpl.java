package com.mall.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.payment.entity.RefundOrder;
import com.mall.payment.service.RefundOrderService;
import com.mall.payment.mapper.RefundOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author 21279
* @description 针对表【refund_order(退款订单表)】的数据库操作Service实现
* @createDate 2025-09-03 00:45:36
*/
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, RefundOrder>
    implements RefundOrderService{

}




