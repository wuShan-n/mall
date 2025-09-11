package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.*;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.RangeAggregate;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsAggregate;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Highlight;
import co.elastic.clients.elasticsearch.core.search.HighlightField;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mall.api.search.dto.request.ProductSearchQuery;
import com.mall.api.search.dto.request.SearchHistoryRequest;
import com.mall.api.search.dto.response.*;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import com.mall.search.document.ProductDocument;
import com.mall.search.entity.SearchHistory;
import com.mall.search.mapper.SearchHistoryMapper;
import com.mall.search.mapper.SearchStatisticsMapper;
import com.mall.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Search service implementation
 * 
 * @author mall
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    
    private final ElasticsearchClient elasticsearchClient;
    private final SearchHistoryMapper searchHistoryMapper;
    private final SearchStatisticsMapper searchStatisticsMapper;
    
    private static final String PRODUCT_INDEX = "mall_product";
    private static final String HIGHLIGHT_TAG_PRE = "<em>";
    private static final String HIGHLIGHT_TAG_POST = "</em>";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public Result<SearchResultVO<ProductSearchVO>> searchProducts(ProductSearchQuery request) {
        try {
            log.info("Searching products with query: {}", request);
            
            // Build search request
            SearchRequest searchRequest = buildSearchRequest(request);
            
            // Execute search
            SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);
            
            // Build result
            SearchResultVO<ProductSearchVO> result = buildSearchResult(response, request);
            
            // Save search history if user is logged in
            // TODO: Add userId to ProductSearchQuery if needed
            // if (request.getUserId() != null) {
            //     saveSearchHistoryAsync(request.getUserId(), request.getKeyword(), 
            //         request.getSearchType(), (int) response.hits().total().value());
            // }
            
            log.info("Search completed, found {} products", result.getTotal());
            return Result.success(result);
            
        } catch (Exception e) {
            log.error("Error searching products", e);
            throw new BusinessException("Search failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> saveSearchHistory(Long userId, String keyword, String searchType) {
        try {
            SearchHistory history = new SearchHistory();
            history.setUserId(userId);
            history.setKeyword(keyword);
            history.setSearchType(searchType);
            history.setResultCount(0); // Will be updated by actual search
            history.setClickCount(0);
            history.setSearchSource("web");
            history.setDeviceType("desktop");
            history.setTrackingId(UUID.randomUUID().toString());
            
            searchHistoryMapper.insert(history);
            return Result.success();
        } catch (Exception e) {
            log.error("Error saving search history", e);
            return Result.failed("Failed to save search history");
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
            log.error("Error getting user search history", e);
            return Result.failed("Failed to get search history");
        }
    }

    @Override
    public Result<Void> clearUserSearchHistory(Long userId) {
        try {
            searchHistoryMapper.clearUserHistory(userId);
            return Result.success();
        } catch (Exception e) {
            log.error("Error clearing user search history", e);
            return Result.failed("Failed to clear search history");
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
            log.error("Error deleting search history", e);
            return Result.failed("Failed to delete search history");
        }
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatistics() {
        try {
            LocalDate today = LocalDate.now();
            return getSearchStatisticsByDateRange(today.format(DATE_FORMATTER), today.format(DATE_FORMATTER));
        } catch (Exception e) {
            log.error("Error getting search statistics", e);
            return Result.failed("Failed to get search statistics");
        }
    }

    @Override
    public Result<SearchStatisticsVO> getSearchStatisticsByDateRange(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate, DATE_FORMATTER);
            LocalDate end = LocalDate.parse(endDate, DATE_FORMATTER);
            
            SearchStatisticsVO statistics = new SearchStatisticsVO();
            
            // Calculate total statistics for the date range
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
            
            // Set the statistics using the correct field names
            statistics.setTotalSearchesToday(totalSearches);
            statistics.setUniqueUsersToday(uniqueUsers);
            statistics.setNoResultSearches(noResultSearches);
            
            // Get additional distribution data for the end date
            statistics.setHourlyTrend(searchStatisticsMapper.getHourlyDistribution(end));
            statistics.setDeviceDistribution(searchStatisticsMapper.getDeviceDistribution(end));
            statistics.setSourceDistribution(searchStatisticsMapper.getSourceDistribution(end));
            
            return Result.success(statistics);
        } catch (Exception e) {
            log.error("Error getting search statistics by date range", e);
            return Result.failed("Failed to get search statistics");
        }
    }

    @Override
    public Result<Void> recordSearchClick(String trackingId, Long productId, Integer position) {
        try {
            // Update click count for the search history
            LambdaUpdateWrapper<SearchHistory> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(SearchHistory::getTrackingId, trackingId)
                         .setSql("click_count = click_count + 1");
            
            searchHistoryMapper.update(null, updateWrapper);
            
            // TODO: Record detailed click analytics if needed
            log.info("Recorded search click - trackingId: {}, productId: {}, position: {}", 
                trackingId, productId, position);
                
            return Result.success();
        } catch (Exception e) {
            log.error("Error recording search click", e);
            return Result.failed("Failed to record search click");
        }
    }

    @Override
    public Result<Void> recordSearchConversion(String trackingId, Long orderId) {
        try {
            // TODO: Record search conversion analytics
            log.info("Recorded search conversion - trackingId: {}, orderId: {}", trackingId, orderId);
            return Result.success();
        } catch (Exception e) {
            log.error("Error recording search conversion", e);
            return Result.failed("Failed to record search conversion");
        }
    }
    
    /**
     * Build Elasticsearch search request
     */
    private SearchRequest buildSearchRequest(ProductSearchQuery request) throws IOException {
        SearchRequest.Builder searchBuilder = new SearchRequest.Builder()
            .index(PRODUCT_INDEX)
            .from((int) ((request.getCurrent() - 1) * request.getSize()))
            .size(request.getSize().intValue());
        
        // Build query
        BoolQuery.Builder boolQuery = new BoolQuery.Builder();
        
        // Main search query
        if (StringUtils.hasText(request.getKeyword())) {
            MultiMatchQuery multiMatchQuery = new MultiMatchQuery.Builder()
                .query(request.getKeyword())
                .fields(Arrays.asList("productName^3", "keywords^2", "brandName", "categoryName", "introduction"))
                .type(TextQueryType.BestFields)
                .build();
            boolQuery.must(Query.of(q -> q.multiMatch(multiMatchQuery)));
        }
        
        // Filter conditions
        addFilterConditions(boolQuery, request);
        
        // Set query
        searchBuilder.query(Query.of(q -> q.bool(boolQuery.build())));
        
        // Add sorting
        addSorting(searchBuilder, request);
        
        // Add highlighting
        if (request.getHighlight() != null && request.getHighlight()) {
            addHighlighting(searchBuilder);
        }
        
        // Add aggregations
        if (request.getIncludeAggregations() != null && request.getIncludeAggregations()) {
            addAggregations(searchBuilder);
        }
        
        return searchBuilder.build();
    }
    
    /**
     * Build search result from Elasticsearch response
     */
    private SearchResultVO<ProductSearchVO> buildSearchResult(SearchResponse<ProductDocument> response, ProductSearchQuery request) {
        SearchResultVO<ProductSearchVO> result = new SearchResultVO<>();
        
        // Set pagination info
        result.setCurrent(request.getCurrent());
        result.setSize(request.getSize());
        result.setTotal(response.hits().total().value());
        result.setPages((long) Math.ceil((double) result.getTotal() / request.getSize()));
        result.setTook(response.took());
        
        // Convert hits to product VOs
        List<ProductSearchVO> products = response.hits().hits().stream()
            .map(hit -> convertToProductVO(hit, request))
            .collect(Collectors.toList());
        result.setRecords(products);
        
        // Set aggregations if included
        if (request.getIncludeAggregations() != null && request.getIncludeAggregations() && response.aggregations() != null) {
            result.setAggregations(buildAggregationMap(response.aggregations()));
        }
        
        // Set other search result properties
        result.setKeyword(request.getKeyword());
        result.setMaxScore(response.maxScore() != null ? response.maxScore().floatValue() : null);
        result.setTrackingId(UUID.randomUUID().toString());
        
        return result;
    }
    
    /**
     * Convert Elasticsearch hit to ProductSearchVO
     */
    private ProductSearchVO convertToProductVO(Hit<ProductDocument> hit, ProductSearchQuery request) {
        ProductDocument doc = hit.source();
        ProductSearchVO vo = new ProductSearchVO();
        
        // Copy basic properties
        BeanUtils.copyProperties(doc, vo);
        vo.setId(doc.getSkuId()); // Use SKU ID as main ID
        
        // Set search score
        vo.setSearchScore(hit.score());
        
        // Handle highlighting
        if (hit.highlight() != null && request.getHighlight() != null && request.getHighlight()) {
            Map<String, List<String>> highlights = hit.highlight();
            if (highlights.containsKey("productName") && !highlights.get("productName").isEmpty()) {
                vo.setProductName(highlights.get("productName").get(0));
            }
        }
        
        // Calculate distance if location search
        if (request.getLatitude() != null && request.getLongitude() != null && doc.getLocation() != null) {
            double distance = calculateDistance(
                request.getLatitude(), request.getLongitude(),
                doc.getLocation().getLat(), doc.getLocation().getLon()
            );
            vo.setDistance(distance);
        }
        
        // Convert SKU options
        if (!CollectionUtils.isEmpty(doc.getSkuOptions())) {
            List<ProductSearchVO.SkuOptionVO> skuOptions = doc.getSkuOptions().stream()
                .map(this::convertToSkuOptionVO)
                .collect(Collectors.toList());
            vo.setSkuOptions(skuOptions);
        }
        
        // Convert attributes to map
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
    
    /**
     * Convert SKU option to VO
     */
    private ProductSearchVO.SkuOptionVO convertToSkuOptionVO(ProductDocument.SkuOption skuOption) {
        ProductSearchVO.SkuOptionVO vo = new ProductSearchVO.SkuOptionVO();
        BeanUtils.copyProperties(skuOption, vo);
        return vo;
    }
    
    /**
     * Build aggregation map from Elasticsearch aggregations
     */
    private Map<String, List<AggregationVO>> buildAggregationMap(Map<String, Aggregate> aggregations) {
        Map<String, List<AggregationVO>> result = new HashMap<>();
        
        for (Map.Entry<String, Aggregate> entry : aggregations.entrySet()) {
            String name = entry.getKey();
            Aggregate aggregate = entry.getValue();
            List<AggregationVO> aggList = new ArrayList<>();
            
            if (aggregate.isSterms()) {
                // Terms aggregation (brand, category, etc.)
                StringTermsAggregate termsAgg = aggregate.sterms();
                aggList = termsAgg.buckets().array().stream()
                    .map(bucket -> {
                        AggregationVO aggVO = new AggregationVO();
                        aggVO.setKey(bucket.key().stringValue());
                        aggVO.setValue(bucket.key().stringValue());
                        aggVO.setDocCount(bucket.docCount());
                        aggVO.setDisplayName(bucket.key().stringValue());
                        return aggVO;
                    })
                    .collect(Collectors.toList());
            } else if (aggregate.isRange()) {
                // Range aggregation (price ranges, score ranges)
                RangeAggregate rangeAgg = aggregate.range();
                aggList = rangeAgg.buckets().array().stream()
                    .map(bucket -> {
                        AggregationVO aggVO = new AggregationVO();
                        aggVO.setKey(bucket.key() != null ? bucket.key() : "unknown");
                        aggVO.setValue(bucket.key() != null ? bucket.key() : "unknown");
                        aggVO.setDocCount(bucket.docCount());
                        
                        // Create display name for range
                        StringBuilder displayName = new StringBuilder();
                        if (bucket.fromAsString() != null) {
                            displayName.append(bucket.fromAsString());
                        }
                        displayName.append(" - ");
                        if (bucket.toAsString() != null) {
                            displayName.append(bucket.toAsString());
                        }
                        aggVO.setDisplayName(displayName.toString());
                        return aggVO;
                    })
                    .collect(Collectors.toList());
            }
            
            result.put(name, aggList);
        }
        
        return result;
    }
    
    /**
     * Convert SearchHistory to SearchHistoryVO
     */
    private SearchHistoryVO convertToHistoryVO(SearchHistory history) {
        SearchHistoryVO vo = new SearchHistoryVO();
        BeanUtils.copyProperties(history, vo);
        return vo;
    }
    
    /**
     * Save search history asynchronously
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
            log.warn("Failed to save search history asynchronously", e);
        }
    }
    
    /**
     * Add filter conditions to bool query
     */
    private void addFilterConditions(BoolQuery.Builder boolQuery, ProductSearchQuery request) {
        // Status filter (active products only)
        boolQuery.filter(Query.of(q -> q.term(t -> t.field("status").value(1))));
        
        // Category filter
        if (request.getCategoryId() != null) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("categoryId").value(request.getCategoryId()))));
        }
        
        // Brand filter
        if (request.getBrandId() != null) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("brandId").value(request.getBrandId()))));
        }
        
        // Multiple brand filter
        if (!CollectionUtils.isEmpty(request.getBrandIds())) {
            List<FieldValue> brandValues = request.getBrandIds().stream()
                .map(FieldValue::of)
                .collect(Collectors.toList());
            boolQuery.filter(Query.of(q -> q.terms(t -> t.field("brandId").terms(tv -> tv.value(brandValues)))));
        }
        
        // Price range filter
        if (request.getMinPrice() != null || request.getMaxPrice() != null) {
            RangeQuery.Builder rangeBuilder = new RangeQuery.Builder().field("price");
            if (request.getMinPrice() != null) {
                rangeBuilder.gte(JsonData.of(request.getMinPrice()));
            }
            if (request.getMaxPrice() != null) {
                rangeBuilder.lte(JsonData.of(request.getMaxPrice()));
            }
            boolQuery.filter(Query.of(q -> q.range(rangeBuilder.build())));
        }
        
        // Boolean filters
        addBooleanFilters(boolQuery, request);
        
        // Attribute filters
        addAttributeFilters(boolQuery, request);
        
        // Location filter
        addLocationFilter(boolQuery, request);
    }
    
    /**
     * Add boolean filters
     */
    private void addBooleanFilters(BoolQuery.Builder boolQuery, ProductSearchQuery request) {
        if (request.getInStock() != null && request.getInStock()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("stockStatus").value("IN_STOCK"))));
        }
        
        if (request.getHasDiscount() != null && request.getHasDiscount()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("hasDiscount").value(true))));
        }
        
        if (request.getIsNew() != null && request.getIsNew()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("isNew").value(true))));
        }
        
        if (request.getIsHot() != null && request.getIsHot()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("isHot").value(true))));
        }
        
        if (request.getIsBest() != null && request.getIsBest()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t.field("isBest").value(true))));
        }
        
        if (request.getMinScore() != null) {
            boolQuery.filter(Query.of(q -> q.range(r -> r.field("score").gte(JsonData.of(request.getMinScore())))));
        }
    }
    
    /**
     * Add attribute filters
     */
    private void addAttributeFilters(BoolQuery.Builder boolQuery, ProductSearchQuery request) {
        if (!CollectionUtils.isEmpty(request.getAttributes())) {
            for (Map.Entry<String, List<String>> entry : request.getAttributes().entrySet()) {
                String attrName = entry.getKey();
                List<String> attrValues = entry.getValue();
                
                if (!CollectionUtils.isEmpty(attrValues)) {
                    BoolQuery.Builder nestedBool = new BoolQuery.Builder();
                    nestedBool.must(Query.of(q -> q.term(t -> t.field("attributes.attrName").value(attrName))));
                    
                    List<FieldValue> values = attrValues.stream()
                        .map(FieldValue::of)
                        .collect(Collectors.toList());
                    nestedBool.must(Query.of(q -> q.terms(t -> t.field("attributes.attrValue").terms(tv -> tv.value(values)))));
                    
                    NestedQuery nestedQuery = new NestedQuery.Builder()
                        .path("attributes")
                        .query(Query.of(q -> q.bool(nestedBool.build())))
                        .build();
                        
                    boolQuery.filter(Query.of(q -> q.nested(nestedQuery)));
                }
            }
        }
    }
    
    /**
     * Add location filter
     */
    private void addLocationFilter(BoolQuery.Builder boolQuery, ProductSearchQuery request) {
        if (request.getLatitude() != null && request.getLongitude() != null && request.getDistance() != null) {
            GeoDistanceQuery geoQuery = new GeoDistanceQuery.Builder()
                .field("location")
                .location(GeoLocation.of(gl -> gl.latlon(ll -> ll.lat(request.getLatitude()).lon(request.getLongitude()))))
                .distance(request.getDistance() + "km")
                .build();
                
            boolQuery.filter(Query.of(q -> q.geoDistance(geoQuery)));
        }
    }
    
    /**
     * Add sorting to search request
     */
    private void addSorting(SearchRequest.Builder searchBuilder, ProductSearchQuery request) {
        List<SortOptions> sorts = new ArrayList<>();
        
        String sortType = request.getSortType() != null ? request.getSortType() : "default";
        
        switch (sortType) {
            case "sales":
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("salesCount").order(SortOrder.Desc))));
                break;
            case "price_asc":
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("price").order(SortOrder.Asc))));
                break;
            case "price_desc":
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("price").order(SortOrder.Desc))));
                break;
            case "new":
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("createTime").order(SortOrder.Desc))));
                break;
            case "score":
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("score").order(SortOrder.Desc))));
                break;
            case "default":
            default:
                // Default sorting: relevance score, then sales, then create time
                sorts.add(SortOptions.of(s -> s.score(sc -> sc.order(SortOrder.Desc))));
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("salesCount").order(SortOrder.Desc))));
                sorts.add(SortOptions.of(s -> s.field(f -> f.field("createTime").order(SortOrder.Desc))));
                break;
        }
        
        // Add location-based sorting if location is provided
        if (request.getLatitude() != null && request.getLongitude() != null) {
            GeoDistanceSort geoSort = new GeoDistanceSort.Builder()
                .field("location")
                .location(Collections.singletonList(
                        GeoLocation.of(gl -> gl.latlon(ll -> ll.lat(request.getLatitude()).lon(request.getLongitude())))
                ))
                .order(SortOrder.Asc)
                .unit(DistanceUnit.Kilometers)
                .build();
            sorts.add(SortOptions.of(s -> s.geoDistance(geoSort)));
        }
        
        searchBuilder.sort(sorts);
    }
    
    /**
     * Add highlighting to search request
     */
    private void addHighlighting(SearchRequest.Builder searchBuilder) {
        Map<String, HighlightField> highlightFields = new HashMap<>();
        
        highlightFields.put("productName", HighlightField.of(h -> h
            .preTags(List.of(HIGHLIGHT_TAG_PRE))
            .postTags(List.of(HIGHLIGHT_TAG_POST))
            .numberOfFragments(0)));
            
        highlightFields.put("keywords", HighlightField.of(h -> h
            .preTags(List.of(HIGHLIGHT_TAG_PRE))
            .postTags(List.of(HIGHLIGHT_TAG_POST))
            .numberOfFragments(0)));
        
        Highlight highlight = new Highlight.Builder()
            .fields(highlightFields)
            .build();
            
        searchBuilder.highlight(highlight);
    }
    
    /**
     * Add aggregations to search request
     */
    private void addAggregations(SearchRequest.Builder searchBuilder) {
        Map<String, Aggregation> aggregations = new HashMap<>();
        
        // Brand aggregation
        aggregations.put("brands", Aggregation.of(a -> a
            .terms(t -> t.field("brandId").size(20))));
        
        // Category aggregation
        aggregations.put("categories", Aggregation.of(a -> a
            .terms(t -> t.field("categoryId").size(20))));
        
        // Price range aggregation - temporarily simplified
        // TODO: Fix AggregationRange implementation for proper price ranges
        
        // Score range aggregation - temporarily simplified  
        // TODO: Fix AggregationRange implementation for proper score ranges
        
        searchBuilder.aggregations(aggregations);
    }

    /**
     * Calculate distance between two points (Haversine formula)
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth's radius in kilometers
        
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}