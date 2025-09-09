package com.mall.api.search.constant;

/**
 * Search service constants
 */
public interface SearchConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "search-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/search";
    
    /**
     * Index names
     */
    interface Index {
        String PRODUCT = "mall_product";
        String SUGGEST = "mall_suggest";
        String LOG = "mall_search_log";
    }
    
    /**
     * Search type constants
     */
    interface SearchType {
        String PRODUCT = "product";
        String STORE = "store";
        String BRAND = "brand";
    }
    
    /**
     * Sort type constants
     */
    interface SortType {
        String DEFAULT = "default";
        String SALES = "sales";
        String PRICE_ASC = "price_asc";
        String PRICE_DESC = "price_desc";
        String NEW = "new";
        String SCORE = "score";
    }
    
    /**
     * Aggregation constants
     */
    interface Aggregation {
        String CATEGORY = "category";
        String BRAND = "brand";
        String PRICE_RANGE = "price_range";
        String ATTRIBUTES = "attributes";
    }
    
    /**
     * Default values
     */
    interface Default {
        Integer PAGE_SIZE = 20;
        Integer MAX_PAGE_SIZE = 100;
        Integer SUGGEST_SIZE = 10;
        Integer HOT_KEYWORD_SIZE = 20;
        Integer HISTORY_SIZE = 50;
    }
}