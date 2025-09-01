package com.mall.api.order.dto.response;

import java.math.BigDecimal;
import java.util.Map;

public class RefundStatisticsVO {
    private Long totalRefunds;
    private BigDecimal totalRefundAmount;
    private BigDecimal refundRate;
    private Map<String, Long> refundReasonDistribution;
}