package com.mall.api.user.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Point record query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Point record query request")
public class PointRecordQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Type: 1-Earn, 2-Spend", example = "1")
    private Integer type;
    
    @Schema(description = "Source", example = "ORDER")
    private String source;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
}