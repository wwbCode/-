package com.jinyafu.thirdpart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 000062064
 * @title: DBConfig
 * @projectName jmall_ms
 * @description: 数据库配置
 * @date 2019/5/15 19:03
 */
@Data
@Component
@ConfigurationProperties(prefix = "db")
public class DbConfig {
    // 主节点
    DbBaseInfo master;
    // 从节点...
    DbBaseInfo slave1;
    DbBaseInfo slave2;

    private String driverClassName;
    private String filters;
    // 最大连接池数量
    private int maxActive;
    // 初始化连接池数量
    private int initialSize;
    // 最小连接池数量
    private int minIdle;
    //获取连接时最大等待时间,单位毫秒
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;
    private String validationQuery;
    //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis,执行validationQuery检测连接是否有效
    private boolean testWhileIdle;
    //申请连接时执行validationQuery检测连接是否有效
    private boolean testOnBorrow;
    //归还连接时执行validationQuery检测连接是否有效
    private boolean testOnReturn;
    private int maxOpenPreparedStatements;
    //对于长时间不使用的连接强制关闭
    private boolean removeAbandoned;
    //超过30分钟开始关闭空闲连接
    private int removeAbandonedTimeout;
    //将当前关闭动作记录到日志
    private boolean logAbandoned;
}
