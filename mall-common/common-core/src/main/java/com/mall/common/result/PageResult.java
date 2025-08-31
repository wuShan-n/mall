package com.mall.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Pagination result wrapper
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long total;
    private Long pages;
    private Long current;
    private Long size;
    private List<T> records;
    
    public static <T> PageResult<T> of(Long current, Long size, Long total, List<T> records) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCurrent(current);
        pageResult.setSize(size);
        pageResult.setTotal(total);
        pageResult.setPages((total + size - 1) / size);
        pageResult.setRecords(records);
        return pageResult;
    }
}