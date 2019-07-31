/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: RedisProperties.java@author: lean.yang@date: 19-7-31 下午4:31@version: 1.0
 */

package com.jinyafu.thirdpart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lean.yang
 * @title: RedisProperties
 * @projectName jmall_ms
 * @description: redis集群属性配置
 * @date 2019/5/7 16:41
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisProperties {
    private String nodes;
    private Integer commandTimeout;
    private Integer maxAttempts;
    private Integer maxRedirects;
    private Integer maxActive;
    private Integer maxWait;
    private Integer maxIdle;
    private Integer minIdle;
    private boolean testOnBorrow;
}
