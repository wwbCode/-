package com.jinyafu.thirdpart.common.util.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Json转换类工具
 * @CreateDate: 2019/1/3 19:07
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
public class JsonUtil {
    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static Object convertMap(Class type, Map<String,Object> map) {
        BeanInfo beanInfo = null; // 获取类属性
        Object obj = null; // 创建 JavaBean 对象
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-ddHH:mm:ss");
        try {
            beanInfo = Introspector.getBeanInfo(type);
            obj = type.newInstance();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = null;
                try {
                    value = map.get(propertyName);
                    if(value!=null){
                        /*日期类型需要特殊处理*/
                        if("java.util.Date".equals(descriptor.getPropertyType().getName())){
                            value=sdf.parse(value.toString());
                        }
                        /*BigDecimal类型需要特殊处理*/
                        if("java.math.BigDecimal".equals(descriptor.getPropertyType().getName())){
                            value=new BigDecimal(value.toString());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Object[] args = new Object[1];
                args[0] = value;

                try {
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    /**
     * 将一个 JavaBean转换为 Map 对象
     * @param obj 要转化的对象
     * @return 转化出来的 JavaBean 对象
     */
//    public static Map<String, Object> objectToMap(Object obj) {
//        if(obj == null)
//            return null;
//
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        BeanInfo beanInfo = null;
//        try {
//            beanInfo = Introspector.getBeanInfo(obj.getClass());
//        } catch (IntrospectionException e) {
//            e.printStackTrace();
//        }
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor property : propertyDescriptors) {
//            String key = property.getName();
//            if (key.compareToIgnoreCase("class") == 0) {
//                continue;
//            }
//            Method getter = property.getReadMethod();
//            Object value = null;
//            try {
//                value = getter!=null ? getter.invoke(obj) : null;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//            map.put(key, value);
//        }
//
//        return map;
//    }
}
