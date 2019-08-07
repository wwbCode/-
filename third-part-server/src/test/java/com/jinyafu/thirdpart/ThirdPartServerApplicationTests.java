package com.jinyafu.thirdpart;

import com.jinyafu.thirdpart.controller.BaseController;
import com.jinyafu.thirdpart.handler.HandlerAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdPartServerApplicationTests extends BaseController{

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendSimpleMailTest() {
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendSimpleMail("2016402773@qq.com", "11111@163.com",
                "qq发邮件测试", "sendSimpleMailTestfrom 测试邮件");

    }

    @Test
    public void sendTemplateMailTest(String orderNumber,String nickname,String netUrl,Integer num,String ticketUrl) {
        String html ="<html><body>"
                +"<div>"
                +"<p>"+"标题：您的金雅福订单【"+orderNumber+"】电子发票已开具"+"</p>"
                +"<p>"+"内容：尊敬的"+ nickname+"您好："+"</p>"
                +"<p>"+"感谢您在金雅福 ("+ netUrl+" ) 购物!"+"</p>"
                +"<p>"+"金雅福已经为您开具订单"+orderNumber+"的电子普通发票，发票数量共计"+num+"张，如下:"+"</p>"
                +"<p>"+"，您可以点击“"+ ticketUrl +"”获取该发票文件；"+"</p>"
                +"</div>"
                +"</body></html>";
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendTemplateMail("2016402773@qq.com", "mutongzijt@163.com",
                "发票邮件", html);
    }

    @Test
    public void sendAttachmentsMailTest() {
        String filePath = "D:\\springcloudws\\sc-mail\\src\\main\\java\\sc\\mail\\service\\impl\\MailServiceImpl.java";
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendAttachmentsMail("2016402773@qq.com", "mutongzijt@163.com",
                "sendAttachmentsMailTest", "sendAttachmentsMailTestfrom 2016402773@qq.com", filePath);
    }

    @Test
    public void sendInlineResourceMailTest() {
        String imgId = "img1";
        String content = "<html><body>"
                + "sendInlineResourceMailTest：<img src=\'cid:" + imgId + "\'>"
                + "</body></html>";
        String imgPath = "D:\\springcloudws\\sc-mail\\src\\main\\resources\\20181015223228.jpg";
        HandlerAdapter handlerAdapter=getHandler("email");
        handlerAdapter.sendInlineResourceMail("2016402773@qq.com", "mutongzijt@163.com",
                "sendAttachmentsMailTest", content, imgPath, imgId);
    }

}
