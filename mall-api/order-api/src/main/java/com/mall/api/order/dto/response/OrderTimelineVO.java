package com.mall.api.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Order timeline view object
 */
@Data
@Schema(description = "Order timeline")
public class OrderTimelineVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Title", example = "Order Created")
    private String title;
    
    @Schema(description = "Description", example = "Your order has been created successfully")
    private String description;
    
    @Schema(description = "Time", example = "2024-01-01 09:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    
    @Schema(description = "Status", example = "completed")
    private String status;
    
    @Schema(description = "Icon", example = "check-circle")
    private String icon;
}
