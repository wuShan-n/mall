package com.mall.user.provider;

import com.mall.api.user.dto.request.AddressCreateRequest;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dubbo.AddressDubboService;
import com.mall.common.result.Result;
import com.mall.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

@Slf4j
@DubboService(version = "1.0.0", timeout = 3000)
@RequiredArgsConstructor
public class AddressDubboProviderImpl implements AddressDubboService {

    private final AddressService addressService;

    @Override
    public Result<AddressVO> createAddress(Long userId, AddressCreateRequest request) {
        return null;
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
        return addressService.getAddressById(addressId);

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
