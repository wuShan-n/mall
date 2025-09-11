package com.mall.search.repository;

import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.response.AggregationVO;
import com.mall.api.search.dto.response.ProductSearchVO;
import com.mall.api.search.dto.response.SearchResultVO;
import com.mall.search.document.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchPage;

import java.util.List;
import java.util.Map;

/**
 * 自定义仓储接口，提供复杂搜索操作
 * 
 * @author mall
 */
public interface ProductSearchRepositoryCustom {

    /**
     * 高级商品搜索，支持复杂过滤、排序和聚合
     */
    SearchResultVO<ProductSearchVO> advancedSearch(ProductSearchQuery query);

    /**
     * 高级商品搜索，返回Spring Data分页结果
     */
    SearchPage<ProductDocument> advancedSearchDocuments(ProductSearchQuery query);

    /**
     * 搜索商品并返回自定义聚合结果
     */
    SearchResultVO<ProductSearchVO> searchWithAggregations(ProductSearchQuery query, List<String> aggregationFields);

    /**
     * 基于地理位置的距离搜索
     */
    Page<ProductDocument> searchNearby(Double latitude, Double longitude, String distance, Pageable pageable);

    /**
     * 根据属性过滤搜索商品
     */
    Page<ProductDocument> searchByAttributes(Map<String, List<String>> attributes, Pageable pageable);

    /**
     * 根据属性过滤和其他条件搜索商品
     */
    Page<ProductDocument> searchByAttributesAndConditions(
        Map<String, List<String>> attributes, 
        ProductSearchQuery baseQuery, 
        Pageable pageable);

    /**
     * 多字段全文搜索，支持自定义权重
     */
    SearchPage<ProductDocument> fullTextSearch(String query, Map<String, Float> fieldBoosts, Pageable pageable);

    /**
     * 自定义排序和过滤搜索
     */
    Page<ProductDocument> searchWithCustomSort(
        ProductSearchQuery query, 
        List<String> sortFields, 
        List<String> sortOrders, 
        Pageable pageable);

    /**
     * 获取聚合数据（用于过滤器/分面）
     */
    Map<String, List<AggregationVO>> getAggregations(ProductSearchQuery query);

    /**
     * 基于部分输入的搜索建议
     */
    List<String> getSuggestions(String partial, int limit);

    /**
     * 根据相似属性搜索商品（推荐功能）
     */
    List<ProductDocument> findSimilarProducts(Long productId, int limit);

    /**
     * 高级文本搜索，支持自动补全和模糊匹配
     */
    SearchPage<ProductDocument> smartTextSearch(String query, boolean fuzzy, Pageable pageable);

    /**
     * 按价格区间搜索商品，带直方图聚合
     */
    Map<String, Object> searchByPriceRangeWithHistogram(
        ProductSearchQuery query, 
        Double interval, 
        Pageable pageable);

    /**
     * 多分类搜索，支持分类路径过滤
     */
    Page<ProductDocument> searchInCategoryPath(String categoryPath, ProductSearchQuery query, Pageable pageable);

    /**
     * 搜索最近查看或热门商品
     */
    List<ProductDocument> getRecentOrPopularProducts(String type, Long userId, int limit);

    /**
     * 索引管理的批量操作
     */
    void bulkIndex(List<ProductDocument> documents);

    /**
     * 根据ID批量删除
     */
    void bulkDelete(List<String> documentIds);
}