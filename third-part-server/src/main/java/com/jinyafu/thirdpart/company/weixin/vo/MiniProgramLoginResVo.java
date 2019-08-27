/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: MiniProgramLoginResponse.java@author: lean.yang@date: 19-6-20 上午9:36@version: 1.0
 */

package com.jinyafu.thirdpart.company.weixin.vo;

import lombok.Data;

/**
 * @author 000062064
 * @title: MiniProgramLoginResponse
 * @projectName jmall_ms
 * @description: 小程序登录接口返回
 * @date 2019/5/20 14:54
 */
@Data
public class MiniProgramLoginResVo extends BaseResVo {
    private String openid; //  	用户唯一标识
    private String session_key; // 	会话密钥
    private String unionid; // 	用户在开放平台的唯一标识符
    private String username;
    private String encryptedData;
    private String iv;
}
