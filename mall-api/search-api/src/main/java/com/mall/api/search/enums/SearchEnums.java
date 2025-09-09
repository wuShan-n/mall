package com.mall.api.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Search related enumerations
 */
public class SearchEnums {
    
    /**
     * Search type enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum SearchType {
        PRODUCT("product", "Product Search"),
        STORE("store", "Store Search"),
        BRAND("brand", "Brand Search"),
        CATEGORY("category", "Category Search");
        
        private final String code;
        private final String desc;
        
        public static SearchType of(String code) {
            for (SearchType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return PRODUCT;
        }
    }
    
    /**
     * Sort type enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum SortType {
        DEFAULT("default", "Default", "score", "desc"),
        SALES("sales", "Sales", "salesCount", "desc"),
        PRICE_ASC("price_asc", "Price Low to High", "price", "asc"),
        PRICE_DESC("price_desc", "Price High to Low", "price", "desc"),
        NEW("new", "New Arrivals", "createTime", "desc"),
        SCORE("score", "Rating", "score", "desc"),
        COMMENT("comment", "Reviews", "commentCount", "desc");
        
        private final String code;
        private final String desc;
        private final String field;
        private final String order;
        
        public static SortType of(String code) {
            for (SortType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return DEFAULT;
        }
    }
    
    /**
     * Stock status enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum StockStatus {
        IN_STOCK("IN_STOCK", "In Stock"),
        LOW_STOCK("LOW_STOCK", "Low Stock"),
        OUT_OF_STOCK("OUT_OF_STOCK", "Out of Stock"),
        PRE_ORDER("PRE_ORDER", "Pre-order");
        
        private final String code;
        private final String desc;
    }
    
    /**
     * Search source enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum SearchSource {
        WEB("WEB", "Web"),
        APP("APP", "Mobile App"),
        MINI_PROGRAM("MINI_PROGRAM", "Mini Program"),
        API("API", "API");
        
        private final String code;
        private final String desc;
    }
    
    /**
     * Trend type enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum TrendType {
        UP("UP", "Trending Up", "↑"),
        DOWN("DOWN", "Trending Down", "↓"),
        STABLE("STABLE", "Stable", "→"),
        NEW("NEW", "New", "NEW"),
        HOT("HOT", "Hot", "🔥");
        
        private final String code;
        private final String desc;
        private final String icon;
    }
    
    /**
     * Index operation type enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum IndexOperationType {
        CREATE("CREATE", "Create Document"),
        UPDATE("UPDATE", "Update Document"),
        DELETE("DELETE", "Delete Document"),
        BULK_CREATE("BULK_CREATE", "Bulk Create"),
        BULK_UPDATE("BULK_UPDATE", "Bulk Update"),
        BULK_DELETE("BULK_DELETE", "Bulk Delete"),
        REINDEX("REINDEX", "Reindex");
        
        private final String code;
        private final String desc;
    }
    
    /**
     * Document type enumeration
     */
    @Getter
    @AllArgsConstructor
    public enum DocumentType {
        PRODUCT("PRODUCT", "Product Document"),
        BRAND("BRAND", "Brand Document"),
        CATEGORY("CATEGORY", "Category Document"),
        STORE("STORE", "Store Document");
        
        private final String code;
        private final String desc;
    }
}