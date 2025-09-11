package com.mall.search.service.impl;

import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuDetailVO;
import com.mall.api.product.feign.ProductFeignClient;
import com.mall.api.search.dto.request.IndexSyncRequest;
import com.mall.api.search.enums.SearchEnums;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import com.mall.search.document.ProductDocument;
import com.mall.search.repository.ProductSearchRepository;
import com.mall.search.service.IndexSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Index synchronization service
 * 
 * @author mall
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class IndexSyncServiceImpl implements IndexSyncService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ProductSearchRepository productSearchRepository;
    private final ProductFeignClient productFeignClient;

    @Override
    public Result<Void> syncProductToIndex(IndexSyncRequest request) {
        try {
            switch (request.getOperationType()) {
                case CREATE:
                case UPDATE:
                    syncSingleProduct(request);
                    break;
                case DELETE:
                    deleteSingleProduct(request);
                    break;
                case BULK_CREATE:
                case BULK_UPDATE:
                    batchSyncProducts(request);
                    break;
                case BULK_DELETE:
                    batchDeleteProducts(request);
                    break;
                case REINDEX:
                    reindexProducts();
                    break;
                default:
                    throw new BusinessException("Unsupported operation type: " + request.getOperationType());
            }
            return Result.success();
        } catch (Exception e) {
            log.error("Sync product to index failed", e);
            throw new BusinessException("Index sync failed: " + e.getMessage());
        }
    }

    @Override
    @Async
    public void asyncSyncProductToIndex(Long spuId) {
        try {
            // Fetch product details from product service
            Result<SpuDetailVO> result = productFeignClient.getSpuDetail(spuId);
            if (!result.isSuccess() || result.getData() == null) {
                log.error("Failed to fetch product details for SPU: {}", spuId);
                return;
            }
            
            SpuDetailVO spuDetail = result.getData();
            
            // Convert to document and save
            List<ProductDocument> documents = convertToDocuments(spuDetail);
            productSearchRepository.saveAll(documents);
            
            log.info("Successfully synced product to index: SPU={}", spuId);
        } catch (Exception e) {
            log.error("Failed to sync product to index: SPU=" + spuId, e);
        }
    }

    @Override
    @Async
    public void asyncRemoveProductFromIndex(Long spuId) {
        try {
            productSearchRepository.deleteBySpuId(spuId);
            log.info("Successfully removed product from index: SPU={}", spuId);
        } catch (Exception e) {
            log.error("Failed to remove product from index: SPU=" + spuId, e);
        }
    }

    @Override
    @Transactional
    public Result<Void> rebuildIndex(String indexType) {
        try {
            // Get index coordinates
            IndexCoordinates indexCoordinates = IndexCoordinates.of("mall_product");
            IndexOperations indexOperations = elasticsearchOperations.indexOps(indexCoordinates);
            
            // Delete existing index if exists
            if (indexOperations.exists()) {
                indexOperations.delete();
                log.info("Deleted existing index: {}", indexType);
            }
            
            // Create new index with mapping
            indexOperations.create();
            indexOperations.putMapping(ProductDocument.class);
            log.info("Created new index with mapping: {}", indexType);
            
            // TODO: Implement full reindex from database
            // This would typically involve:
            // 1. Fetching all products from product service in batches
            // 2. Converting them to documents
            // 3. Bulk indexing them
            
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to rebuild index: " + indexType, e);
            throw new BusinessException("Rebuild index failed: " + e.getMessage());
        }
    }

    private void syncSingleProduct(IndexSyncRequest request) {
        Long spuId = Long.valueOf(request.getDocumentId());
        
        // Fetch product details
        Result<SpuDetailVO> result = productFeignClient.getSpuDetail(spuId);
        if (!result.isSuccess() || result.getData() == null) {
            throw new BusinessException("Failed to fetch product details");
        }
        
        // Convert and save
        List<ProductDocument> documents = convertToDocuments(result.getData());
        productSearchRepository.saveAll(documents);
    }

    private void deleteSingleProduct(IndexSyncRequest request) {
        if (SearchEnums.DocumentType.PRODUCT.name().equals(request.getDocumentType().name())) {
            productSearchRepository.deleteBySpuId(Long.valueOf(request.getDocumentId()));
        }
    }

    private void batchSyncProducts(IndexSyncRequest request) {
        List<ProductDocument> documents = new ArrayList<>();
        
        for (String documentId : request.getDocumentIds()) {
            Result<SpuDetailVO> result = productFeignClient.getSpuDetail(Long.valueOf(documentId));
            if (result.isSuccess() && result.getData() != null) {
                documents.addAll(convertToDocuments(result.getData()));
            }
        }
        
        if (!documents.isEmpty()) {
            productSearchRepository.saveAll(documents);
        }
    }

    private void batchDeleteProducts(IndexSyncRequest request) {
        for (String documentId : request.getDocumentIds()) {
            productSearchRepository.deleteBySpuId(Long.valueOf(documentId));
        }
    }

    private void reindexProducts() {
        // Implementation for full reindex
        // This would be a heavy operation and should be done carefully
        log.info("Starting full reindex of products");
        
        // TODO: Implement pagination and batch processing
        // 1. Query all products from product service
        // 2. Convert to documents in batches
        // 3. Use bulk API for efficient indexing
    }

    private List<ProductDocument> convertToDocuments(SpuDetailVO spu) {
        List<ProductDocument> documents = new ArrayList<>();
        
        // Create a document for each SKU
        if (spu.getSkuList() != null && !spu.getSkuList().isEmpty()) {
            for (SkuVO sku : spu.getSkuList()) {
                ProductDocument doc = new ProductDocument();
                
                // Set IDs
                doc.setId(sku.getId().toString());
                doc.setSpuId(spu.getId());
                doc.setSkuId(sku.getId());
                
                // Set basic info
                doc.setProductName(spu.getProductName());
                doc.setProductCode(spu.getProductCode());
                doc.setCategoryId(spu.getCategoryId());
                doc.setCategoryName(spu.getCategoryName());
                doc.setBrandId(spu.getBrandId());
                doc.setBrandName(spu.getBrandName());
                doc.setMainImage(spu.getMainImage());
                
                // Set SKU specific info
                doc.setPrice(sku.getPrice());
                doc.setOriginalPrice(sku.getOriginalPrice());
                doc.setStock(sku.getStock());
                doc.setStockStatus(determineStockStatus(sku.getStock()));
                
                // Set sales and rating info
                doc.setSalesCount(spu.getSaleCount());
                doc.setCommentCount(spu.getCommentCount());
                doc.setScore(spu.getScore() != null ? spu.getScore().floatValue() : null);
                
                // Set flags
                doc.setIsNew(spu.getIsNew());
                doc.setIsHot(spu.getIsHot());
                doc.setIsBest(spu.getIsBest());
                doc.setHasDiscount(sku.getOriginalPrice() != null && 
                    sku.getPrice().compareTo(sku.getOriginalPrice()) < 0);
                
                // Set other fields
                doc.setStatus(spu.getStatus());
                doc.setKeywords(spu.getKeywords());
                doc.setIntroduction(spu.getIntroduction());
                doc.setTags(spu.getTags() != null ? 
                    List.of(spu.getTags().split(",")) : null);
                
                // Set attributes
                if (spu.getAttributes() != null) {
                    List<ProductDocument.ProductAttribute> attrs = spu.getAttributes().stream()
                        .map(attr -> {
                            ProductDocument.ProductAttribute pa = new ProductDocument.ProductAttribute();
                            pa.setAttrName(attr.getAttrName());
                            pa.setAttrValue(attr.getAttributeValue());
                            return pa;
                        })
                        .collect(Collectors.toList());
                    doc.setAttributes(attrs);
                }
                
                // Set timestamps
                doc.setCreateTime(spu.getCreateTime());
                doc.setUpdateTime(LocalDateTime.now());
                
                documents.add(doc);
            }
        } else {
            // Create a single document for SPU without SKUs
            ProductDocument doc = new ProductDocument();
            doc.setId(spu.getId().toString());
            doc.setSpuId(spu.getId());
            doc.setProductName(spu.getProductName());
            doc.setProductCode(spu.getProductCode());
            doc.setCategoryId(spu.getCategoryId());
            doc.setCategoryName(spu.getCategoryName());
            doc.setBrandId(spu.getBrandId());
            doc.setBrandName(spu.getBrandName());
            doc.setMainImage(spu.getMainImage());
            doc.setPrice(spu.getMinPrice());
            doc.setOriginalPrice(spu.getMaxPrice());
            doc.setSalesCount(spu.getSaleCount());
            doc.setCommentCount(spu.getCommentCount());
            doc.setScore(spu.getScore() != null ? spu.getScore().floatValue() : null);
            doc.setIsNew(spu.getIsNew());
            doc.setIsHot(spu.getIsHot());
            doc.setIsBest(spu.getIsBest());
            doc.setStatus(spu.getStatus());
            doc.setKeywords(spu.getKeywords());
            doc.setIntroduction(spu.getIntroduction());
            doc.setCreateTime(spu.getCreateTime());
            doc.setUpdateTime(LocalDateTime.now());
            
            documents.add(doc);
        }
        
        return documents;
    }

    private String determineStockStatus(Integer stock) {
        if (stock == null || stock <= 0) {
            return SearchEnums.StockStatus.OUT_OF_STOCK.getCode();
        } else if (stock < 10) {
            return SearchEnums.StockStatus.LOW_STOCK.getCode();
        } else {
            return SearchEnums.StockStatus.IN_STOCK.getCode();
        }
    }
}