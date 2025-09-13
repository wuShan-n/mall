package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

/**
 * =====================================================
 * 店铺信息
 * =====================================================
 */
@Value
@Builder
@Schema(description = "店铺信息")
public class StoreVO {

    @Schema(description = "店铺ID")
    Long storeId;

    @Schema(description = "店铺名称")
    String storeName;

    @Schema(description = "店铺Logo")
    String storeLogo;

    @Schema(description = "店铺类型")
    String storeType; // SELF, THIRD_PARTY

    @Schema(description = "店铺等级")
    Integer storeLevel;

    @Schema(description = "店铺评分")
    StoreScore score;

    @Schema(description = "商品数量")
    Integer productCount;

    @Schema(description = "销售量")
    Integer salesCount;

    @Schema(description = "粉丝数")
    Integer fansCount;

    @Schema(description = "店铺标签")
    List<String> tags;

    @Schema(description = "主营类目")
    List<String> mainCategories;

    @Schema(description = "店铺地址")
    StoreAddress address;

    @Schema(description = "认证信息")
    List<Certification> certifications;

    @Schema(description = "是否官方店")
    Boolean isOfficial;

    @Schema(description = "是否认证")
    Boolean isVerified;

    /**
     * 店铺评分
     */
    @Data
    @Builder
    public static class StoreScore {
        @Schema(description = "综合评分")
        Float totalScore;

        @Schema(description = "商品评分")
        Float productScore;

        @Schema(description = "服务评分")
        Float serviceScore;

        @Schema(description = "物流评分")
        Float logisticsScore;
    }

    /**
     * 店铺地址
     */
    @Data
    @Builder
    public static class StoreAddress {
        @Schema(description = "省份")
        String province;

        @Schema(description = "城市")
        String city;

        @Schema(description = "区县")
        String district;

        @Schema(description = "详细地址")
        String detail;

        @Schema(description = "经度")
        Double longitude;

        @Schema(description = "纬度")
        Double latitude;
    }

    /**
     * 认证信息
     */
    @Data
    @Builder
    public static class Certification {
        @Schema(description = "认证类型")
        String type;

        @Schema(description = "认证名称")
        String name;

        @Schema(description = "认证图标")
        String icon;
    }
}