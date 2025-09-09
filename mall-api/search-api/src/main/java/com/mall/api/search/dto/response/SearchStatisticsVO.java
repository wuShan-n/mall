package com.mall.api.search.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Search statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Search statistics")
public class SearchStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total searches today", example = "10000")
    private Long totalSearchesToday;
    
    @Schema(description = "Total searches this week", example = "70000")
    private Long totalSearchesWeek;
    
    @Schema(description = "Total searches this month", example = "300000")
    private Long totalSearchesMonth;
    
    @Schema(description = "Unique users today", example = "5000")
    private Long uniqueUsersToday;
    
    @Schema(description = "Average searches per user", example = "2.5")
    private BigDecimal avgSearchesPerUser;
    
    @Schema(description = "No result searches count", example = "100")
    private Long noResultSearches;
    
    @Schema(description = "No result rate percentage", example = "1.0")
    private BigDecimal noResultRate;
    
    @Schema(description = "Click through rate", example = "65.5")
    private BigDecimal clickThroughRate;
    
    @Schema(description = "Conversion rate", example = "15.3")
    private BigDecimal conversionRate;
    
    @Schema(description = "Average search depth", example = "2.3")
    private BigDecimal avgSearchDepth;
    
    @Schema(description = "Top search keywords")
    private List<KeywordStatVO> topKeywords;
    
    @Schema(description = "Top no result keywords")
    private List<KeywordStatVO> topNoResultKeywords;
    
    @Schema(description = "Search trend by hour")
    private Map<Integer, Long> hourlyTrend;
    
    @Schema(description = "Search trend by day")
    private Map<String, Long> dailyTrend;
    
    @Schema(description = "Device distribution")
    private Map<String, Long> deviceDistribution;
    
    @Schema(description = "Source distribution")
    private Map<String, Long> sourceDistribution;
    
    /**
     * Keyword statistics
     */
    @Data
    public static class KeywordStatVO {
        @Schema(description = "Keyword", example = "iPhone")
        private String keyword;
        
        @Schema(description = "Search count", example = "1000")
        private Long count;
        
        @Schema(description = "Percentage", example = "10.5")
        private BigDecimal percentage;
        
        @Schema(description = "Click count", example = "800")
        private Long clickCount;
        
        @Schema(description = "Conversion count", example = "150")
        private Long conversionCount;
    }
}