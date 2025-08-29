package com.mall.user.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.user.infrastructure.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper
 *
 * @author mall
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
