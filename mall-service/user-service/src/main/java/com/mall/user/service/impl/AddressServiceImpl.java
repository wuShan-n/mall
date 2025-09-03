package com.mall.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mall.api.user.dto.request.AddressCreateRequest;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.common.result.Result;
import com.mall.user.entity.UserAddress;
import com.mall.user.mapper.UserAddressMapper;
import com.mall.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by wuShan on 2025/9/3
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final UserAddressMapper userAddressMapper;

    @Override
    public Result<AddressVO> createAddress(Long userId, AddressCreateRequest request) {
        UserAddress address = new UserAddress();
        BeanUtil.copyProperties(request,address);
        address.setUserId(userId);
        userAddressMapper.insert(address);
        AddressVO vo = new AddressVO();
        BeanUtil.copyProperties(address,vo);
        vo.setFullAddress(address.getProvince()+address.getCity()+address.getDistrict()+address.getDetailAddress());
        return Result.success(vo);
    }

    @Override
    public Result<AddressVO> updateAddress(Long addressId, AddressCreateRequest request) {
        return null;
    }

    @Override
    public Result<Void> deleteAddress(Long userId, Long addressId) {
        return null;
    }

    @Override
    public Result<AddressVO> getAddressById(Long addressId) {
        UserAddress address = userAddressMapper.selectById(addressId);
        AddressVO vo = new AddressVO();
        BeanUtil.copyProperties(address,vo);
        return Result.success(vo);
    }

    @Override
    public Result<List<AddressVO>> getUserAddresses(Long userId) {
        return null;
    }

    @Override
    public Result<AddressVO> getDefaultAddress(Long userId) {
        return null;
    }

    @Override
    public Result<Void> setDefaultAddress(Long userId, Long addressId) {
        return null;
    }
}
