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
 * Product search repository
 * 
 * @author mall
 */
@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, String> {

    /**
     * Find by product name with highlighting
     */
    @Highlight(fields = {
        @HighlightField(name = "productName"),
        @HighlightField(name = "keywords")
    })
    SearchPage<ProductDocument> findByProductNameOrKeywords(String productName, String keywords, Pageable pageable);

    /**
     * Find by category ID
     */
    Page<ProductDocument> findByCategoryId(Long categoryId, Pageable pageable);

    /**
     * Find by brand ID
     */
    Page<ProductDocument> findByBrandId(Long brandId, Pageable pageable);

    /**
     * Find by price range
     */
    Page<ProductDocument> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    /**
     * Find by multiple conditions
     */
    @Query("{\"bool\": {" +
           "  \"must\": [" +
           "    {\"term\": {\"status\": ?0}}," +
           "    {\"term\": {\"categoryId\": ?1}}" +
           "  ]," +
           "  \"filter\": [" +
           "    {\"range\": {\"price\": {\"gte\": ?2, \"lte\": ?3}}}" +
           "  ]" +
           "}}")
    Page<ProductDocument> findByStatusAndCategoryAndPriceRange(
        Integer status, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    /**
     * Find hot products
     */
    List<ProductDocument> findTop10ByIsHotTrueOrderBySalesCountDesc();

    /**
     * Find new products
     */
    List<ProductDocument> findTop10ByIsNewTrueOrderByCreateTimeDesc();

    /**
     * Find best products
     */
    List<ProductDocument> findTop10ByIsBestTrueOrderByScoreDesc();

    /**
     * Delete by SPU ID
     */
    void deleteBySpuId(Long spuId);

    /**
     * Delete by SKU ID
     */
    void deleteBySkuId(Long skuId);

    /**
     * Check if exists by SPU ID
     */
    boolean existsBySpuId(Long spuId);

    /**
     * Check if exists by SKU ID
     */
    boolean existsBySkuId(Long skuId);
}