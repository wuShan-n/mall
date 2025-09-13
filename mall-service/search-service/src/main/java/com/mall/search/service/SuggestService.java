package com.mall.search.service;


import com.mall.search.dto.response.HotWordDTO;

import java.util.List;

/**
 * 搜索建议服务接口
 */
public interface SuggestService {
    /**
     * 获取搜索建议
     */
    List<String> suggest(String keyword, Integer size);

}