package com.hitman_zhang.datasource.modules.game.service.impl;

import com.hitman_zhang.datasource.modules.game.mapper.GameMapper;
import com.hitman_zhang.datasource.modules.game.pojo.GamePojo;
import com.hitman_zhang.datasource.modules.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class GameServiceImpl implements GameService {

    @Autowired
    GameMapper gameMapper;


    @Override
    public int addGame(GamePojo game) {
        return gameMapper.insert(game);
    }
}
