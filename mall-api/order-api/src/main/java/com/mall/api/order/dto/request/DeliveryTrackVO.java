package com.mall.api.order.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Delivery tracking view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Delivery tracking information")
public class DeliveryTrackVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Delivery company", example = "SF Express")
    private String deliveryCompany;
    
    @Schema(description = "Delivery number", example = "SF123456789")
    private String deliveryNo;
    
    @Schema(description = "Current status", example = "In Transit")
    private String currentStatus;
    
    @Schema(description = "Tracking details")
    private List<TrackDetail> trackDetails;
    
    @Data
    @Schema(description = "Track detail")
    public static class TrackDetail {
        
        @Schema(description = "Time", example = "2024-01-02 10:00:00")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime time;
        
        @Schema(description = "Location", example = "Beijing Distribution Center")
        private String location;
        
        @Schema(description = "Status", example = "Package arrived")
        private String status;
        
        @Schema(description = "Description", example = "Package has arrived at Beijing Distribution Center")
        private String description;
    }
}
