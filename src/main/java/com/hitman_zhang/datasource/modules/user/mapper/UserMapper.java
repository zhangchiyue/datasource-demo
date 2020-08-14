package com.hitman_zhang.datasource.modules.user.mapper;

import com.hitman_zhang.datasource.common.datasource.DataSourceNames;
import com.hitman_zhang.datasource.common.datasource.annotation.TargetDataSource;
import com.hitman_zhang.datasource.modules.user.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @TargetDataSource(name = DataSourceNames.FIRST)
    int insert(UserPojo user);


}
