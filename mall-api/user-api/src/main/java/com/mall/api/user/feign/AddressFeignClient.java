package com.mall.api.user.feign;

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
@FeignClient(name = UserConstants.SERVICE_NAME, path = UserConstants.API_PREFIX + "/address")
public interface AddressFeignClient {

    @PostMapping("/create")
    Result<AddressVO> createAddress(@RequestParam("userId") Long userId,
                                    @RequestBody AddressCreateRequest request);

    @PutMapping("/{addressId}")
    Result<AddressVO> updateAddress(@PathVariable("addressId") Long addressId,
                                    @RequestBody AddressCreateRequest request);

    @DeleteMapping("/{addressId}")
    Result<Void> deleteAddress(@RequestParam("userId") Long userId,
                               @PathVariable("addressId") Long addressId);

    @GetMapping("/{addressId}")
    Result<AddressVO> getAddressById(@PathVariable("addressId") Long addressId);

    @GetMapping("/user/{userId}")
    Result<List<AddressVO>> getUserAddresses(@PathVariable("userId") Long userId);

    @GetMapping("/user/{userId}/default")
    Result<AddressVO> getDefaultAddress(@PathVariable("userId") Long userId);

    @PostMapping("/default")
    Result<Void> setDefaultAddress(@RequestParam("userId") Long userId,
                                   @RequestParam("addressId") Long addressId);
}