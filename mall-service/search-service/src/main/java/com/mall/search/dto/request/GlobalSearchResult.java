package com.mall.search.dto.request;

import com.mall.search.dto.response.BrandVO;
import com.mall.search.dto.response.CategoryVO;
import com.mall.search.dto.response.ProductSimpleVO;
import com.mall.search.dto.response.StoreVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "全局搜索结果")
public class GlobalSearchResult {
    
    @Schema(description = "商品结果")
    private List<ProductSimpleVO> products;
    
    @Schema(description = "品牌结果")
    private List<BrandVO> brands;
    
    @Schema(description = "分类结果")
    private List<CategoryVO> categories;
    
    @Schema(description = "店铺结果")
    private List<StoreVO> stores;
    
    @Schema(description = "搜索建议")
    private List<String> suggestions;
}