/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: DBContextHolder.java@author: lean.yang@date: 19-7-31 下午4:39@version: 1.0
 */

package com.jinyafu.thirdpart.context;


import com.jinyafu.thirdpart.persist.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 000062064
 * @title: DBContextHolder
 * @projectName jmall_ms
 * @description: 数据库源切换上下文
 * @date 2019/5/15 18:10
 */
public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
    }

    public static void slave() {
        //  轮询，取模方式
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
        } else {
            set(DBTypeEnum.SLAVE2);
        }
    }
}
