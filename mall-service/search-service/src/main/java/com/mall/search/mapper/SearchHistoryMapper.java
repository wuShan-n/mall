package com.mall.search.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.search.entity.SearchHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Search history mapper
 */
@Mapper
public interface SearchHistoryMapper extends BaseMapper<SearchHistory> {
    
    /**
     * Get user search history
     */
    @Select("SELECT * FROM search_history WHERE user_id = #{userId} AND deleted = 0 " +
            "ORDER BY create_time DESC LIMIT #{size}")
    List<SearchHistory> getUserHistory(@Param("userId") Long userId, @Param("size") Integer size);
    
    /**
     * Get user search history by type
     */
    @Select("SELECT * FROM search_history WHERE user_id = #{userId} AND search_type = #{searchType} " +
            "AND deleted = 0 ORDER BY create_time DESC LIMIT #{size}")
    List<SearchHistory> getUserHistoryByType(@Param("userId") Long userId, 
                                             @Param("searchType") String searchType, 
                                             @Param("size") Integer size);
    
    /**
     * Clear user search history
     */
    @Select("UPDATE search_history SET deleted = 1 WHERE user_id = #{userId}")
    void clearUserHistory(@Param("userId") Long userId);
    
    /**
     * Get popular search keywords
     */
    @Select("SELECT keyword, COUNT(*) as count FROM search_history " +
            "WHERE deleted = 0 AND create_time >= DATE_SUB(NOW(), INTERVAL #{days} DAY) " +
            "GROUP BY keyword ORDER BY count DESC LIMIT #{size}")
    List<SearchHistory> getPopularKeywords(@Param("days") Integer days, @Param("size") Integer size);
}