package com.hitman_zhang.datasource.modules.user.service;

import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import com.hitman_zhang.datasource.modules.game.pojo.GamePojo;
import com.hitman_zhang.datasource.modules.user.pojo.UserPojo;

public interface UserService {
    int insert(UserPojo user);

    int insertAll(UserPojo user, BookPojo book, GamePojo game);
}
