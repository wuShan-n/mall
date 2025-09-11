package com.mall.search.repository;

import com.mall.search.document.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品搜索仓储接口，提供全面的Spring Data Elasticsearch查询方法
 * 
 * @author mall
 */
@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String>, ProductSearchRepositoryCustom {

    // ========== 基础查询方法 ==========
    
    /**
     * 查找激活的商品，支持多字段搜索和高亮显示
     */
    @Highlight(fields = {
        @HighlightField(name = "productName"),
        @HighlightField(name = "keywords"),
        @HighlightField(name = "introduction")
    })
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"multi_match\": {" +
           "      \"query\": \"?0\"," +
           "      \"fields\": [\"productName^3\", \"keywords^2\", \"brandName\", \"categoryName\", \"introduction\"]," +
           "      \"type\": \"best_fields\"" +
           "    }}," +
           "    {\"term\": {\"status\": 1}}" +
           "  ]" +
           "}}")
    SearchPage<ProductDocument> searchProductsWithHighlight(String keyword, Pageable pageable);

    /**
     * 根据商品名称或关键词查找（简单搜索）
     */
    @Highlight(fields = {
        @HighlightField(name = "productName"),
        @HighlightField(name = "keywords")
    })
    SearchPage<ProductDocument> findByProductNameContainingOrKeywordsContaining(String productName, String keywords, Pageable pageable);

    // ========== 过滤查询方法 ==========
    
    /**
     * 根据状态查找（仅激活商品）
     */
    Page<ProductDocument> findByStatus(Integer status, Pageable pageable);

    /**
     * 根据分类ID查找
     */
    Page<ProductDocument> findByCategoryIdAndStatus(Long categoryId, Integer status, Pageable pageable);

    /**
     * 根据多个分类ID查找
     */
    Page<ProductDocument> findByCategoryIdInAndStatus(List<Long> categoryIds, Integer status, Pageable pageable);

    /**
     * 根据品牌ID查找
     */
    Page<ProductDocument> findByBrandIdAndStatus(Long brandId, Integer status, Pageable pageable);

    /**
     * 根据多个品牌ID查找
     */
    Page<ProductDocument> findByBrandIdInAndStatus(List<Long> brandIds, Integer status, Pageable pageable);

    /**
     * 根据价格区间查找
     */
    Page<ProductDocument> findByPriceBetweenAndStatus(BigDecimal minPrice, BigDecimal maxPrice, Integer status, Pageable pageable);

    /**
     * 根据最低评分查找
     */
    Page<ProductDocument> findByScoreGreaterThanEqualAndStatus(Float minScore, Integer status, Pageable pageable);

    /**
     * 根据库存状态查找
     */
    Page<ProductDocument> findByStockStatusAndStatus(String stockStatus, Integer status, Pageable pageable);

    // ========== 布尔值过滤方法 ==========
    
    /**
     * 查找有折扣的商品
     */
    Page<ProductDocument> findByHasDiscountTrueAndStatus(Integer status, Pageable pageable);

    /**
     * 查找新品
     */
    Page<ProductDocument> findByIsNewTrueAndStatus(Integer status, Pageable pageable);

    /**
     * 查找热门商品
     */
    Page<ProductDocument> findByIsHotTrueAndStatus(Integer status, Pageable pageable);

    /**
     * 查找精品推荐
     */
    Page<ProductDocument> findByIsBestTrueAndStatus(Integer status, Pageable pageable);

    // ========== 组合过滤方法 ==========
    
    /**
     * 根据分类和品牌查找
     */
    Page<ProductDocument> findByCategoryIdAndBrandIdAndStatus(Long categoryId, Long brandId, Integer status, Pageable pageable);

    /**
     * 根据分类和价格区间查找
     */
    Page<ProductDocument> findByCategoryIdAndPriceBetweenAndStatus(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Integer status, Pageable pageable);

    /**
     * 根据品牌和价格区间查找
     */
    Page<ProductDocument> findByBrandIdAndPriceBetweenAndStatus(Long brandId, BigDecimal minPrice, BigDecimal maxPrice, Integer status, Pageable pageable);

    /**
     * 复杂多条件搜索
     */
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"term\": {\"status\": ?0}}," +
           "    {\"term\": {\"categoryId\": ?1}}," +
           "    {\"term\": {\"brandId\": ?2}}" +
           "  ]," +
           "  \"filter\": [" +
           "    {\"range\": {\"price\": {\"gte\": ?3, \"lte\": ?4}}}" +
           "  ]" +
           "}}")
    Page<ProductDocument> findByStatusAndCategoryAndBrandAndPriceRange(
        Integer status, Long categoryId, Long brandId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    // ========== 排序和排名方法 ==========
    
    /**
     * 查找热门商品，按销量排序
     */
    List<ProductDocument> findTop10ByIsHotTrueAndStatusOrderBySalesCountDesc(Integer status);

    /**
     * 查找新品，按时间排序
     */
    List<ProductDocument> findTop10ByIsNewTrueAndStatusOrderByCreateTimeDesc(Integer status);

    /**
     * 查找精品推荐，按评分排序
     */
    List<ProductDocument> findTop10ByIsBestTrueAndStatusOrderByScoreDesc(Integer status);

    /**
     * 查找销量排行榜前20的商品
     */
    List<ProductDocument> findTop20ByStatusOrderBySalesCountDesc(Integer status);

    /**
     * 查找评分最高的前20个商品
     */
    List<ProductDocument> findTop20ByStatusOrderByScoreDesc(Integer status);

    /**
     * 查找最新商品前20个
     */
    List<ProductDocument> findTop20ByStatusOrderByCreateTimeDesc(Integer status);

    // ========== 店铺相关方法 ==========
    
    /**
     * 根据店铺ID查找
     */
    Page<ProductDocument> findByStoreIdAndStatus(Long storeId, Integer status, Pageable pageable);

    /**
     * 根据多个店铺ID查找
     */
    Page<ProductDocument> findByStoreIdInAndStatus(List<Long> storeIds, Integer status, Pageable pageable);

    // ========== 管理方法 ==========
    
    /**
     * 根据SPU ID删除
     */
    void deleteBySpuId(Long spuId);

    /**
     * 根据SKU ID删除
     */
    void deleteBySkuId(Long skuId);

    /**
     * 根据SPU ID检查是否存在
     */
    boolean existsBySpuId(Long spuId);

    /**
     * 根据SKU ID检查是否存在
     */
    boolean existsBySkuId(Long skuId);

    /**
     * 按状态统计数量
     */
    long countByStatus(Integer status);

    /**
     * 按分类统计数量
     */
    long countByCategoryIdAndStatus(Long categoryId, Integer status);

    /**
     * 按品牌统计数量
     */
    long countByBrandIdAndStatus(Long brandId, Integer status);

    // ========== 文本搜索方法 ==========
    
    /**
     * 商品名称全文搜索
     */
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"match\": {\"productName\": \"?0\"}}," +
           "    {\"term\": {\"status\": ?1}}" +
           "  ]" +
           "}}")
    Page<ProductDocument> findByProductNameMatchAndStatus(String productName, Integer status, Pageable pageable);

    /**
     * 根据关键词搜索
     */
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"match\": {\"keywords\": \"?0\"}}," +
           "    {\"term\": {\"status\": ?1}}" +
           "  ]" +
           "}}")
    Page<ProductDocument> findByKeywordsMatchAndStatus(String keywords, Integer status, Pageable pageable);

    /**
     * 根据介绍/描述搜索
     */
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"match\": {\"introduction\": \"?0\"}}," +
           "    {\"term\": {\"status\": ?1}}" +
           "  ]" +
           "}}")
    Page<ProductDocument> findByIntroductionMatchAndStatus(String introduction, Integer status, Pageable pageable);
}