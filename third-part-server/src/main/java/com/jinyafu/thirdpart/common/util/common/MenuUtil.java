package com.jinyafu.thirdpart.common.util.common;

/**
 * @Description:
 * @CreateDate: 2019/8/29 11:06
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public class MenuUtil {

    public static String getKey(String key, String space) {
        String id = "";
        if (null != key) {
            if (key.startsWith("/")) {
                key = key.substring(1, key.length());
            }
            if (null != space) {
                id = key.replace("/", space);
            }
        }
        return id;
    }
}