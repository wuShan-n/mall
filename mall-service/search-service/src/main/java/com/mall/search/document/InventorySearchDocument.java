package com.mall.search.document;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * =====================================================
 * 2. 库存搜索索引 - B端库存管理
 * =====================================================
 */
@Data
@Document(indexName = "mall_inventory_v1")
@Setting(shards = 3, replicas = 1, refreshInterval = "1s")
public class InventorySearchDocument {
    
    @Id
    private String id; // sku_id + warehouse_id
    
    @Version
    private Long version;
    
    // SKU信息
    @Field(type = FieldType.Long)
    private Long skuId;
    
    @Field(type = FieldType.Keyword)
    private String skuCode;
    
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String skuName;
    
    // SPU信息（冗余）
    @Field(type = FieldType.Long)
    private Long spuId;
    
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String spuName;
    
    // 仓库信息
    @Field(type = FieldType.Long)
    private Long warehouseId;
    
    @Field(type = FieldType.Keyword)
    private String warehouseName;
    
    @Field(type = FieldType.Keyword)
    private String warehouseCode;
    
    @Field(type = FieldType.Keyword)
    private String warehouseType; // MAIN, BRANCH, TRANSIT
    
    // 库存信息
    @Field(type = FieldType.Integer)
    private Integer totalStock;
    
    @Field(type = FieldType.Integer)
    private Integer availableStock;
    
    @Field(type = FieldType.Integer)
    private Integer lockedStock;
    
    @Field(type = FieldType.Integer)
    private Integer inTransitStock;
    
    @Field(type = FieldType.Integer)
    private Integer warnStock;
    
    @Field(type = FieldType.Integer)
    private Integer safetyStock;
    
    // 库存状态
    @Field(type = FieldType.Keyword)
    private String stockStatus; // SUFFICIENT, WARNING, DANGER, OUT_OF_STOCK
    
    @Field(type = FieldType.Boolean)
    private Boolean needReplenish;
    
    // 地理位置
    @GeoPointField
    private GeoPoint location;
    
    @Field(type = FieldType.Keyword)
    private String province;
    
    @Field(type = FieldType.Keyword)
    private String city;
    
    @Field(type = FieldType.Keyword)
    private String district;
    
    // 锁定信息
    @Field(type = FieldType.Nested)
    private List<StockLockInfo> locks;
    
    // 时间
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime lastStockChangeTime;
    
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updateTime;
    
    @Data
    public static class StockLockInfo {
        @Field(type = FieldType.Keyword)
        private String orderNo;
        
        @Field(type = FieldType.Integer)
        private Integer lockedQuantity;
        
        @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
        private LocalDateTime lockTime;
        
        @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
        private LocalDateTime expireTime;
    }
    
    @Data
    public static class GeoPoint {
        private double lat;
        private double lon;
    }
}