package com.jinyafu.thirdpart.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.jinyafu.thirdpart.context.DBContextHolder;

/**
 * @author 000062064
 * @title: RoutingDataSource
 * @projectName jmall_ms
 * @description: 数据源路由
 * @date 2019/5/15 18:03
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
