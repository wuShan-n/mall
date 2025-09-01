package com.mall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.user.entity.UserMember;
import com.mall.user.service.UserMemberService;
import com.mall.user.mapper.UserMemberMapper;
import org.springframework.stereotype.Service;

/**
* @author 21279
* @description 针对表【user_member(会员信息表)】的数据库操作Service实现
* @createDate 2025-09-01 21:14:18
*/
@Service
public class UserMemberServiceImpl extends ServiceImpl<UserMemberMapper, UserMember>
    implements UserMemberService{

}




