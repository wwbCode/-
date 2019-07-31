/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: TaskConfig.java@author: lean.yang@date: 19-7-31 下午4:30@version: 1.0
 */

package com.jinyafu.thirdpart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lean.yang
 * @title: TaskConfig
 * @projectName jmall_ms
 * @description: 多线程任务调度配置
 * @date 2019/7/17
 */
@Configuration
public class TaskConfig {
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(20);
        return taskExecutor;
    }

}
