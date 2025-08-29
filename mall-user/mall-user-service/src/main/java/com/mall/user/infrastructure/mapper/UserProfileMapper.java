package com.mall.user.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.user.infrastructure.po.UserProfilePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详情Mapper
 *
 * @author mall
 */
@Mapper
public interface UserProfileMapper extends BaseMapper<UserProfilePO> {
}
