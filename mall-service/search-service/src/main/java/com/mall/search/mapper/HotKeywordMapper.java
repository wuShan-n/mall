package com.mall.search.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.search.entity.HotKeyword;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * Hot keyword mapper
 */
@Mapper
public interface HotKeywordMapper extends BaseMapper<HotKeyword> {
    
    /**
     * Select top keywords
     */
    @Select("SELECT * FROM hot_keyword WHERE deleted = 0 AND status = 1 " +
            "AND (statistics_date IS NULL OR statistics_date = CURDATE()) " +
            "ORDER BY search_count DESC LIMIT #{size}")
    List<HotKeyword> selectTopKeywords(@Param("size") Integer size);
    
    /**
     * Select top keywords by category
     */
    @Select("SELECT * FROM hot_keyword WHERE category_id = #{categoryId} " +
            "AND deleted = 0 AND status = 1 " +
            "AND (statistics_date IS NULL OR statistics_date = CURDATE()) " +
            "ORDER BY search_count DESC LIMIT #{size}")
    List<HotKeyword> selectTopKeywordsByCategory(@Param("categoryId") Long categoryId, 
                                                 @Param("size") Integer size);
    
    /**
     * Select keywords by date
     */
    @Select("SELECT * FROM hot_keyword WHERE statistics_date = #{date} " +
            "AND deleted = 0 AND status = 1 " +
            "ORDER BY search_count DESC")
    List<HotKeyword> selectKeywordsByDate(@Param("date") LocalDate date);
    
    /**
     * Update keyword statistics
     */
    @Select("UPDATE hot_keyword SET search_count = search_count + 1 " +
            "WHERE keyword = #{keyword} AND statistics_date = CURDATE()")
    void incrementSearchCount(@Param("keyword") String keyword);
}
