package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.List;

/**
 * Order create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order create request")
public class OrderCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotEmpty(message = "Order items cannot be empty")
    @Schema(description = "Order items")
    private List<OrderItemRequest> orderItems;
    
    @NotNull(message = "Address ID cannot be null")
    @Schema(description = "Delivery address ID", example = "1")
    private Long addressId;
    
    @Schema(description = "Coupon ID", example = "1")
    private Long couponId;
    
    @Schema(description = "Use points", example = "100")
    private Integer usePoints;
    
    @Schema(description = "Payment type: 1-Alipay, 2-WeChat", example = "1")
    private Integer paymentType;
    
    @Schema(description = "Order source: 1-PC, 2-APP, 3-Mini Program", example = "2")
    private Integer sourceType;
    
    @Schema(description = "Order remark", example = "Please deliver in the morning")
    private String remark;
    
    @Schema(description = "Invoice required", example = "false")
    private Boolean invoiceRequired = false;
    
    @Schema(description = "Invoice title", example = "Company Name")
    private String invoiceTitle;
    
    @Schema(description = "Invoice tax number", example = "123456789")
    private String invoiceTaxNumber;
}