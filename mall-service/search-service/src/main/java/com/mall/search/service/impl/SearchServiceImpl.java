package com.mall.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.request.SearchHistoryRequest;
import com.mall.api.search.dto.response.ProductSearchVO;
import com.mall.api.search.dto.response.SearchHistoryVO;
import com.mall.api.search.dto.response.SearchResultVO;
import com.mall.api.search.dto.response.SearchStatisticsVO;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import com.mall.search.entity.SearchHistory;
import com.mall.search.mapper.SearchHistoryMapper;
import com.mall.search.mapper.SearchStatisticsMapper;
import com.mall.search.repository.ProductSearchRepository;
import com.mall.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 搜索服务实现类
 * 
 * @author mall
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    
    private final ProductSearchRepository productSearchRepository;
    private final SearchHistoryMapper searchHistoryMapper;
    private final SearchStatisticsMapper searchStatisticsMapper;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public Result<SearchResultVO<ProductSearchVO>> searchProducts(ProductSearchQuery request) {
        try {
            log.info("使用查询搜索商品: {}", request);
            
            // 使用Spring Data Elasticsearch进行高级搜索
            SearchResultVO<ProductSearchVO> result = productSearchRepository.advancedSearch(request);
            
            // 如果用户已登录，保存搜索历史
            // TODO: 如果需要，向ProductSearchQuery添加userId
            // if (request.getUserId() != null) {
            //     saveSearchHistoryAsync(request.getUserId(), request.getKeyword(), 
            //         request.getSearchType(), (int) result.getTotal());
            // }
            
            log.info("搜索完成，找到 {} 个商品", result.getTotal());
            return Result.success(result);
            
        } catch (Exception e) {
            log.error("搜索商品错误", e);
            throw new BusinessException("搜索失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> saveSearchHistory(Long userId, String keyword, String searchType) {
        try {
            SearchHistory history = new SearchHistory();
            history.setUserId(userId);
            history.setKeyword(keyword);
            history.setSearchType(searchType);
            history.setResultCount(0); // 将由实际搜索更新
            history.setClickCount(0);
            history.setSearchSource("web");
            history.setDeviceType("desktop");
            history.setTrackingId(UUID.randomUUID().toString());
            
            searchHistoryMapper.insert(history);
            return Result.success();
        } catch (Exception e) {
            log.error("保存搜索历史错误", e);
            return Result.failed("保存搜索历史失败");
        }
    }

    @Override
    public Result<List<SearchHistoryVO>> getUserSearchHistory(SearchHistoryRequest request) {
        try {
            List<SearchHistory> histories;
            if (StringUtils.hasText(request.getSearchType())) {
                histories = searchHistoryMapper.getUserHistoryByType(
                    request.getUserId(), request.getSearchType(), request.getSize());
            } else {
                histories = searchHistoryMapper.getUserHistory(request.getUserId(), request.getSize());
            }
            
            List<SearchHistoryVO> result = histories.stream()
                .map(this::convertToHistoryVO)
                .collect(Collectors.toList());
                
            return Result.success(result);
        } catch (Exception e) {
            log.error("获取用户搜索历史错误", e);
            return Result.failed("获取搜索历史失败");
        }
    }

    @Override
    public Result<Void> clearUserSearchHistory(Long userId) {
        try {
            searchHistoryMapper.clearUserHistory(userId);
            return Result.success();
        } catch (Exception e) {
            log.error("清理用户搜索历史错误", e);
            return Result.failed("清理搜索历史失败");
        }
    }

    @Override
    public Result<Void> deleteSearchHistory(Long userId, Long historyId) {
        try {
            LambdaUpdateWrapper<SearchHistory> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(SearchHistory::getUserId, userId)
                         .eq(SearchHistory::getId, historyId)
                         .set(SearchHistory::getDeleted, 1);
                         
            searchHistoryMapper.update(null, updateWrapper);
            return Result.success();
        } catch (Exception e) {
            log.error("删除搜索历史错误", e);
            return Result.failed("删除搜索历史失败");
        }
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatistics() {
        try {
            LocalDate today = LocalDate.now();
            return getSearchStatisticsByDateRange(today.format(DATE_FORMATTER), today.format(DATE_FORMATTER));
        } catch (Exception e) {
            log.error("获取搜索统计错误", e);
            return Result.failed("获取搜索统计失败");
        }
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatisticsByDateRange(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
            LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);
            
            SearchStatisticsVO statistics = new SearchStatisticsVO();
            
            // 计算日期范围内的总统计
            long totalSearches = 0;
            long uniqueUsers = 0;
            long noResultSearches = 0;
            
            LocalDate current = start;
            while (!current.isAfter(end)) {
                totalSearches += searchStatisticsMapper.getTotalSearchesByDate(current);
                uniqueUsers += searchStatisticsMapper.getUniqueUsersByDate(current);
                noResultSearches += searchStatisticsMapper.getNoResultSearchesByDate(current);
                current = current.plusDays(1);
            }
            
            // 使用正确的字段名设置统计数据
            statistics.setTotalSearchesToday(totalSearches);
            statistics.setUniqueUsersToday(uniqueUsers);
            statistics.setNoResultSearches(noResultSearches);
            
            // 获取结束日期的额外分布数据
            statistics.setHourlyTrend(searchStatisticsMapper.getHourlyDistribution(end));
            statistics.setDeviceDistribution(searchStatisticsMapper.getDeviceDistribution(end));
            statistics.setSourceDistribution(searchStatisticsMapper.getSourceDistribution(end));
            
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("按日期范围获取搜索统计错误", e);
            return Result.failed("获取搜索统计失败");
        }
    }

    @Override
    public Result<Void> recordSearchClick(String trackingId, Long productId, Integer position) {
        try {
            // 更新搜索历史的点击数
            LambdaUpdateWrapper<SearchHistory> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(SearchHistory::getTrackingId, trackingId)
                         .setSql("click_count = click_count + 1");
            
            searchHistoryMapper.update(null, updateWrapper);
            
            // TODO: 如果需要，记录详细的点击分析
            log.info("记录搜索点击 - 跟踪ID: {}, 商品ID: {}, 位置: {}", 
                trackingId, productId, position);
                
            return Result.success();
        } catch (Exception e) {
            log.error("记录搜索点击错误", e);
            return Result.failed("记录搜索点击失败");
        }
    }

    @Override
    public Result<Void> recordSearchConversion(String trackingId, Long orderId) {
        try {
            // TODO: 记录搜索转化分析
            log.info("记录搜索转化 - 跟踪ID: {}, 订单ID: {}", trackingId, orderId);
            return Result.success();
        } catch (Exception e) {
            log.error("记录搜索转化错误", e);
            return Result.failed("记录搜索转化失败");
        }
    }
    
    /**
     * 将SearchHistory转换为SearchHistoryVO
     */
    private SearchHistoryVO convertToHistoryVO(SearchHistory history) {
        SearchHistoryVO vo = new SearchHistoryVO();
        BeanUtils.copyProperties(history, vo);
        return vo;
    }
    
    /**
     * 异步保存搜索历史
     */
    private void saveSearchHistoryAsync(Long userId, String keyword, String searchType, int resultCount) {
        try {
            SearchHistory history = new SearchHistory();
            history.setUserId(userId);
            history.setKeyword(keyword);
            history.setSearchType(searchType);
            history.setResultCount(resultCount);
            history.setClickCount(0);
            history.setSearchSource("web");
            history.setDeviceType("desktop");
            history.setTrackingId(UUID.randomUUID().toString());
            
            searchHistoryMapper.insert(history);
        } catch (Exception e) {
            log.warn("异步保存搜索历史失败", e);
        }
    }

}