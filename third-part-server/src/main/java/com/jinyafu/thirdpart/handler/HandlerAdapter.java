/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: PayHandlerAdapter.java@author: lean.yang@date: 19-7-31 下午4:00@version: 1.0
 */

package com.jinyafu.thirdpart.handler;

/**
 * @author lean.yang
 * @title: HandlerAdapter
 * @projectName jmall_ms
 * @description: 处理适配器
 * @date 2019/7/11
 */
public interface HandlerAdapter {
    /**
     * @return java.lang.Object
     * @throws
     * @description: 业务处理接口demo
     * @params [reqDTO]
     * @author lean.yang
     * @date 2019/7/11
     */
    Object handleDemo(Object reqDTO);

    /**
     * @return boolean
     * @throws
     * @description: 是否支持
     * @params [handler]
     * @author lean.yang
     * @date 2019/7/11
     */
    boolean supports(Object handler);


}
