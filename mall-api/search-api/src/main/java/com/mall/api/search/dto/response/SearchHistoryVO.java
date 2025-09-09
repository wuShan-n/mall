package com.mall.api.search.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Search history view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search history")
public class SearchHistoryVO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "History ID", example = "1")
    private Long id;

    @Schema(description = "User ID", example = "1")
    private Long userId;

    @Schema(description = "Search keyword", example = "iPhone")
    private String keyword;

    @Schema(description = "Search type", example = "product")
    private String searchType;

    @Schema(description = "Result count", example = "100")
    private Integer resultCount;

    @Schema(description = "Click count", example = "5")
    private Integer clickCount;

    @Schema(description = "Last search time", example = "2024-01-01 10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime searchTime;

    @Schema(description = "Search source: WEB/APP/MINI_PROGRAM", example = "WEB")
    private String searchSource;

    @Schema(description = "Device type", example = "iPhone")
    private String deviceType;

    @Schema(description = "IP address", example = "192.168.1.1")
    private String ipAddress;
}