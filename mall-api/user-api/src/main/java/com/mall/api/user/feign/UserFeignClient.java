package com.mall.api.user.feign;

import com.mall.api.user.constant.UserConstants;

import com.mall.api.user.dto.request.UserLoginRequest;
import com.mall.api.user.dto.request.UserQueryRequest;
import com.mall.api.user.dto.request.UserRegisterRequest;
import com.mall.api.user.dto.request.UserUpdateRequest;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * User Feign client for HTTP calls
 */
@FeignClient(name = UserConstants.SERVICE_NAME, path = UserConstants.API_PREFIX)
public interface UserFeignClient {

    @PostMapping("/register")
    Result<UserVO> register(@RequestBody UserRegisterRequest request);

    @PostMapping("/login")
    Result<UserLoginVO> login(@RequestBody UserLoginRequest request);

    @PostMapping("/logout")
    Result<Void> logout(@RequestHeader("Authorization") String token);

    @GetMapping("/{userId}")
    Result<UserVO> getUserById(@PathVariable("userId") Long userId);

    @GetMapping("/username/{username}")
    Result<UserVO> getUserByUsername(@PathVariable("username") String username);

    @PutMapping("/update")
    Result<UserVO> updateUser(@RequestBody UserUpdateRequest request);

    @PostMapping("/query")
    Result<PageResult<UserVO>> queryUsers(@RequestBody UserQueryRequest request);

    @GetMapping("/check/username/{username}")
    Result<Boolean> checkUsernameExists(@PathVariable("username") String username);

    @GetMapping("/check/phone/{phone}")
    Result<Boolean> checkPhoneExists(@PathVariable("phone") String phone);

    @GetMapping("/check/email/{email}")
    Result<Boolean> checkEmailExists(@PathVariable("email") String email);

    @PostMapping("/token/validate")
    Result<UserVO> validateToken(@RequestHeader("Authorization") String token);

    @PostMapping("/token/refresh")
    Result<String> refreshToken(@RequestParam("refreshToken") String refreshToken);
}
