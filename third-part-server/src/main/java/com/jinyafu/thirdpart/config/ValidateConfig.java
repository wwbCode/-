/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: ValidateConfig.java@author: lean.yang@date: 19-7-31 下午4:30@version: 1.0
 */

package com.jinyafu.thirdpart.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author lean.yang
 * @title: ValidateConfig
 * @projectName jmall_ms
 * @description: 校验配置
 * @date 2019/7/8
 */
@Configuration
public class ValidateConfig {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            // 开启快速校验--默认校验所有参数，false校验全部
            .addProperty("hibernate.validator.fail_fast", "true").buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
