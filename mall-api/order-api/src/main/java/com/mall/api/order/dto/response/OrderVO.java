package com.mall.api.order.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order information")
public class OrderVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Order ID", example = "1")
    private Long id;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Username", example = "user123")
    private String username;
    
    @Schema(description = "Total amount", example = "1000.00")
    private BigDecimal totalAmount;
    
    @Schema(description = "Product amount", example = "900.00")
    private BigDecimal productAmount;
    
    @Schema(description = "Freight amount", example = "10.00")
    private BigDecimal freightAmount;
    
    @Schema(description = "Discount amount", example = "50.00")
    private BigDecimal discountAmount;
    
    @Schema(description = "Pay amount", example = "960.00")
    private BigDecimal payAmount;
    
    @Schema(description = "Payment type", example = "1")
    private Integer payType;
    
    @Schema(description = "Payment type name", example = "Alipay")
    private String payTypeName;
    
    @Schema(description = "Source type", example = "2")
    private Integer sourceType;
    
    @Schema(description = "Source type name", example = "Mobile App")
    private String sourceTypeName;
    
    @Schema(description = "Order status", example = "1")
    private Integer status;
    
    @Schema(description = "Order status name", example = "Pending Delivery")
    private String statusName;
    
    @Schema(description = "Delivery company", example = "SF Express")
    private String deliveryCompany;
    
    @Schema(description = "Delivery number", example = "SF123456789")
    private String deliveryNo;
    
    @Schema(description = "Receiver name", example = "John Doe")
    private String receiverName;
    
    @Schema(description = "Receiver phone", example = "13800138000")
    private String receiverPhone;
    
    @Schema(description = "Receiver province", example = "Beijing")
    private String receiverProvince;
    
    @Schema(description = "Receiver city", example = "Beijing")
    private String receiverCity;
    
    @Schema(description = "Receiver district", example = "Chaoyang")
    private String receiverDistrict;
    
    @Schema(description = "Receiver address", example = "No.1 Street")
    private String receiverAddress;
    
    @Schema(description = "Order remark", example = "Please deliver in the morning")
    private String remark;
    
    @Schema(description = "Confirm status", example = "0")
    private Integer confirmStatus;
    
    @Schema(description = "Payment time", example = "2024-01-01 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;
    
    @Schema(description = "Delivery time", example = "2024-01-02 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryTime;
    
    @Schema(description = "Receive time", example = "2024-01-05 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;
    
    @Schema(description = "Comment time", example = "2024-01-06 10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentTime;
    
    @Schema(description = "Create time", example = "2024-01-01 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    @Schema(description = "Item count", example = "3")
    private Integer itemCount;
    
    @Schema(description = "Can cancel", example = "true")
    private Boolean canCancel;
    
    @Schema(description = "Can pay", example = "false")
    private Boolean canPay;
    
    @Schema(description = "Can refund", example = "true")
    private Boolean canRefund;
    
    @Schema(description = "Can receive", example = "false")
    private Boolean canReceive;
    
    @Schema(description = "Can comment", example = "false")
    private Boolean canComment;
}