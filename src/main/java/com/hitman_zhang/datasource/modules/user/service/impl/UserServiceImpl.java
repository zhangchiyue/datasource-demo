package com.hitman_zhang.datasource.modules.user.service.impl;

import com.hitman_zhang.datasource.common.datasource.annotation.TransactionSource;
import com.hitman_zhang.datasource.modules.book.BookMapper;
import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import com.hitman_zhang.datasource.modules.game.mapper.GameMapper;
import com.hitman_zhang.datasource.modules.game.pojo.GamePojo;
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
    @Autowired
    BookMapper bookMapper;
    @Autowired
    GameMapper gameMapper;

    @Override
    public int insert(UserPojo user) {
        return userMapper.insert(user);
    }

    @Override
    @TransactionSource
    public int insertAll(UserPojo user, BookPojo book, GamePojo game) {
        int i1 = userMapper.insert(user);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i2 = bookMapper.insert(book);
        int i3 = gameMapper.insert(game);
        return i1 + i2 + i3;
    }


}
