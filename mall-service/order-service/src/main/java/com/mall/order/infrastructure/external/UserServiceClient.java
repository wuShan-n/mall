package com.mall.order.infrastructure.external;

import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.api.user.dubbo.AddressDubboService;
import com.mall.api.user.dubbo.UserDubboService;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 用户服务客户端
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient {
    
    @DubboReference(version = "1.0.0", check = false)
    private UserDubboService userDubboService;
    
    @DubboReference(version = "1.0.0", check = false)
    private AddressDubboService addressDubboService;
    
    /**
     * 获取用户信息
     */
    public UserVO getUserById(Long userId) {
        try {
            Result<UserVO> result = userDubboService.getUserById(userId);
            if (!result.isSuccess()) {
                throw new RuntimeException("获取用户信息失败: " + result.getMessage());
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Call user service failed: {}", e.getMessage(), e);
            throw new RuntimeException("调用用户服务失败", e);
        }
    }
    
    /**
     * 获取地址信息
     */
    public AddressVO getAddressById(Long addressId) {
        try {
            Result<AddressVO> result = addressDubboService.getAddressById(addressId);
            if (!result.isSuccess()) {
                throw new RuntimeException("获取地址信息失败: " + result.getMessage());
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Call address service failed: {}", e.getMessage(), e);
            throw new RuntimeException("调用地址服务失败", e);
        }
    }
}
