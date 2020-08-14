package com.hitman_zhang.datasource.modules.user.service.impl;

import com.hitman_zhang.datasource.modules.user.mapper.UserMapper;
import com.hitman_zhang.datasource.modules.user.pojo.UserPojo;
import com.hitman_zhang.datasource.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(UserPojo user) {
        return userMapper.insert(user);
    }
}
