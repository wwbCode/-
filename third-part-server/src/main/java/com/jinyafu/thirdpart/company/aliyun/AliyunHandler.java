/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.jinyafu.thirdpart.company.aliyun;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.consts.CommonConsts;
import com.jinyafu.thirdpart.common.enums.ThirdTypeEnum;
import com.jinyafu.thirdpart.common.util.aliyun.AuthSmsManager;
import com.jinyafu.thirdpart.common.util.aliyun.SmsManager;
import com.jinyafu.thirdpart.common.util.aliyun.VerifyManager;
import com.jinyafu.thirdpart.common.util.common.NumberUtil;
import com.jinyafu.thirdpart.handler.AbsHandlerAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 阿里服务
 * @version 2019年8月6日上午11:54:59
 * @author Ly
 */
@Slf4j
public class AliyunHandler extends AbsHandlerAdapter{
    
    @Resource
    VerifyManager verifyManager;
    @Resource
    SmsManager smsManager;
    @Resource
    AuthSmsManager authSmsManager;
    
    @Override
    public boolean supports(Object handler) {
        return ThirdTypeEnum.SMS_ALIYUN.getThirdType().equals(handler);
    }
    
    @Override
    public Object sendMessage(String payType, String mobile) {
        log.info(">>>>>>>>>>>>>>>>调用阿里短信服务开始，入参:[{}]", payType+mobile);
        try {
            String verifyCode = NumberUtil.getRandom(6);//生成随机验证码
            //发送短信
            Map<String, String> param = new HashMap<String, String>(2);
            param.put("code", verifyCode);   
            log.info(">>>>>>>>>>>>>>>>获取随机验证码:[{}]", verifyCode);
            if(smsManager.send(CommonConsts.SMS_REGISTER_VERIFYCODE, mobile, param)) {
                authSmsManager.putSmsCode(mobile, verifyCode, 3*60);//保存到缓存
            } else {
                return  MessageOutput.get(OutputCode.MESSAGE_SEND_FAIL.getCode(), OutputCode.MESSAGE_SEND_FAIL.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  MessageOutput.get(OutputCode.EX.getCode(), OutputCode.EX.getMessage());
        }
        return MessageOutput.get(OutputCode.OK.getCode(), OutputCode.OK.getMessage());
    }

    @Override
    public Object verifyMessage(String mobile, String verifyCode) {
        int rsValid = authSmsManager.isValid(mobile, verifyCode);
        if (rsValid == -1) {
            return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }
        else if (rsValid == -2) {
            return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }
        return MessageOutput.get(OutputCode.OK.getCode(), OutputCode.OK.getMessage());
    }
}
