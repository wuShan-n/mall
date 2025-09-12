package com.mall.search.service;


import com.mall.search.dto.response.HotWordDTO;

import java.util.List;

/**
 * =====================================================
 * 服务接口定义
 * =====================================================
 */

/**
 * 搜索建议服务接口
 */
public interface SuggestService {
    /**
     * 获取搜索建议
     */
    List<String> suggest(String keyword, Integer size);
    
    /**
     * 更新搜索建议索引
     */
    void updateSuggestIndex(String keyword, Integer weight);
    
    /**
     * 批量导入热词
     */
    void batchImportHotWords(List<HotWordDTO> hotWords);
}