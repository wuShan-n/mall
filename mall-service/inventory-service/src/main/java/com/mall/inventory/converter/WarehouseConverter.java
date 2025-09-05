package com.mall.inventory.converter;

import com.mall.api.inventory.dto.request.WarehouseCreateRequest;
import com.mall.api.inventory.dto.response.WarehouseVO;
import com.mall.inventory.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseConverter {
    
    @Mapping(target = "fullAddress", expression = "java(warehouse.getProvince() + warehouse.getCity() + warehouse.getDistrict() + warehouse.getAddress())")
    WarehouseVO toVO(Warehouse warehouse);
    
    List<WarehouseVO> toVOList(List<Warehouse> warehouses);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Warehouse toEntity(WarehouseCreateRequest request);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateEntity(WarehouseCreateRequest request, @MappingTarget Warehouse warehouse);
}