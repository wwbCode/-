package com.jinyafu.thirdpart.company.weixin;

import com.alibaba.fastjson.JSON;
import com.jinyafu.thirdpart.common.enums.ThirdTypeEnum;
import com.jinyafu.thirdpart.company.weixin.constants.ApiOperateType;
import com.jinyafu.thirdpart.company.weixin.constants.WeixinPublicConstants;
import com.jinyafu.thirdpart.company.weixin.vo.WxGetTokenReqVo;
import com.jinyafu.thirdpart.company.weixin.vo.WxGetTokenResVo;
import com.jinyafu.thirdpart.handler.AbsHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

import static com.jinyafu.thirdpart.company.weixin.constants.ApiOperateType.WX_PUBLIC_TOKEN;

/**
 * @author lean.yang
 * @title: WeChatPublicApiHandler
 * @projectName ThirdPartPlatform_ms
 * @description: 微信公众号接口
 * @date 2019/8/27
 */
@Slf4j
public class WeChatPublicApiHandler extends AbsHandlerAdapter {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean supports(Object handler) {
        return ThirdTypeEnum.API_WEIXIN_PUBLIC.getThirdType().equals(handler);
    }

    @Override
    public Object handle(Object type, Object reqDTO) throws Exception {
        switch ((ApiOperateType) type) {
            case WX_PUBLIC_TOKEN:
                return getAccessToken((WxGetTokenReqVo) reqDTO);
        }
        return null;
    }

    /**
     * @return com.jinyafu.thirdpart.company.weixin.vo.WxGetTokenResVo
     * @throws
     * @description: access_token获取
     * @params [wxGetTokenReqVo]
     * @author lean.yang
     * @date 2019/8/27
     */
    public WxGetTokenResVo getAccessToken(WxGetTokenReqVo wxGetTokenReqVo) throws Exception {
        String getUrl = WeixinPublicConstants.PROTOCOL_NAME + WeixinPublicConstants.DOMAIN_API + WeixinPublicConstants.GET_TOKEN;
        getUrl = getUrl + "?grant_type=client_credential" + "&appid=" + wxGetTokenReqVo.getAppid() + "&secret=" + wxGetTokenReqVo.getSecret();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getUrl, String.class);
        log.info("access_token获取响应：{}", responseEntity.getBody());
        WxGetTokenResVo wxGetTokenResVo = JSON.parseObject(responseEntity.getBody(), WxGetTokenResVo.class);
        return wxGetTokenResVo;
    }
}
