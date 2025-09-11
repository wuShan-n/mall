package com.mall.search.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.Map;

/**
 * Search statistics mapper
 */
@Mapper
public interface SearchStatisticsMapper {
    
    /**
     * Get total searches by date
     */
    @Select("SELECT COUNT(*) FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND deleted = 0")
    Long getTotalSearchesByDate(@Param("date") LocalDate date);
    
    /**
     * Get unique users by date
     */
    @Select("SELECT COUNT(DISTINCT user_id) FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND deleted = 0")
    Long getUniqueUsersByDate(@Param("date") LocalDate date);
    
    /**
     * Get no result searches by date
     */
    @Select("SELECT COUNT(*) FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND result_count = 0 AND deleted = 0")
    Long getNoResultSearchesByDate(@Param("date") LocalDate date);
    
    /**
     * Get hourly distribution
     */
    @Select("SELECT HOUR(create_time) as hour, COUNT(*) as count " +
            "FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND deleted = 0 " +
            "GROUP BY HOUR(create_time)")
    Map<Integer, Long> getHourlyDistribution(@Param("date") LocalDate date);
    
    /**
     * Get device distribution
     */
    @Select("SELECT device_type, COUNT(*) as count " +
            "FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND deleted = 0 " +
            "GROUP BY device_type")
    Map<String, Long> getDeviceDistribution(@Param("date") LocalDate date);
    
    /**
     * Get source distribution
     */
    @Select("SELECT search_source, COUNT(*) as count " +
            "FROM search_history " +
            "WHERE DATE(create_time) = #{date} AND deleted = 0 " +
            "GROUP BY search_source")
    Map<String, Long> getSourceDistribution(@Param("date") LocalDate date);
}