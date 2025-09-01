package com.mall.api.order.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Order comment request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Order comment request")
public class OrderCommentRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Order number cannot be empty")
    @Schema(description = "Order number", example = "ORD202401010001")
    private String orderNo;
    
    @NotNull(message = "Comments cannot be null")
    @Schema(description = "Item comments")
    private List<ItemComment> comments;
    
    @Data
    @Schema(description = "Item comment")
    public static class ItemComment {
        
        @NotNull(message = "Order item ID cannot be null")
        @Schema(description = "Order item ID", example = "1")
        private Long orderItemId;
        
        @NotNull(message = "Rating cannot be null")
        @Schema(description = "Rating (1-5)", example = "5")
        private Integer rating;
        
        @Schema(description = "Comment content", example = "Great product!")
        private String content;
        
        @Schema(description = "Comment images")
        private List<String> images;
        
        @Schema(description = "Is anonymous", example = "false")
        private Boolean anonymous = false;
    }
}