package com.jinyafu.thirdpart.common.util.common;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 把java对象转换成map对象
 * @CreateDate: 2019/8/29 14:30
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

public class MapTools {

    /*
    * javaBean 转换成 Map
    * */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /*
    * Map 转换成 javaBean
    * */
//    public static <T> T toBean(Map map,Class<T> clazz){
//
//        try {
//            /*
//             * 1创建指定类的javabean对象
//             */
//            T bean=clazz.newInstance();
//            /**
//             * 2.把数据封装到Java bean中
//             */
//            BeanUtils.populate(bean, map);
//            /**
//             * 3.返回Javabean;
//             */
//            return bean;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//
//        }
//    }

}
