package com.jinyafu.thirdpart.company.weixin.vo;

import lombok.Data;

/**
 * @author lean.yang
 * @title: WxGetTokenReqVo
 * @projectName ThirdPartPlatform_ms
 * @description: 获取微信公众号token响应实体
 * @date 2019/8/27
 */
@Data
public class WxGetTokenResVo extends BaseResVo {
    private String accessToken; // 令牌
    private long expiresIn; // 时效，秒
}
