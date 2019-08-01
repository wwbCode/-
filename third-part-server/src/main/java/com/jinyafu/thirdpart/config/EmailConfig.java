/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: IgnoreResourcesConfig.java@author: 000062064@date: 19-6-13 上午9:17@version: 1.0
 */

package com.jinyafu.thirdpart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @Description: 邮件发送配置
* @CreateDate: 2019/8/1 18:42
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
@Data
@Component
@ConfigurationProperties(prefix = "email")
public class EmailConfig {
    /*平台邮件发件人*/
    private String username;
}
