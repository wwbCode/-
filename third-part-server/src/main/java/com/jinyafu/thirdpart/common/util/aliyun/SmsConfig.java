/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: SmsConfig.java@author: lean.yang@date: 19-7-23 下午5:51@version: 1.0
 */

package com.jinyafu.thirdpart.common.util.aliyun;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lean.yang
 * @title: SmsConfig
 * @projectName jmall_ms
 * @description: TODO
 * @date 2019/7/23
 */
@Configuration
public class SmsConfig {
    @Bean
    public Producer captchaProducer() {
        return new DefaultKaptcha();
    }
}
