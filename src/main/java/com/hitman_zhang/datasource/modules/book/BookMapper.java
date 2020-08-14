package com.hitman_zhang.datasource.modules.book;

import com.hitman_zhang.datasource.common.datasource.DataSourceNames;
import com.hitman_zhang.datasource.common.datasource.annotation.TargetDataSource;
import com.hitman_zhang.datasource.modules.book.pojo.BookPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

    @TargetDataSource(name = DataSourceNames.SECOND)
    int insert(BookPojo book);
}
