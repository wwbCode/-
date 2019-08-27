package com.jinyafu.thirdpart.company.weixin.constants;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lean.yang
 * @title: ApiOperateType
 * @projectName ThirdPartPlatform_ms
 * @description: 微信接口枚举
 * @date 2019/8/27
 */
public enum ApiOperateType {
    WX_PUBLIC_TOKEN("TOKEN", "公众号token接口"),
    WX_MINI_CODE2SESSION("CODE2SESSION", "小程序登录会话接口");

    ApiOperateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Setter
    @Getter
    public String code;
    @Setter
    @Getter
    public String desc;
}
