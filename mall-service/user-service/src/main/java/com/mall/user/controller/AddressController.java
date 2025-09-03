package com.mall.user.controller;

import com.mall.api.user.dto.request.AddressCreateRequest;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.common.result.Result;
import com.mall.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Address Feign client for HTTP calls
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create")
    Result<AddressVO> createAddress(@RequestParam("userId") Long userId,
                                    @RequestBody AddressCreateRequest request){
        return addressService.createAddress(userId,request);
    }

    @PutMapping("/{addressId}")
    Result<AddressVO> updateAddress(@PathVariable("addressId") Long addressId,
                                    @RequestBody AddressCreateRequest request){
        return addressService.updateAddress(addressId,request);

    }

    @DeleteMapping("/{addressId}")
    Result<Void> deleteAddress(@RequestParam("userId") Long userId,
                               @PathVariable("addressId") Long addressId){
        return addressService.deleteAddress(userId,addressId);
    }

    @GetMapping("/{addressId}")
    Result<AddressVO> getAddressById(@PathVariable("addressId") Long addressId){
        return addressService.getAddressById(addressId);

    }


    @GetMapping("/user/{userId}")
    Result<List<AddressVO>> getUserAddresses(@PathVariable("userId") Long userId){
        return addressService.getUserAddresses(userId);

    }

    @GetMapping("/user/{userId}/default")
    Result<AddressVO> getDefaultAddress(@PathVariable("userId") Long userId){
        return addressService.getDefaultAddress(userId);
    }

    @PostMapping("/default")
    Result<Void> setDefaultAddress(@RequestParam("userId") Long userId,
                                   @RequestParam("addressId") Long addressId){
        return addressService.setDefaultAddress(userId,addressId);

    }
}