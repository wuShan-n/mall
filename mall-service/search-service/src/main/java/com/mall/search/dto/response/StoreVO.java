package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * =====================================================
 * 店铺信息
 * =====================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "店铺信息")
public class StoreVO {
    
    @Schema(description = "店铺ID")
    private Long storeId;
    
    @Schema(description = "店铺名称")
    private String storeName;
    
    @Schema(description = "店铺Logo")
    private String storeLogo;
    
    @Schema(description = "店铺类型")
    private String storeType; // SELF, THIRD_PARTY
    
    @Schema(description = "店铺等级")
    private Integer storeLevel;
    
    @Schema(description = "店铺评分")
    private StoreScore score;
    
    @Schema(description = "商品数量")
    private Integer productCount;
    
    @Schema(description = "销售量")
    private Integer salesCount;
    
    @Schema(description = "粉丝数")
    private Integer fansCount;
    
    @Schema(description = "店铺标签")
    private List<String> tags;
    
    @Schema(description = "主营类目")
    private List<String> mainCategories;
    
    @Schema(description = "店铺地址")
    private StoreAddress address;
    
    @Schema(description = "认证信息")
    private List<Certification> certifications;
    
    @Schema(description = "是否官方店")
    private Boolean isOfficial;
    
    @Schema(description = "是否认证")
    private Boolean isVerified;
    
    /**
     * 店铺评分
     */
    @Data
    @Builder
    public static class StoreScore {
        @Schema(description = "综合评分")
        private Float totalScore;
        
        @Schema(description = "商品评分")
        private Float productScore;
        
        @Schema(description = "服务评分")
        private Float serviceScore;
        
        @Schema(description = "物流评分")
        private Float logisticsScore;
    }
    
    /**
     * 店铺地址
     */
    @Data
    @Builder
    public static class StoreAddress {
        @Schema(description = "省份")
        private String province;
        
        @Schema(description = "城市")
        private String city;
        
        @Schema(description = "区县")
        private String district;
        
        @Schema(description = "详细地址")
        private String detail;
        
        @Schema(description = "经度")
        private Double longitude;
        
        @Schema(description = "纬度")
        private Double latitude;
    }
    
    /**
     * 认证信息
     */
    @Data
    @Builder
    public static class Certification {
        @Schema(description = "认证类型")
        private String type;
        
        @Schema(description = "认证名称")
        private String name;
        
        @Schema(description = "认证图标")
        private String icon;
    }
}