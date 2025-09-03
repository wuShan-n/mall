package com.mall.user.service;

import com.mall.api.user.constant.UserConstants;
import com.mall.api.user.dto.request.AddressCreateRequest;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Address Feign client for HTTP calls
 */
public interface AddressService {

    Result<AddressVO> createAddress(Long userId,
                                    AddressCreateRequest request);

    Result<AddressVO> updateAddress(Long addressId,
                                     AddressCreateRequest request);

    Result<Void> deleteAddress( Long userId,
                                Long addressId);

    Result<AddressVO> getAddressById( Long addressId);

    Result<List<AddressVO>> getUserAddresses( Long userId);

    Result<AddressVO> getDefaultAddress( Long userId);

    Result<Void> setDefaultAddress(Long userId,
                                    Long addressId);
}