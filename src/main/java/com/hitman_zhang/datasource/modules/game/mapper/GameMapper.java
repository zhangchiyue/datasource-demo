package com.hitman_zhang.datasource.modules.game.mapper;

import com.hitman_zhang.datasource.common.datasource.DataSourceNames;
import com.hitman_zhang.datasource.common.datasource.annotation.TargetDataSource;
import com.hitman_zhang.datasource.modules.game.pojo.GamePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper {

    @TargetDataSource(name = DataSourceNames.THREE)
    int insert(GamePojo game);
}
