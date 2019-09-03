package com.jinyafu.thirdpart.controller;

import com.jinyafu.thirdpart.common.enums.ThirdTypeEnum;
import com.jinyafu.thirdpart.company.weixin.constants.ApiOperateType;
import com.jinyafu.thirdpart.company.weixin.vo.*;
import com.jinyafu.thirdpart.handler.HandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author lean.yang
 * @title: WeixinApiController
 * @projectName ThirdPartPlatform_ms
 * @description: 微信接口控制层
 * @date 2019/8/27
 */
@Slf4j
@RestController
@RequestMapping("/third/weixin")
public class WeixinApiController extends BaseController {

    /**
     * @return com.jinyafu.thirdpart.company.weixin.vo.MiniProgramLoginResVo
     * @throws
     * @description: 获取小程序登录会话接口
     * @params [miniProgramLoginReqVo]
     * @author lean.yang
     * @date 2019/8/27
     */
    @PostMapping("/mini/code2Session")
    public MiniProgramLoginResVo code2Session(@RequestBody MiniProgramLoginReqVo miniProgramLoginReqVo) {
        try {
            HandlerAdapter handlerAdapter = getHandler(ThirdTypeEnum.API_WEIXIN_MINI.getThirdType());
            MiniProgramLoginResVo miniProgramLoginResVo = (MiniProgramLoginResVo) handlerAdapter.handle(ApiOperateType.WX_MINI_CODE2SESSION, miniProgramLoginReqVo);
            return miniProgramLoginResVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return com.jinyafu.thirdpart.company.weixin.vo.WxGetTokenResVo
     * @throws
     * @description: 获取微信公众号access_token或者微信小程序access_token
     * @params [wxGetTokenReqVo]
     * @author lean.yang
     * @date 2019/8/27
     */
    @PostMapping("/public/getAccessToken")
    public WxGetTokenResVo getAccessToken(@RequestBody WxGetTokenReqVo wxGetTokenReqVo) {
        try {
            HandlerAdapter handlerAdapter = getHandler(ThirdTypeEnum.API_WEIXIN_PUBLIC.getThirdType());
            WxGetTokenResVo wxGetTokenResVo = (WxGetTokenResVo) handlerAdapter.handle(ApiOperateType.WX_PUBLIC_TOKEN, wxGetTokenReqVo);
            return wxGetTokenResVo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*@PostMapping("/getWxAcodeUrl")
    public WxGetAcodeResVo getWxAcodeUrl(){

    }*/


}
