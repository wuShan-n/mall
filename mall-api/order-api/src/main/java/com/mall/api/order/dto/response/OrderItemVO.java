package com.mall.api.order.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * Order item view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order item information")
public class OrderItemVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Item ID", example = "1")
    private Long id;
    
    @Schema(description = "Order ID", example = "1")
    private Long orderId;
    
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @Schema(description = "SPU ID", example = "1")
    private Long spuId;
    
    @Schema(description = "SKU ID", example = "1")
    private Long skuId;
    
    @Schema(description = "Product name", example = "iPhone 15 Pro")
    private String productName;
    
    @Schema(description = "Product image", example = "https://example.com/product.jpg")
    private String productImage;
    
    @Schema(description = "Product specs", example = "256GB Natural Titanium")
    private String productSpecs;
    
    @Schema(description = "Product code", example = "IP15PRO")
    private String productCode;
    
    @Schema(description = "Price", example = "9999.00")
    private BigDecimal price;
    
    @Schema(description = "Quantity", example = "1")
    private Integer quantity;
    
    @Schema(description = "Total amount", example = "9999.00")
    private BigDecimal totalAmount;
    
    @Schema(description = "Discount amount", example = "100.00")
    private BigDecimal discountAmount;
    
    @Schema(description = "Real amount", example = "9899.00")
    private BigDecimal realAmount;
    
    @Schema(description = "Gift growth", example = "100")
    private Integer giftGrowth;
    
    @Schema(description = "Gift points", example = "999")
    private Integer giftPoint;
    
    @Schema(description = "Can refund", example = "true")
    private Boolean canRefund;
    
    @Schema(description = "Refund status", example = "0")
    private Integer refundStatus;
    
    @Schema(description = "Comment status", example = "0")
    private Integer commentStatus;
}
