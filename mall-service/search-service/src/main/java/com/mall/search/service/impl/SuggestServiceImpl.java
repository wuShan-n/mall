package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.mall.api.search.dto.request.SuggestRequest;
import com.mall.api.search.dto.response.HotKeywordVO;
import com.mall.api.search.dto.response.SuggestVO;
import com.mall.api.search.enums.SearchEnums;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import com.mall.search.document.ProductDocument;
import com.mall.search.entity.HotKeyword;
import com.mall.search.mapper.HotKeywordMapper;
import com.mall.search.service.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Suggest service implementation
 * 
 * @author mall
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SuggestServiceImpl implements SuggestService {

    private final ElasticsearchClient elasticsearchClient;
    private final RedisTemplate<String, Object> redisTemplate;
    private final HotKeywordMapper hotKeywordMapper;

    private static final String HOT_KEYWORD_KEY = "search:hot_keywords";
    private static final String HOT_KEYWORD_CATEGORY_KEY = "search:hot_keywords:category:";
    private static final String SUGGEST_CACHE_KEY = "search:suggest:";

    @Override
    public Result<List<SuggestVO>> getSuggestions(SuggestRequest request) {
        try {
            // Check cache first
            String cacheKey = SUGGEST_CACHE_KEY + request.getKeyword() + ":" + request.getSuggestType();
            List<SuggestVO> cached = getCachedSuggestions(cacheKey);
            if (cached != null && !cached.isEmpty()) {
                return Result.success(cached);
            }
            
            // Get suggestions from Elasticsearch
            List<SuggestVO> suggestions = fetchSuggestionsFromES(request);
            
            // Cache the results
            if (!suggestions.isEmpty()) {
                cacheSuggestions(cacheKey, suggestions);
            }
            
            return Result.success(suggestions);
        } catch (Exception e) {
            log.error("Get suggestions failed", e);
            throw new BusinessException("Failed to get suggestions: " + e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "hotKeywords", key = "#size", unless = "#result == null || #result.data == null")
    public Result<List<HotKeywordVO>> getHotKeywords(Integer size) {
        try {
            // Get from Redis sorted set
            Set<ZSetOperations.TypedTuple<Object>> hotKeywords = redisTemplate.opsForZSet()
                .reverseRangeWithScores(HOT_KEYWORD_KEY, 0, size - 1);
            
            if (hotKeywords == null || hotKeywords.isEmpty()) {
                // Load from database if Redis is empty
                return loadHotKeywordsFromDB(null, size);
            }
            
            // Convert to VOs
            List<HotKeywordVO> result = convertToHotKeywordVOs(hotKeywords);
            return Result.success(result);
        } catch (Exception e) {
            log.error("Get hot keywords failed", e);
            return Result.success(new ArrayList<>());
        }
    }

    @Override
    @Cacheable(value = "hotKeywordsByCategory", key = "#categoryId + ':' + #size", unless = "#result == null || #result.data == null")
    public Result<List<HotKeywordVO>> getHotKeywordsByCategory(Long categoryId, Integer size) {
        try {
            String key = HOT_KEYWORD_CATEGORY_KEY + categoryId;
            
            // Get from Redis sorted set
            Set<ZSetOperations.TypedTuple<Object>> hotKeywords = redisTemplate.opsForZSet()
                .reverseRangeWithScores(key, 0, size - 1);
            
            if (hotKeywords == null || hotKeywords.isEmpty()) {
                // Load from database if Redis is empty
                return loadHotKeywordsFromDB(categoryId, size);
            }
            
            // Convert to VOs
            List<HotKeywordVO> result = convertToHotKeywordVOs(hotKeywords);
            return Result.success(result);
        } catch (Exception e) {
            log.error("Get hot keywords by category failed", e);
            return Result.success(new ArrayList<>());
        }
    }

    @Override
    public void updateHotKeyword(String keyword, Long categoryId) {
        try {
            // Update global hot keywords
            redisTemplate.opsForZSet().incrementScore(HOT_KEYWORD_KEY, keyword, 1);
            
            // Update category-specific hot keywords
            if (categoryId != null) {
                String key = HOT_KEYWORD_CATEGORY_KEY + categoryId;
                redisTemplate.opsForZSet().incrementScore(key, keyword, 1);
            }
            
            // Set expiration (7 days)
            redisTemplate.expire(HOT_KEYWORD_KEY, 7, TimeUnit.DAYS);
            if (categoryId != null) {
                redisTemplate.expire(HOT_KEYWORD_CATEGORY_KEY + categoryId, 7, TimeUnit.DAYS);
            }
        } catch (Exception e) {
            log.error("Update hot keyword failed: keyword={}, categoryId={}", keyword, categoryId, e);
        }
    }

    private List<SuggestVO> fetchSuggestionsFromES(SuggestRequest request) throws IOException {
        // Build search request with prefix query
        SearchRequest searchRequest = SearchRequest.of(s -> s
            .index("mall_product")
            .query(q -> q.bool(b -> {
                // Match phrase prefix for better suggestions
                b.should(sh -> sh.matchPhrasePrefix(mpp -> mpp
                    .field("productName")
                    .query(request.getKeyword())
                    .slop(2)
                ));
                
                b.should(sh -> sh.matchPhrasePrefix(mpp -> mpp
                    .field("keywords")
                    .query(request.getKeyword())
                    .slop(1)
                ));
                
                b.should(sh -> sh.matchPhrasePrefix(mpp -> mpp
                    .field("brandName")
                    .query(request.getKeyword())
                ));
                
                // Add category filter if specified
                if (request.getCategoryId() != null) {
                    b.filter(f -> f.term(t -> t
                        .field("categoryId")
                        .value(request.getCategoryId())
                    ));
                }
                
                return b;
            }))
            .size(request.getSize())
            .source(src -> src.filter(f -> f
                .includes(Arrays.asList("productName", "brandName", "categoryName", "mainImage", "price"))
            ))
            .highlight(h -> h
                .fields("productName", hf -> hf
                    .preTags("<em>")
                    .postTags("</em>")
                )
            )
        );
        
        SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);
        
        // Convert to SuggestVOs
        List<SuggestVO> suggestions = new ArrayList<>();
        for (var hit : response.hits().hits()) {
            SuggestVO vo = new SuggestVO();
            ProductDocument doc = hit.source();
            
            // Set basic info
            if (hit.highlight() != null && hit.highlight().containsKey("productName")) {
                vo.setHighlightedText(hit.highlight().get("productName").get(0));
                vo.setText(doc.getProductName());
            } else {
                vo.setText(doc.getProductName());
                vo.setHighlightedText(doc.getProductName());
            }
            
            vo.setType(request.getSuggestType());
            vo.setRelatedId(doc.getSpuId());
            vo.setCategoryName(doc.getCategoryName());
            vo.setCategoryId(doc.getCategoryId());
            vo.setImageUrl(doc.getMainImage());
            
            // Set price range
            if (doc.getPrice() != null) {
                vo.setPriceRange("$" + doc.getPrice());
            }
            
            vo.setScore(hit.score() != null ? hit.score().intValue() : 0);
            vo.setFrequency(doc.getSalesCount());
            
            suggestions.add(vo);
        }
        
        return suggestions;
    }

    private List<SuggestVO> getCachedSuggestions(String cacheKey) {
        try {
            Object cached = redisTemplate.opsForValue().get(cacheKey);
            if (cached instanceof List) {
                return (List<SuggestVO>) cached;
            }
        } catch (Exception e) {
            log.debug("Failed to get cached suggestions", e);
        }
        return null;
    }

    private void cacheSuggestions(String cacheKey, List<SuggestVO> suggestions) {
        try {
            redisTemplate.opsForValue().set(cacheKey, suggestions, 1, TimeUnit.HOURS);
        } catch (Exception e) {
            log.debug("Failed to cache suggestions", e);
        }
    }

    private Result<List<HotKeywordVO>> loadHotKeywordsFromDB(Long categoryId, Integer size) {
        try {
            List<HotKeyword> keywords;
            if (categoryId == null) {
                keywords = hotKeywordMapper.selectTopKeywords(size);
            } else {
                keywords = hotKeywordMapper.selectTopKeywordsByCategory(categoryId, size);
            }
            
            List<HotKeywordVO> vos = keywords.stream()
                .map(this::convertToHotKeywordVO)
                .collect(Collectors.toList());
            
            // Cache in Redis
            cacheHotKeywords(vos, categoryId);
            
            return Result.success(vos);
        } catch (Exception e) {
            log.error("Load hot keywords from DB failed", e);
            return Result.success(new ArrayList<>());
        }
    }

    private void cacheHotKeywords(List<HotKeywordVO> keywords, Long categoryId) {
        try {
            String key = categoryId == null ? HOT_KEYWORD_KEY : HOT_KEYWORD_CATEGORY_KEY + categoryId;
            
            // Clear existing data
            redisTemplate.delete(key);
            
            // Add to sorted set
            for (int i = 0; i < keywords.size(); i++) {
                HotKeywordVO keyword = keywords.get(i);
                redisTemplate.opsForZSet().add(key, keyword.getKeyword(), keyword.getSearchCount());
            }
            
            // Set expiration
            redisTemplate.expire(key, 1, TimeUnit.DAYS);
        } catch (Exception e) {
            log.error("Cache hot keywords failed", e);
        }
    }

    private List<HotKeywordVO> convertToHotKeywordVOs(Set<ZSetOperations.TypedTuple<Object>> tuples) {
        List<HotKeywordVO> vos = new ArrayList<>();
        int rank = 1;
        
        for (ZSetOperations.TypedTuple<Object> tuple : tuples) {
            HotKeywordVO vo = new HotKeywordVO();
            vo.setKeyword(String.valueOf(tuple.getValue()));
            vo.setSearchCount(tuple.getScore() != null ? tuple.getScore().longValue() : 0L);
            vo.setRank(rank++);
            
            // Determine trend based on score
            if (tuple.getScore() != null && tuple.getScore() > 1000) {
                vo.setIsHot(true);
                vo.setTrend(SearchEnums.TrendType.UP.getCode());
                vo.setIcon("🔥");
            } else {
                vo.setIsHot(false);
                vo.setTrend(SearchEnums.TrendType.STABLE.getCode());
            }
            
            vos.add(vo);
        }
        
        return vos;
    }

    private HotKeywordVO convertToHotKeywordVO(HotKeyword entity) {
        HotKeywordVO vo = new HotKeywordVO();
        vo.setKeyword(entity.getKeyword());
        vo.setSearchCount(entity.getSearchCount());
        vo.setCategory(entity.getCategoryName());
        vo.setIsHot(entity.getIsHot());
        vo.setIsNew(entity.getIsNew());
        vo.setRank(entity.getRank());
        vo.setPreviousRank(entity.getPreviousRank());
        
        // Calculate trend
        if (entity.getPreviousRank() != null) {
            if (entity.getRank() < entity.getPreviousRank()) {
                vo.setTrend(SearchEnums.TrendType.UP.getCode());
            } else if (entity.getRank() > entity.getPreviousRank()) {
                vo.setTrend(SearchEnums.TrendType.DOWN.getCode());
            } else {
                vo.setTrend(SearchEnums.TrendType.STABLE.getCode());
            }
        }
        
        // Set icon based on status
        if (entity.getIsHot()) {
            vo.setIcon("🔥");
            vo.setColor("#FF0000");
        } else if (entity.getIsNew()) {
            vo.setIcon("✨");
            vo.setColor("#00FF00");
        }
        
        return vo;
    }
}