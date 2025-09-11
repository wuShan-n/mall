package com.mall.search.repository.impl;

import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.response.AggregationVO;
import com.mall.api.search.dto.response.ProductSearchVO;
import com.mall.api.search.dto.response.SearchResultVO;
import com.mall.search.document.ProductDocument;
import com.mall.search.repository.ProductSearchRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 简化版自定义仓储实现类，使用Spring Data Elasticsearch提供复杂搜索操作
 * 
 * @author mall
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductSearchRepositoryCustomImpl implements ProductSearchRepositoryCustom {

    private final ElasticsearchOperations elasticsearchOperations;
    
    private static final String PRODUCT_INDEX = "mall_product";
    private static final IndexCoordinates INDEX_COORDINATES = IndexCoordinates.of(PRODUCT_INDEX);

    @Override
    public SearchResultVO<ProductSearchVO> advancedSearch(ProductSearchQuery query) {
        try {
            // 构建简单的条件查询
            Query searchQuery = buildSimpleQuery(query);
            
            // 执行搜索
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(searchQuery, ProductDocument.class, INDEX_COORDINATES);
            
            // 转换为结果VO
            return buildSearchResult(searchHits, query);
        } catch (Exception e) {
            log.error("高级搜索错误", e);
            // 错误时返回空结果
            SearchResultVO<ProductSearchVO> emptyResult = new SearchResultVO<>();
            emptyResult.setCurrent(query.getCurrent());
            emptyResult.setSize(query.getSize());
            emptyResult.setTotal(0L);
            emptyResult.setPages(0L);
            emptyResult.setRecords(Collections.emptyList());
            return emptyResult;
        }
    }

    @Override
    public SearchPage<ProductDocument> advancedSearchDocuments(ProductSearchQuery query) {
        try {
            Query searchQuery = buildSimpleQuery(query);
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(searchQuery, ProductDocument.class, INDEX_COORDINATES);
            return (SearchPage<ProductDocument>) searchHits;
        } catch (Exception e) {
            log.error("高级搜索文档错误", e);
            // 目前返回null - 由服务层处理
            return null;
        }
    }

    @Override
    public SearchResultVO<ProductSearchVO> searchWithAggregations(ProductSearchQuery query, List<String> aggregationFields) {
        // 简化实现 - 目前直接调用advancedSearch
        return advancedSearch(query);
    }

    @Override
    public Page<ProductDocument> searchNearby(Double latitude, Double longitude, String distance, Pageable pageable) {
        // 简化实现 - 目前返回空页面
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    @Override
    public Page<ProductDocument> searchByAttributes(Map<String, List<String>> attributes, Pageable pageable) {
        // 简化实现 - 目前返回空页面
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    @Override
    public Page<ProductDocument> searchByAttributesAndConditions(
            Map<String, List<String>> attributes, 
            ProductSearchQuery baseQuery, 
            Pageable pageable) {
        // 简化实现 - 目前返回空页面
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    @Override
    public SearchPage<ProductDocument> fullTextSearch(String searchQuery, Map<String, Float> fieldBoosts, Pageable pageable) {
        try {
            Criteria criteria = new Criteria("productName").contains(searchQuery)
                .or("keywords").contains(searchQuery);
                
            Query query = new CriteriaQuery(criteria).setPageable(pageable);
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class, INDEX_COORDINATES);
            return (SearchPage<ProductDocument>) searchHits;
        } catch (Exception e) {
            log.error("全文搜索错误", e);
            // 目前返回null - 由服务层处理
            return null;
        }
    }

    @Override
    public Page<ProductDocument> searchWithCustomSort(
            ProductSearchQuery query, 
            List<String> sortFields, 
            List<String> sortOrders, 
            Pageable pageable) {
        // 简化实现 - 目前返回空页面
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    @Override
    public Map<String, List<AggregationVO>> getAggregations(ProductSearchQuery query) {
        // 简化实现 - 目前返回空映射
        return Collections.emptyMap();
    }

    @Override
    public List<String> getSuggestions(String partial, int limit) {
        try {
            Criteria criteria = new Criteria("productName").startsWith(partial);
            Query query = new CriteriaQuery(criteria);
            
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class, INDEX_COORDINATES);
            
            return searchHits.stream()
                .map(hit -> hit.getContent().getProductName())
                .distinct()
                .limit(limit)
                .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("获取建议错误", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<ProductDocument> findSimilarProducts(Long productId, int limit) {
        // 简化实现 - 目前返回空列表
        return Collections.emptyList();
    }

    @Override
    public SearchPage<ProductDocument> smartTextSearch(String searchQuery, boolean fuzzy, Pageable pageable) {
        try {
            Criteria criteria = new Criteria("productName").contains(searchQuery);
            Query query = new CriteriaQuery(criteria).setPageable(pageable);
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class, INDEX_COORDINATES);
            return (SearchPage<ProductDocument>) searchHits;
        } catch (Exception e) {
            log.error("智能文本搜索错误", e);
            // 目前返回null - 由服务层处理
            return null;
        }
    }

    @Override
    public Map<String, Object> searchByPriceRangeWithHistogram(
            ProductSearchQuery query, 
            Double interval, 
            Pageable pageable) {
        // 简化实现 - 目前返回空映射
        return Collections.emptyMap();
    }

    @Override
    public Page<ProductDocument> searchInCategoryPath(String categoryPath, ProductSearchQuery query, Pageable pageable) {
        // 简化实现 - 目前返回空页面
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    @Override
    public List<ProductDocument> getRecentOrPopularProducts(String type, Long userId, int limit) {
        try {
            Criteria criteria = new Criteria("status").is(1);
            
            Query query = new CriteriaQuery(criteria);
            SearchHits<ProductDocument> searchHits = elasticsearchOperations.search(query, ProductDocument.class, INDEX_COORDINATES);
            
            return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("获取最近或热门商品错误", e);
            return Collections.emptyList();
        }
    }

    @Override
    public void bulkIndex(List<ProductDocument> documents) {
        try {
            if (!CollectionUtils.isEmpty(documents)) {
                List<IndexQuery> indexQueries = documents.stream()
                    .map(doc -> new IndexQueryBuilder()
                        .withId(doc.getId())
                        .withObject(doc)
                        .build())
                    .collect(Collectors.toList());
                    
                elasticsearchOperations.bulkIndex(indexQueries, INDEX_COORDINATES);
            }
        } catch (Exception e) {
            log.error("批量索引错误", e);
        }
    }

    @Override
    public void bulkDelete(List<String> documentIds) {
        try {
            if (!CollectionUtils.isEmpty(documentIds)) {
                for (String id : documentIds) {
                    elasticsearchOperations.delete(id, INDEX_COORDINATES);
                }
            }
        } catch (Exception e) {
            log.error("批量删除错误", e);
        }
    }

    // ========== 私有辅助方法 ==========

    private Query buildSimpleQuery(ProductSearchQuery queryRequest) {
        Criteria criteria = new Criteria("status").is(1); // 仅激活商品
        
        // 文本搜索
        if (StringUtils.hasText(queryRequest.getKeyword())) {
            Criteria textCriteria = new Criteria("productName").contains(queryRequest.getKeyword())
                .or("keywords").contains(queryRequest.getKeyword());
            criteria = criteria.and(textCriteria);
        }
        
        // 分类过滤
        if (queryRequest.getCategoryId() != null) {
            criteria = criteria.and("categoryId").is(queryRequest.getCategoryId());
        }
        
        // 品牌过滤  
        if (queryRequest.getBrandId() != null) {
            criteria = criteria.and("brandId").is(queryRequest.getBrandId());
        }
        
        // 价格区间
        if (queryRequest.getMinPrice() != null && queryRequest.getMaxPrice() != null) {
            criteria = criteria.and("price").between(queryRequest.getMinPrice(), queryRequest.getMaxPrice());
        } else if (queryRequest.getMinPrice() != null) {
            criteria = criteria.and("price").greaterThanEqual(queryRequest.getMinPrice());
        } else if (queryRequest.getMaxPrice() != null) {
            criteria = criteria.and("price").lessThanEqual(queryRequest.getMaxPrice());
        }
        
        return new CriteriaQuery(criteria).setPageable(buildPageable(queryRequest));
    }

    private Pageable buildPageable(ProductSearchQuery query) {
        return PageRequest.of(
            query.getCurrent().intValue() - 1, 
            query.getSize().intValue()
        );
    }

    private SearchResultVO<ProductSearchVO> buildSearchResult(SearchHits<ProductDocument> searchHits, ProductSearchQuery query) {
        SearchResultVO<ProductSearchVO> result = new SearchResultVO<>();
        
        // 分页信息
        result.setCurrent(query.getCurrent());
        result.setSize(query.getSize());
        result.setTotal(searchHits.getTotalHits());
        result.setPages((long) Math.ceil((double) searchHits.getTotalHits() / query.getSize()));
        result.setTook(0L); // Spring Data doesn't expose took time directly
        
        // 将文档转换为VO
        List<ProductSearchVO> products = searchHits.stream()
            .map(hit -> convertToProductVO(hit, query))
            .collect(Collectors.toList());
        result.setRecords(products);
        
        // 设置其他搜索属性
        result.setKeyword(query.getKeyword());
        result.setMaxScore(searchHits.getMaxScore());
        result.setTrackingId(UUID.randomUUID().toString());
        
        return result;
    }

    private ProductSearchVO convertToProductVO(SearchHit<ProductDocument> hit, ProductSearchQuery query) {
        ProductDocument doc = hit.getContent();
        ProductSearchVO vo = new ProductSearchVO();
        
        // 复制基本属性
        BeanUtils.copyProperties(doc, vo);
        vo.setId(doc.getSkuId()); // Use SKU ID as main ID
        
        // 设置搜索分数 
        Float score = hit.getScore();
        vo.setSearchScore(score != null ? score.doubleValue() : 0.0);
        
        // 处理高亮显示
        Map<String, List<String>> highlights = hit.getHighlightFields();
        if (!highlights.isEmpty() && query.getHighlight() != null && query.getHighlight()) {
            if (highlights.containsKey("productName") && !highlights.get("productName").isEmpty()) {
                vo.setProductName(highlights.get("productName").get(0));
            }
        }
        
        // 转换SKU选项和属性（简化版）
        if (!CollectionUtils.isEmpty(doc.getSkuOptions())) {
            List<ProductSearchVO.SkuOptionVO> skuOptions = doc.getSkuOptions().stream()
                .map(this::convertToSkuOptionVO)
                .collect(Collectors.toList());
            vo.setSkuOptions(skuOptions);
        }
        
        // 将属性转换为映射
        if (!CollectionUtils.isEmpty(doc.getAttributes())) {
            Map<String, String> attributeMap = doc.getAttributes().stream()
                .collect(Collectors.toMap(
                    ProductDocument.ProductAttribute::getAttrName,
                    ProductDocument.ProductAttribute::getAttrValue,
                    (existing, replacement) -> existing
                ));
            vo.setAttributes(attributeMap);
        }
        
        return vo;
    }

    private ProductSearchVO.SkuOptionVO convertToSkuOptionVO(ProductDocument.SkuOption skuOption) {
        ProductSearchVO.SkuOptionVO vo = new ProductSearchVO.SkuOptionVO();
        BeanUtils.copyProperties(skuOption, vo);
        return vo;
    }
}