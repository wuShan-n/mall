package com.mall.common.mybatis.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.utils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Base service implementation
 * @param <M> Mapper type
 * @param <T> Entity type
 */
public abstract class BaseServiceImpl<M extends SuperMapper<T>, T> 
        extends ServiceImpl<M, T> implements IBaseService<T> {
    
    /**
     * Convert PageRequest to MyBatis-Plus Page
     */
    protected <T> Page<T> toPage(PageRequest request) {
        return new Page<>(request.getCurrent(), request.getSize());
    }
    
    /**
     * Convert MyBatis-Plus IPage to PageResult
     */
    protected <T> PageResult<T> toPageResult(IPage<T> page) {
        return PageResult.of(
            page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            page.getRecords()
        );
    }
    
    /**
     * Convert entity page to VO page
     */
    protected <V> PageResult<V> toPageResult(IPage<T> page, Class<V> voClass) {
        List<V> voList = BeanUtils.copyList(page.getRecords(), voClass);
        return PageResult.of(
            page.getCurrent(),
            page.getSize(),
            page.getTotal(),
            voList
        );
    }
    
    /**
     * Build query wrapper with common conditions
     */
    protected QueryWrapper<T> buildQueryWrapper(T entity) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (entity != null) {
            wrapper.setEntity(entity);
        }
        return wrapper;
    }
    
    /**
     * Batch save with transaction
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchWithTransaction(List<T> entityList) {
        return saveBatch(entityList);
    }
    
    /**
     * Batch update with transaction
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchWithTransaction(List<T> entityList) {
        return updateBatchById(entityList);
    }
}