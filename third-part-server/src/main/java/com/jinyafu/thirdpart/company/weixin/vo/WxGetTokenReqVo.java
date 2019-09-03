package com.jinyafu.thirdpart.company.weixin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lean.yang
 * @title: WxGetTokenReqVo
 * @projectName ThirdPartPlatform_ms
 * @description: 获取微信公众号token请求实体
 * @date 2019/8/27
 */
@Data
public class WxGetTokenReqVo implements Serializable {
    private String grantType = "client_credential";
    private String appid;
    private String secret;
}
