package com.jinyafu.thirdpart.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.jinyafu.thirdpart.persist.DBTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 一主多从数据源，不支持从数据源事务
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    DbConfig dbConfig;

    /**
     * 主数据源配置
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource database = null;
        try {
            database = new DruidDataSource();
            database.setDriverClassName(dbConfig.getDriverClassName());
            database.setUrl(dbConfig.getMaster().getUrl());
            database.setUsername(dbConfig.getMaster().getUsername());
            database.setPassword(dbConfig.getMaster().getPassword());
            database.setFilters(dbConfig.getFilters());
            database.setMaxActive(dbConfig.getMaxActive());
            database.setInitialSize(dbConfig.getInitialSize());
            database.setMaxWait(dbConfig.getMaxWait());
            database.setMinIdle(dbConfig.getMinIdle());
            database.setTimeBetweenEvictionRunsMillis(dbConfig.getTimeBetweenEvictionRunsMillis());
            database.setMinEvictableIdleTimeMillis(dbConfig.getMinEvictableIdleTimeMillis());
            database.setValidationQuery(dbConfig.getValidationQuery());
            database.setTestWhileIdle(dbConfig.isTestWhileIdle());
            database.setTestOnBorrow(dbConfig.isTestOnBorrow());
            database.setTestOnReturn(dbConfig.isTestOnReturn());
            database.setPoolPreparedStatements(false);
            //database.setRemoveAbandoned(environment.getProperty("db.removeAbandoned", Boolean.class));
            //database.setRemoveAbandonedTimeout(environment.getProperty("db.removeAbandonedTimeout", Integer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    /**
     * 读数据源
     *
     * @return
     */
    @Bean(name = "slave1DataSource")
    public DataSource slave1DataSource() {
        DruidDataSource database = null;
        try {
            database = new DruidDataSource();
            database.setDriverClassName(dbConfig.getDriverClassName());
            database.setUrl(dbConfig.getSlave1().getUrl());
            database.setUsername(dbConfig.getSlave1().getUsername());
            database.setPassword(dbConfig.getSlave1().getPassword());
            database.setFilters(dbConfig.getFilters());
            database.setMaxActive(dbConfig.getMaxActive());
            database.setInitialSize(dbConfig.getInitialSize());
            database.setMaxWait(dbConfig.getMaxWait());
            database.setMinIdle(dbConfig.getMinIdle());
            database.setTimeBetweenEvictionRunsMillis(dbConfig.getTimeBetweenEvictionRunsMillis());
            database.setMinEvictableIdleTimeMillis(dbConfig.getMinEvictableIdleTimeMillis());
            database.setValidationQuery(dbConfig.getValidationQuery());
            database.setTestWhileIdle(dbConfig.isTestWhileIdle());
            database.setTestOnBorrow(dbConfig.isTestOnBorrow());
            database.setTestOnReturn(dbConfig.isTestOnReturn());
            database.setPoolPreparedStatements(false);
            //database.setRemoveAbandoned(environment.getProperty("db.removeAbandoned", Boolean.class));
            //database.setRemoveAbandonedTimeout(environment.getProperty("db.removeAbandonedTimeout", Integer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    /**
     * 读数据源
     *
     * @return
     */
    @Bean(name = "slave2DataSource")
    public DataSource slave2DataSource() {
        DruidDataSource database = null;
        try {
            database = new DruidDataSource();
            database.setDriverClassName(dbConfig.getDriverClassName());
            database.setUrl(dbConfig.getSlave2().getUrl());
            database.setUsername(dbConfig.getSlave2().getUsername());
            database.setPassword(dbConfig.getSlave2().getPassword());
            database.setFilters(dbConfig.getFilters());
            database.setMaxActive(dbConfig.getMaxActive());
            database.setInitialSize(dbConfig.getInitialSize());
            database.setMaxWait(dbConfig.getMaxWait());
            database.setMinIdle(dbConfig.getMinIdle());
            database.setTimeBetweenEvictionRunsMillis(dbConfig.getTimeBetweenEvictionRunsMillis());
            database.setMinEvictableIdleTimeMillis(dbConfig.getMinEvictableIdleTimeMillis());
            database.setValidationQuery(dbConfig.getValidationQuery());
            database.setTestWhileIdle(dbConfig.isTestWhileIdle());
            database.setTestOnBorrow(dbConfig.isTestOnBorrow());
            database.setTestOnReturn(dbConfig.isTestOnReturn());
            database.setPoolPreparedStatements(false);
            //database.setRemoveAbandoned(environment.getProperty("db.removeAbandoned", Boolean.class));
            //database.setRemoveAbandonedTimeout(environment.getProperty("db.removeAbandonedTimeout", Integer.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database;
    }

    @Bean
    public DataSource routingDataSource(
          @Qualifier("masterDataSource") DataSource masterDataSource,
          @Qualifier("slave1DataSource") DataSource slave1DataSource,
          @Qualifier("slave2DataSource") DataSource slave2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterDataSource);
        targetDataSources.put(DBTypeEnum.SLAVE1, slave1DataSource);
        targetDataSources.put(DBTypeEnum.SLAVE2, slave2DataSource);
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        return routingDataSource;
    }
}
