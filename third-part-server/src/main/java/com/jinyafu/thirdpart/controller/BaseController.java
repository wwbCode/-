package com.jinyafu.thirdpart.controller;

import com.jinyafu.thirdpart.config.CompanyDispatch;
import com.jinyafu.thirdpart.handler.HandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:获取适配器公共controller
 * @CreateDate: 2019/8/1 9:46
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
@RestController
public class BaseController {
    @Autowired
    CompanyDispatch companyDispatch;

    /**
     * @return com.jinyafu.jmall.pay.handler.PayHandlerAdapter
     * @throws
     * @description: 获取适配资源
     * @params [psyType]
     * @author lean.yang
     * @date 2019/7/11
     */
    public HandlerAdapter getHandler(String payType) {
        for (HandlerAdapter adapter : companyDispatch.getHandlerAdapters()) {
            if (adapter.supports(payType)) {
                return adapter;
            }
        }
        return null;
    }
}
