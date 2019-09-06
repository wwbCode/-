package com.jinyafu.thirdpart.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数来源定义
 * @author XiaHui
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Define {

    String value() default "";

    String type() default "json";

    public static final String TYPE_JSON = "json";
    public static final String TYPE_BEAN = "bean";
}
