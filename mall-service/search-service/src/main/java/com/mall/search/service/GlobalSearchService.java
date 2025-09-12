package com.mall.search.service;

import com.mall.search.dto.request.GlobalSearchResult;
import com.mall.search.dto.response.HotKeywordVO;

import java.util.List;

/**
 * 全局搜索服务接口
 */
public interface GlobalSearchService {
    /**
     * 全局搜索
     */
    GlobalSearchResult search(String keyword, Integer size);
    
    /**
     * 获取热搜词
     */
    List<HotKeywordVO> getHotKeywords(Integer size);
    
    /**
     * 获取用户搜索历史
     */
    List<String> getSearchHistory(Long userId, Integer size);
    
    /**
     * 记录搜索历史
     */
    void recordSearchHistory(Long userId, String keyword);
}