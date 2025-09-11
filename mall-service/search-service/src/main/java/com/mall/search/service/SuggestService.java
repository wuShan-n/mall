package com.mall.search.service;

import com.mall.api.search.dto.request.SuggestRequest;
import com.mall.api.search.dto.response.HotKeywordVO;
import com.mall.api.search.dto.response.SuggestVO;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Suggest service interface
 */
public interface SuggestService {
    
    /**
     * Get search suggestions
     */
    Result<List<SuggestVO>> getSuggestions(SuggestRequest request);
    
    /**
     * Get hot keywords
     */
    Result<List<HotKeywordVO>> getHotKeywords(Integer size);
    
    /**
     * Get hot keywords by category
     */
    Result<List<HotKeywordVO>> getHotKeywordsByCategory(Long categoryId, Integer size);
    
    /**
     * Update hot keyword
     */
    void updateHotKeyword(String keyword, Long categoryId);
}
