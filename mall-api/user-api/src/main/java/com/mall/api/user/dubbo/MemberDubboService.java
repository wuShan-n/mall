package com.mall.api.user.dubbo;

import com.mall.api.user.dto.response.MemberVO;
import com.mall.common.result.Result;
import java.math.BigDecimal;

/**
 * Member Dubbo RPC service interface
 */
public interface MemberDubboService {
    
    /**
     * Get member info by user ID
     */
    Result<MemberVO> getMemberByUserId(Long userId);
    
    /**
     * Add points
     */
    Result<Integer> addPoints(Long userId, Integer points, String source, String sourceId);
    
    /**
     * Deduct points
     */
    Result<Integer> deductPoints(Long userId, Integer points, String source, String sourceId);
    
    /**
     * Add growth value
     */
    Result<Integer> addGrowthValue(Long userId, Integer growthValue);
    
    /**
     * Recharge balance
     */
    Result<BigDecimal> rechargeBalance(Long userId, BigDecimal amount);
    
    /**
     * Deduct balance
     */
    Result<BigDecimal> deductBalance(Long userId, BigDecimal amount);
    
    /**
     * Update member level
     */
    Result<MemberVO> updateMemberLevel(Long userId);
    
    /**
     * Get member discount
     */
    Result<BigDecimal> getMemberDiscount(Long userId);
}