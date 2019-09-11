package com.jinyafu.thirdpart.controller;

import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Out;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.pojo.sun.EmailInfoReq;
import com.jinyafu.thirdpart.handler.HandlerAdapter;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 邮件发送
 * @CreateDate: 2019/8/1 9:45
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
@RequestMapping("/third/email/sendEmail")
@RestController
public class MailController extends BaseController {

    /*@
    private String*/

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
                +"<p>"+"，您可以点击 <a href='"+ emailInfoReq.getInvoiceUrl() +"' target='_blank'>发票地址</a> 获取该发票文件；"+"</p>"
                +"</div>"
                +"</body></html>";
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendTemplateMail(emailInfoReq.getPlatformEmailAddress(), emailInfoReq.getUserEmailAddress(),"发票邮件", html);
        return MessageOutput.get(OutputCode.SEND_EMAIL_SUCCESS.getCode(),OutputCode.SEND_EMAIL_SUCCESS.getMessage());
    }
}