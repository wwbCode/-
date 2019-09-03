/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: MiniProgramLoginResponse.java@author: lean.yang@date: 19-6-20 上午9:36@version: 1.0
 */

package com.jinyafu.thirdpart.company.weixin.vo;

import lombok.Data;

/**
 * @author 000062064
 * @title: MiniProgramLoginResponse
 * @projectName jmall_ms
 * @description: 小程序登录接口请求
 * @date 2019/5/20 14:54
 */
@Data
public class MiniProgramLoginReqVo {
    String code; // jscode
    private String appid;
    private String secret;
    private String grantType = "authorization_code";
}
