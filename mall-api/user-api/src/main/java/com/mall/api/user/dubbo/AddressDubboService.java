package com.mall.api.user.dubbo;

import com.mall.api.user.dto.request.AddressCreateRequest;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.common.result.Result;
import java.util.List;

/**
 * Address Dubbo RPC service interface
 */
public interface AddressDubboService {
    
    /**
     * Create address
     */
    Result<AddressVO> createAddress(Long userId, AddressCreateRequest request);
    
    /**
     * Update address
     */
    Result<AddressVO> updateAddress(Long addressId, AddressCreateRequest request);
    
    /**
     * Delete address
     */
    Result<Void> deleteAddress(Long userId, Long addressId);
    
    /**
     * Get address by ID
     */
    Result<AddressVO> getAddressById(Long addressId);
    
    /**
     * Get user addresses
     */
    Result<List<AddressVO>> getUserAddresses(Long userId);
    
    /**
     * Get default address
     */
    Result<AddressVO> getDefaultAddress(Long userId);
    
    /**
     * Set default address
     */
    Result<Void> setDefaultAddress(Long userId, Long addressId);
}