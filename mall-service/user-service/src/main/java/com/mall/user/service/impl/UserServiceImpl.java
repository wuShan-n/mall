package com.mall.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.user.entity.User;
import com.mall.user.service.UserService;
import com.mall.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 21279
* @description 针对表【user(用户基础信息表)】的数据库操作Service实现
* @createDate 2025-09-01 21:14:18
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




