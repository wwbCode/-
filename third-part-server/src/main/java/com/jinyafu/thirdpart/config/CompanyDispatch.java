/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: PayDispatch.java@author: lean.yang@date: 19-7-31 下午4:03@version: 1.0
 */

package com.jinyafu.thirdpart.config;

import com.jinyafu.thirdpart.handler.HandlerAdapter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lean.yang
 * @title: PayDispatch
 * @projectName jmall_ms
 * @description: 请求分发集合
 * @date 2019/7/11
 */
@Data
public class CompanyDispatch {
    List<HandlerAdapter> handlerAdapters = new ArrayList<>();
}
