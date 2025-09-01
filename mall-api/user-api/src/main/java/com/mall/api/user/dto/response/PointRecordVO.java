package com.mall.api.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Point record view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Point record")
public class PointRecordVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Record ID", example = "1")
    private Long id;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Points change", example = "+100")
    private Integer points;
    
    @Schema(description = "Balance after change", example = "1500")
    private Integer balance;
    
    @Schema(description = "Type: 1-Earn, 2-Spend", example = "1")
    private Integer type;
    
    @Schema(description = "Type description", example = "Earn")
    private String typeDesc;
    
    @Schema(description = "Source", example = "ORDER_COMPLETE")
    private String source;
    
    @Schema(description = "Source description", example = "Order completed")
    private String sourceDesc;
    
    @Schema(description = "Source ID", example = "ORDER123456")
    private String sourceId;
    
    @Schema(description = "Remark", example = "Order #123456 completed, earned 100 points")
    private String remark;
    
    @Schema(description = "Create time", example = "2024-01-01 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
