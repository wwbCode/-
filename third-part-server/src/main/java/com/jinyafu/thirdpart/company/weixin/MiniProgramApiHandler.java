package com.jinyafu.thirdpart.company.weixin;

import com.alibaba.fastjson.JSON;
import com.jinyafu.thirdpart.common.enums.ThirdTypeEnum;
import com.jinyafu.thirdpart.company.weixin.constants.ApiOperateType;
import com.jinyafu.thirdpart.company.weixin.constants.WeixinMiniConstants;
import com.jinyafu.thirdpart.company.weixin.vo.MiniProgramLoginReqVo;
import com.jinyafu.thirdpart.company.weixin.vo.MiniProgramLoginResVo;
import com.jinyafu.thirdpart.handler.AbsHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @author lean.yang
 * @title: MiniProgramApiHandler
 * @projectName ThirdPartPlatform_ms
 * @description: 微信小程序接口处理类
 * @date 2019/8/27
 */
@Slf4j
public class MiniProgramApiHandler extends AbsHandlerAdapter {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean supports(Object handler) {
        return ThirdTypeEnum.API_WEIXIN_MINI.getThirdType().equals(handler);
    }

    @Override
    public Object handle(Object type, Object reqDTO) throws Exception {
        switch ((ApiOperateType) type) {
            case WX_MINI_CODE2SESSION:
                return code2Session((MiniProgramLoginReqVo) reqDTO);
            /*case WX_ACODE_UNLIMIT:
                return getWxAcodeUnlimit(() reqDTO);*/
        }
        return null;
    }

    /**
     * @return com.jinyafu.thirdpart.company.weixin.vo.MiniProgramLoginResVo
     * @throws
     * @description: 小程序登录接口
     * @params [miniProgramLoginReqVo]
     * @author lean.yang
     * @date 2019/8/27
     */
    public MiniProgramLoginResVo code2Session(MiniProgramLoginReqVo miniProgramLoginReqVo)
            throws Exception {
        String urlString = "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}";
        String response = restTemplate.getForObject(
                WeixinMiniConstants.PROTOCOL_NAME + WeixinMiniConstants.DOMAIN_API + WeixinMiniConstants.WX_LOGIN_URL + urlString, String.class,
                miniProgramLoginReqVo.getAppid(),
                miniProgramLoginReqVo.getSecret(),
                miniProgramLoginReqVo.getCode(),
                miniProgramLoginReqVo.getGrantType());
        log.info("小程序登录code2Session接口返回：{}", response);
        if (null == response) {
            throw new Exception("调用小程序登录接口失败");
        }
        MiniProgramLoginResVo miniProgramLoginResponse = JSON.parseObject(response, MiniProgramLoginResVo.class);
        return miniProgramLoginResponse;
    }


}
