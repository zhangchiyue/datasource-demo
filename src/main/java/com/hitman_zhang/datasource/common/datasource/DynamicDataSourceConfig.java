package com.hitman_zhang.datasource.common.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 构件数据源以及注入到动态数据源中
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name=DataSourceNames.FIRST)
    @ConfigurationProperties("spring.datasource.druid.datasource1")
    public DataSource firstDatasource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(name=DataSourceNames.SECOND)
    @ConfigurationProperties("spring.datasource.druid.datasource2")
    public DataSource secondDatasource(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean(name=DataSourceNames.THREE)
    @ConfigurationProperties("spring.datasource.druid.datasource3")
    public DataSource threeDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier(DataSourceNames.FIRST) DataSource dataSource1,
                                        @Qualifier(DataSourceNames.SECOND) DataSource dataSource2,
                                        @Qualifier(DataSourceNames.THREE) DataSource dataSource3){
        Map<String ,DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, dataSource1);
        targetDataSources.put(DataSourceNames.SECOND, dataSource2);
        targetDataSources.put(DataSourceNames.THREE, dataSource3);
        return new DynamicDataSource(dataSource1, targetDataSources);
    }





}
