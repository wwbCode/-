package com.jinyafu.thirdpart.controller;

import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Out;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.pojo.sun.EmailInfoReq;
import com.jinyafu.thirdpart.config.EmailConfig;
import com.jinyafu.thirdpart.handler.HandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 邮件发送
 * @CreateDate: 2019/8/1 9:45
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
@RequestMapping("/email/sendEmail")
@Slf4j
public class MailController extends BaseController {

    @Autowired
    private EmailConfig emailConfig;

    /**
     * @description: 发送发票邮件
     * @date: 2019/8/1 18:37
     * @author: mjt
     * @param:  [ticketInfoReq]
     * @return: com.jinyafu.thirdpart.common.code.Out
     */
    @PostMapping("/sendTicketMail")
    public Out sendTicketMail(@RequestBody EmailInfoReq emailInfoReq){
        String html ="<html><body>"
                +"<div>"
                +"<p>"+"标题：您的金雅福订单【"+emailInfoReq.getOrderNumber()+"】电子发票已开具"+"</p>"
                +"<p>"+"内容：尊敬的"+ emailInfoReq.getNickname()+"您好："+"</p>"
                +"<p>"+"感谢您在金雅福 ("+ emailInfoReq.getNetUrl()+" ) 购物!"+"</p>"
                +"<p>"+"金雅福已经为您开具订单"+emailInfoReq.getOrderNumber()+"的电子普通发票，发票数量共计"+emailInfoReq.getNum()+"张，如下:"+"</p>"
                +"<p>"+"，您可以点击“"+ emailInfoReq.getTicketUrl() +"”获取该发票文件；"+"</p>"
                +"</div>"
                +"</body></html>";
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendTemplateMail(emailConfig.getUsername(), emailInfoReq.getUserEmailAddress(),"发票邮件", html);
        return MessageOutput.get(OutputCode.SEND_EMAIL_SUCCESS.getCode(),OutputCode.SEND_EMAIL_SUCCESS.getMessage());
    }
}
