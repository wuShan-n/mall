package com.mall.order.adapter;

import com.mall.api.user.dubbo.AddressDubboService;
import com.mall.api.user.dubbo.MemberDubboService;
import com.mall.api.user.dubbo.UserDubboService;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dto.response.MemberVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户服务适配器 封装用户相关的Dubbo调用，保持订单服务的独立性
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceAdapter {

    @DubboReference(version = "1.0.0", group = "mall")
    private UserDubboService userDubboService;

    @DubboReference(version = "1.0.0", group = "mall")
    private MemberDubboService memberDubboService;

    @DubboReference(version = "1.0.0", group = "mall")
    private AddressDubboService addressDubboService;

    /**
     * 获取用户信息
     */
    public UserVO getUserInfo(Long userId) {
        Result<UserVO> result = userDubboService.getUserById(userId);
        if (!result.isSuccess()) {
            throw new BusinessException("获取用户信息失败: " + result.getMessage());
        }
        return result.getData();
    }

    /**
     * 获取收货地址
     */
    public AddressVO getAddress(Long addressId) {
        Result<AddressVO> result = addressDubboService.getAddressById(addressId);
        if (!result.isSuccess()) {
            throw new BusinessException("获取收货地址失败: " + result.getMessage());
        }
        return result.getData();
    }

    /**
     * 获取用户地址列表
     */
    public List<AddressVO> getUserAddresses(Long userId) {
        Result<List<AddressVO>> result = addressDubboService.getUserAddresses(userId);
        if (!result.isSuccess()) {
            log.error("获取用户地址列表失败: userId={}, error={}", userId, result.getMessage());
            return List.of();
        }
        return result.getData();
    }

    /**
     * 获取默认地址
     */
    public AddressVO getDefaultAddress(Long userId) {
        Result<AddressVO> result = addressDubboService.getDefaultAddress(userId);
        if (!result.isSuccess()) {
            log.warn("获取默认地址失败: userId={}, error={}", userId, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 获取会员信息
     */
    public MemberVO getMemberInfo(Long userId) {
        Result<MemberVO> result = memberDubboService.getMemberByUserId(userId);
        if (!result.isSuccess()) {
            log.error("获取会员信息失败: userId={}, error={}", userId, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 获取会员折扣
     */
    public BigDecimal getMemberDiscount(Long userId) {
        Result<BigDecimal> result = memberDubboService.getMemberDiscount(userId);
        if (!result.isSuccess()) {
            log.error("获取会员折扣失败: userId={}, error={}", userId, result.getMessage());
            return BigDecimal.ONE; // 默认无折扣
        }
        return result.getData();
    }

    /**
     * 扣减用户积分
     */
    public void deductPoints(Long userId, Integer points, String orderNo) {
        if (points == null || points <= 0) {
            return;
        }

        Result<Integer> result = memberDubboService.deductPoints(
                userId, points, "ORDER_PAYMENT", orderNo);
        if (!result.isSuccess()) {
            throw new BusinessException("积分扣减失败: " + result.getMessage());
        }
        log.info("成功扣减用户积分: userId={}, points={}, orderNo={}", userId, points, orderNo);
    }

    /**
     * 退还用户积分
     */
    public void returnPoints(Long userId, Integer points, String orderNo) {
        if (points == null || points <= 0) {
            return;
        }

        try {
            Result<Integer> result = memberDubboService.addPoints(
                    userId, points, "ORDER_CANCEL", orderNo);
            if (!result.isSuccess()) {
                log.error("积分退还失败: userId={}, points={}, orderNo={}, error={}",
                        userId, points, orderNo, result.getMessage());
            } else {
                log.info("成功退还用户积分: userId={}, points={}, orderNo={}", userId, points, orderNo);
            }
        } catch (Exception e) {
            log.error("积分退还异常: userId={}, points={}, orderNo={}", userId, points, orderNo, e);
        }
    }

    /**
     * 赠送积分和成长值（订单完成时）
     */
    public void awardPointsAndGrowth(Long userId, Integer points, Integer growth, String orderNo) {
        // 赠送积分
        if (points != null && points > 0) {
            try {
                Result<Integer> pointsResult = memberDubboService.addPoints(
                        userId, points, "ORDER_COMPLETE", orderNo);
                if (!pointsResult.isSuccess()) {
                    log.error("积分赠送失败: userId={}, points={}, orderNo={}, error={}",
                            userId, points, orderNo, pointsResult.getMessage());
                } else {
                    log.info("成功赠送积分: userId={}, points={}, orderNo={}", userId, points, orderNo);
                }
            } catch (Exception e) {
                log.error("积分赠送异常: userId={}, points={}, orderNo={}", userId, points, orderNo, e);
            }
        }

        // 赠送成长值
        if (growth != null && growth > 0) {
            try {
                Result<Integer> growthResult = memberDubboService.addGrowthValue(userId, growth);
                if (!growthResult.isSuccess()) {
                    log.error("成长值赠送失败: userId={}, growth={}, error={}",
                            userId, growth, growthResult.getMessage());
                } else {
                    log.info("成功赠送成长值: userId={}, growth={}", userId, growth);
                }
            } catch (Exception e) {
                log.error("成长值赠送异常: userId={}, growth={}", userId, growth, e);
            }
        }
    }

    /**
     * 扣减用户余额
     */
    public void deductBalance(Long userId, BigDecimal amount, String orderNo) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        Result<BigDecimal> result = memberDubboService.deductBalance(userId, amount);
        if (!result.isSuccess()) {
            throw new BusinessException("余额扣减失败: " + result.getMessage());
        }
        log.info("成功扣减用户余额: userId={}, amount={}, orderNo={}", userId, amount, orderNo);
    }

    /**
     * 验证用户积分是否足够
     */
    public boolean checkPointsSufficient(Long userId, Integer usePoints) {
        if (usePoints == null || usePoints <= 0) {
            return true;
        }

        MemberVO member = getMemberInfo(userId);
        if (member == null) {
            return false;
        }

        return member.getPoints() >= usePoints;
    }

    /**
     * 验证用户余额是否足够
     */
    public boolean checkBalanceSufficient(Long userId, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return true;
        }

        MemberVO member = getMemberInfo(userId);
        if (member == null) {
            return false;
        }

        return member.getBalance().compareTo(amount) >= 0;
    }
}
