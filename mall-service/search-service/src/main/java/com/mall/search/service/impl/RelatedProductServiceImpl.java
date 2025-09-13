package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.JsonData;
import com.mall.search.document.ProductSearchDocument;
import com.mall.search.dto.response.ProductSimpleVO;
import com.mall.search.service.RelatedProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 相关商品推荐服务实现
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RelatedProductServiceImpl implements RelatedProductService {

    private final ElasticsearchClient elasticsearchClient;

    private static final String PRODUCT_INDEX = "mall_product_v1";

    @Override
    public List<ProductSimpleVO> getRelatedProducts(Long spuId, Integer size) {
        try {
            // 1. 先获取基准商品信息
            GetRequest getRequest = GetRequest.of(g -> g
                    .index(PRODUCT_INDEX)
                    .id(spuId.toString())
            );

            GetResponse<ProductSearchDocument> getResponse = elasticsearchClient.get(
                    getRequest,
                    ProductSearchDocument.class
            );

            if (!getResponse.found() || getResponse.source() == null) {
                log.warn("Product not found. SPU ID: {}", spuId);
                return Collections.emptyList();
            }

            ProductSearchDocument baseProduct = getResponse.source();

            // 2. 构建相似查询
            BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

            // 排除自己
            boolBuilder.mustNot(Query.of(q -> q
                    .term(t -> t
                    .field("spuId")
                    .value(spuId)
                    )
            ));

            // 只查询上架商品
            boolBuilder.filter(Query.of(q -> q
                    .term(t -> t
                    .field("status")
                    .value(1)
                    )
            ));

            // 相似条件构建
            List<Query> shouldQueries = buildSimilarQueries(baseProduct);

            boolBuilder.should(shouldQueries);
            boolBuilder.minimumShouldMatch("2"); // 至少满足2个条件

            // 3. 执行搜索
            SearchRequest searchRequest = SearchRequest.of(s -> s
                    .index(PRODUCT_INDEX)
                    .query(boolBuilder.build()._toQuery())
                    .size(size)
                    .sort(so -> so
                    .field(f -> f
                    .field("salesCount")
                    .order(SortOrder.Desc)
                    )
                    )
            );

            SearchResponse<ProductSearchDocument> response = elasticsearchClient.search(
                    searchRequest,
                    ProductSearchDocument.class
            );

            // 4. 转换结果
            return response.hits().hits().stream()
                    .map(hit -> convertToProductSimpleVO(hit.source()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Get related products error. SPU ID: {}", spuId, e);
            return Collections.emptyList();
        }
    }

    /**
     * 构建相似查询条件
     */
    private List<Query> buildSimilarQueries(ProductSearchDocument baseProduct) {
        List<Query> shouldQueries = new ArrayList<>();

        // 同分类（权重高）
        if (baseProduct.getCategoryId() != null) {
            shouldQueries.add(Query.of(q -> q
                    .term(t -> t
                    .field("categoryId")
                    .value(baseProduct.getCategoryId())
                    )
            ));
        }

        // 同品牌
        if (baseProduct.getBrandId() != null) {
            shouldQueries.add(Query.of(q -> q
                    .term(t -> t
                    .field("brandId")
                    .value(baseProduct.getBrandId())
                    )
            ));
        }

        // 相似价格区间（±30%）
        if (baseProduct.getMinPrice() != null) {
            BigDecimal minPrice = baseProduct.getMinPrice()
                    .multiply(BigDecimal.valueOf(0.7));
            BigDecimal maxPrice = baseProduct.getMinPrice()
                    .multiply(BigDecimal.valueOf(1.3));

            shouldQueries.add(Query.of(q -> q
                    .range(r -> r
                    .field("minPrice")
                    .gte(JsonData.of(minPrice))
                    .lte(JsonData.of(maxPrice))
                    )
            ));
        }

        // 相似标签
        if (baseProduct.getTags() != null && !baseProduct.getTags().isEmpty()) {
            shouldQueries.add(Query.of(q -> q
                    .terms(t -> t
                    .field("tags")
                    .terms(TermsQueryField.of(tf -> tf
                    .value(baseProduct.getTags().stream()
                            .map(FieldValue::of)
                            .collect(Collectors.toList()))
                    ))
                    )
            ));
        }

        // 关键词匹配
        if (StringUtils.hasText(baseProduct.getKeywords())) {
            shouldQueries.add(Query.of(q -> q
                    .match(m -> m
                    .field("keywords")
                    .query(baseProduct.getKeywords())
                    )
            ));
        }

        return shouldQueries;
    }

    /**
     * 转换为商品简要信息
     */
    private ProductSimpleVO convertToProductSimpleVO(ProductSearchDocument doc) {
        if (doc == null) {
            return null;
        }

        return ProductSimpleVO.builder()
                .spuId(doc.getSpuId())
                .productName(doc.getProductName())
                .productCode(doc.getProductCode())
                .mainImage(doc.getMainImage())
                .minPrice(doc.getMinPrice())
                .maxPrice(doc.getMaxPrice())
                .salesCount(doc.getSalesCount())
                .commentCount(doc.getCommentCount())
                .score(doc.getAverageScore())
                .brandName(doc.getBrandName())
                .categoryName(doc.getCategoryName())
                .tags(doc.getTags())
                .hasStock(doc.getHasStock())
                .flags(ProductSimpleVO.ProductFlags.builder()
                        .isNew(doc.getIsNew())
                        .isHot(doc.getIsHot())
                        .isBest(doc.getIsBest())
                        .build())
                .build();
    }
}
