package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "订单搜索请求")
public class OrderSearchRequest {
    
    @Schema(description = "订单号")
    private String orderNo;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "商品名称")
    private String productName;
    
    @Schema(description = "SKU编码")
    private String skuCode;
    
    @Schema(description = "订单状态列表")
    private List<Integer> orderStatus;
    
    @Schema(description = "支付方式")
    private String paymentType;
    
    @Schema(description = "下单时间范围")
    private DateRange orderTimeRange;
    
    @Schema(description = "支付时间范围")
    private DateRange paymentTimeRange;
    
    @Schema(description = "收货省份")
    private String receiverProvince;
    
    @Schema(description = "收货城市")
    private String receiverCity;
    
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    
    @Data
    public static class DateRange {
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;
    }
}
