/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: PayDispatchConfig.java@author: lean.yang@date: 19-7-31 下午4:03@version: 1.0
 */

package com.jinyafu.thirdpart.config;

import com.jinyafu.thirdpart.company.google.GoogleHandler;
import com.jinyafu.thirdpart.company.sun.SunHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lean.yang
 * @title: PayDispatchConfig
 * @projectName jmall_ms
 * @description: 第三方分发配置
 * @date 2019/7/11
 */
@Configuration
public class DispatchConfig {
    @Bean
    public CompanyDispatch payDispatch() {
        CompanyDispatch payDispatch = new CompanyDispatch();
        payDispatch.getHandlerAdapters().add(googleHandler());
        payDispatch.getHandlerAdapters().add((sunHandler()));
        return payDispatch;
    }

    /**
     * @description: GOOGLE
     * @params []
     * @return com.jinyafu.jmall.pay.handler.PayHandlerAdapter
     * @throws
     * @author lean.yang
     * @date 2019/7/11
     */
    @Bean
    public GoogleHandler googleHandler() {
        return new GoogleHandler();
    }

    /**
     * @description: Sun
     * @date: 2019/8/1 15:03
     * @author: mjt
     * @param:  []
     * @return: com.jinyafu.thirdpart.company.sun.SunHandler
     */
    @Bean
    public SunHandler sunHandler(){
        return new SunHandler();
    }

}
