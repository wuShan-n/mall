package com.mall.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.inventory.dto.request.WarehouseCreateRequest;
import com.mall.api.inventory.dto.response.WarehouseVO;
import com.mall.common.base.PageRequest;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.PageResult;
import com.mall.inventory.converter.WarehouseConverter;
import com.mall.inventory.entity.Warehouse;
import com.mall.inventory.mapper.WarehouseMapper;
import com.mall.inventory.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    
    private final WarehouseMapper warehouseMapper;
    private final WarehouseConverter warehouseConverter;
    
    @Override
    @Transactional
    public WarehouseVO createWarehouse(WarehouseCreateRequest request) {
        // 检查编码是否存在
        Long count = warehouseMapper.selectCount(
            new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getWarehouseCode, request.getWarehouseCode())
        );
        
        if (count > 0) {
            throw new BusinessException("Warehouse code already exists");
        }
        
        Warehouse warehouse = warehouseConverter.toEntity(request);
        warehouseMapper.insert(warehouse);
        
        return warehouseConverter.toVO(warehouse);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "warehouse", key = "#warehouseId")
    public WarehouseVO updateWarehouse(Long warehouseId, WarehouseCreateRequest request) {
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        if (warehouse == null) {
            throw new BusinessException("Warehouse not found");
        }
        
        // 检查编码是否重复
        if (!warehouse.getWarehouseCode().equals(request.getWarehouseCode())) {
            Long count = warehouseMapper.selectCount(
                new LambdaQueryWrapper<Warehouse>()
                    .eq(Warehouse::getWarehouseCode, request.getWarehouseCode())
                    .ne(Warehouse::getId, warehouseId)
            );
            
            if (count > 0) {
                throw new BusinessException("Warehouse code already exists");
            }
        }
        
        warehouseConverter.updateEntity(request, warehouse);
        warehouseMapper.updateById(warehouse);
        
        return warehouseConverter.toVO(warehouse);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "warehouse", key = "#warehouseId")
    public void deleteWarehouse(Long warehouseId) {
        warehouseMapper.deleteById(warehouseId);
    }
    
    @Override
    @Cacheable(value = "warehouse", key = "#warehouseId")
    public WarehouseVO getWarehouseById(Long warehouseId) {
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        return warehouse != null ? warehouseConverter.toVO(warehouse) : null;
    }
    
    @Override
    @Cacheable(value = "warehouse", key = "#warehouseCode")
    public WarehouseVO getWarehouseByCode(String warehouseCode) {
        Warehouse warehouse = warehouseMapper.selectOne(
            new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getWarehouseCode, warehouseCode)
        );
        return warehouse != null ? warehouseConverter.toVO(warehouse) : null;
    }
    
    @Override
    public List<WarehouseVO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectList(null);
        return warehouseConverter.toVOList(warehouses);
    }
    
    @Override
    public List<WarehouseVO> getActiveWarehouses() {
        List<Warehouse> warehouses = warehouseMapper.selectList(
            new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getStatus, 1)
        );
        return warehouseConverter.toVOList(warehouses);
    }
    
    @Override
    public PageResult<WarehouseVO> queryWarehouses(PageRequest request) {
        Page<Warehouse> page = new Page<>(request.getCurrent(), request.getSize());
        Page<Warehouse> result = warehouseMapper.selectPage(page, null);
        
        List<WarehouseVO> records = warehouseConverter.toVOList(result.getRecords());
        return PageResult.of(result.getCurrent(), result.getSize(), result.getTotal(), records);
    }
    
    @Override
    @Transactional
    @CacheEvict(value = "warehouse", key = "#warehouseId")
    public void updateWarehouseStatus(Long warehouseId, Integer status) {
        Warehouse warehouse = warehouseMapper.selectById(warehouseId);
        if (warehouse == null) {
            throw new BusinessException("Warehouse not found");
        }
        
        warehouse.setStatus(status);
        warehouseMapper.updateById(warehouse);
    }
    
    @Override
    public WarehouseVO getDefaultWarehouse() {
        Warehouse warehouse = warehouseMapper.selectOne(
            new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getWarehouseType, 1)
                .eq(Warehouse::getStatus, 1)
                .orderByAsc(Warehouse::getId)
                .last("LIMIT 1")
        );
        return warehouse != null ? warehouseConverter.toVO(warehouse) : null;
    }
    
    @Override
    public WarehouseVO getNearestWarehouse(String province, String city) {
        // 首先查找同城市的仓库
        Warehouse warehouse = warehouseMapper.selectOne(
            new LambdaQueryWrapper<Warehouse>()
                .eq(Warehouse::getProvince, province)
                .eq(Warehouse::getCity, city)
                .eq(Warehouse::getStatus, 1)
                .orderByAsc(Warehouse::getWarehouseType)
                .last("LIMIT 1")
        );
        
        // 如果没有找到，查找同省份的仓库
        if (warehouse == null) {
            warehouse = warehouseMapper.selectOne(
                new LambdaQueryWrapper<Warehouse>()
                    .eq(Warehouse::getProvince, province)
                    .eq(Warehouse::getStatus, 1)
                    .orderByAsc(Warehouse::getWarehouseType)
                    .last("LIMIT 1")
            );
        }
        
        // 如果还是没有，返回默认仓库
        if (warehouse == null) {
            return getDefaultWarehouse();
        }
        
        return warehouseConverter.toVO(warehouse);
    }
}