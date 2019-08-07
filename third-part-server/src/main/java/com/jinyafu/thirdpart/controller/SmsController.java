package com.jinyafu.thirdpart.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Out;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.util.aliyun.AuthSmsManager;
import com.jinyafu.thirdpart.common.util.aliyun.SmsManager;
import com.jinyafu.thirdpart.common.util.aliyun.VerifyManager;
import com.jinyafu.thirdpart.handler.HandlerAdapter;

/**
 * 第三方短信服务发送
 * @version 2019年8月6日下午2:42:06
 * @author Ly
 */
@RestController
@RequestMapping("/third/sendSms")
public class SmsController extends BaseController {
	protected final Log logger = LogFactory.getLog(getClass());

	@Resource
	VerifyManager verifyManager;
	@Resource
	SmsManager smsManager;
	@Resource
	AuthSmsManager authSmsManager;
	
	/**
	 * 发送短信验证码
	 * @version 2019年7月18日下午4:30:06
	 * @author Ly
	 * @param request
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@PostMapping("{payType}")
	public Out sendVerifyCode(@PathVariable(name = "payType") String payType, HttpServletRequest request,  
	        @RequestBody String mobile) {
		if(mobile==null || mobile.isEmpty()) {
		    return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(),OutputCode.PARAMS_INVALID_EMPTY.getMessage());
		}	
		HandlerAdapter handlerAdapter=getHandler(payType);
        handlerAdapter.sendMessage(payType, mobile);
		return MessageOutput.get(OutputCode.OK.getCode(), OutputCode.OK.getMessage());
	}
	
	/**
	 * 判断验证码是否正确
	 * @version 2019年7月18日下午5:37:43
	 * @author Ly
	 * @param request
	 * @param mobile
	 * @return
	 */
	@ResponseBody
    @PostMapping("/verify/{payType}")
    public Out verify(
            HttpServletRequest request,  
            @PathVariable(name = "payType") String payType, 
            @RequestBody String mobile,
            @RequestBody String verifyCode) {
        if (mobile == null || verifyCode == null) {
            return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }      
        HandlerAdapter handlerAdapter=getHandler(payType);
        handlerAdapter.verifyMessage(mobile, verifyCode);
        return MessageOutput.get(OutputCode.OK.getCode(), OutputCode.OK.getMessage());
    }
}