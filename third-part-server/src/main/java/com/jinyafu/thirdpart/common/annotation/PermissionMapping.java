package com.jinyafu.thirdpart.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * date 2018-07-04 15:28:04<br>
 * description
 *
 * @author XiaHui<br>
 * @since
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionMapping {

    String name();

    String key() default "";

    String superKey() default "0";

    boolean isIntercept() default true;

    Type type() default Type.function;

    enum Type {
        /**
         * 菜单类型（用户登录后在页面显示的导航菜单）
         */
        menu(1),
        /*
         * 功能类型（在进入业务页面时的保存、新增、删除等操作）
         */
        function(2);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}

