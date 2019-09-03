/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: thirdTypeEnum.java@author: lean.yang@date: 19-7-11 下午3:47@version: 1.0
 */

package com.jinyafu.thirdpart.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
* @Description: 第三方类型枚举类
* @CreateDate: 2019/8/1 15:12
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
public enum ThirdTypeEnum {

    /**邮件第三方*/
    EAMIL_SUN(1, "email", "sun邮件第三方"),
    /**短信第三方*/
    SMS_ALIYUN(2, "aliyun", "阿里云短信服务"),
    SMS_MICROSOFT(2, "microsoft", "微软短信服务"),
    SMS_JIGUANG(2, "jiguang", "极光短信服务"),
    API_WEIXIN_MINI(5, "weixinMini", "微信小程序接口"),
    API_WEIXIN_PUBLIC(6, "weixinPublic", "微信公众号接口")
    ;

    ThirdTypeEnum() {
    };

    ThirdTypeEnum(Integer thirdTypeNum, String thirdType, String thirdTypeName) {
        this.thirdTypeNum = thirdTypeNum;
        this.thirdType = thirdType;
        this.thirdTypeName = thirdTypeName;
    }

    /**类型*/
    @Setter
    @Getter
    private Integer thirdTypeNum;
    /**路劲*/
    @Setter
    @Getter
    private String thirdType;
    /**名称*/
    @Setter
    @Getter
    private String thirdTypeName;

    public static ThirdTypeEnum getByNum(Integer thirdTypeNum) {
        for (ThirdTypeEnum thirdTypeEnum : ThirdTypeEnum.values())
            if (thirdTypeEnum.getThirdTypeNum().equals(thirdTypeNum))
                return thirdTypeEnum;
        return null;
    }
}
