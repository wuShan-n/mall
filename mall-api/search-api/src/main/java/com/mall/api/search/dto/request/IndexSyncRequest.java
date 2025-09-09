package com.mall.api.search.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * Index sync request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Index sync request")
public class IndexSyncRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Operation type cannot be null")
    @Schema(description = "Operation type: CREATE/UPDATE/DELETE", example = "CREATE")
    private OperationType operationType;
    
    @NotNull(message = "Document type cannot be null")
    @Schema(description = "Document type: PRODUCT/BRAND/CATEGORY", example = "PRODUCT")
    private DocumentType documentType;
    
    @Schema(description = "Document ID", example = "1")
    private String documentId;
    
    @Schema(description = "Document IDs for batch operation")
    private List<String> documentIds;
    
    @Schema(description = "Document data")
    private Map<String, Object> documentData;
    
    @Schema(description = "Document data list for batch operation")
    private List<Map<String, Object>> documentDataList;
    
    @Schema(description = "Index name override", example = "mall_product")
    private String indexName;
    
    @Schema(description = "Async operation", example = "true")
    private Boolean async = true;
    
    /**
     * Operation type enum
     */
    public enum OperationType {
        CREATE,
        UPDATE,
        DELETE,
        BULK_CREATE,
        BULK_UPDATE,
        BULK_DELETE,
        REINDEX
    }
    
    /**
     * Document type enum
     */
    public enum DocumentType {
        PRODUCT,
        BRAND,
        CATEGORY,
        STORE
    }
}