/*
 *
 *  *  * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 *  *  * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *  *  * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 *
 */

package com.jinyafu.thirdpart.handler;

public abstract class AbsHandlerAdapter implements HandlerAdapter {
    /**
     * @param reqDTO
     * @return java.lang.Object
     * @throws
     * @description: 业务处理接口demo
     * @params [reqDTO]
     * @author lean.yang
     * @date 2019/7/11
     */
    @Override
    public Object handleDemo(Object reqDTO) {
        return null;
    }

    /**
     * 发送文本邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleMail(String from, String to, String subject, String content) {

    }

    /**
     * 发送模板邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendTemplateMail(String from, String to, String subject, String content) {

    }

    /**
     * 发送附件邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    @Override
    public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath) {

    }

    /**
     * 发送内嵌图片
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param imgPath
     * @param imgId
     */
    @Override
    public void sendInlineResourceMail(String from, String to, String subject, String content, String imgPath, String imgId) {

    }
    
    /**
     * 发送验证码短信
     * 
     * @version 2019年8月6日下午3:48:27
     * @author Ly
     * @param payType
     * @param mobile
     * @return
     */
    @Override
    public Object sendMessage(String payType, String mobile) {
        return this.sendMessage(payType, mobile);
    }
    
    /**
     * 验证短信验证码
     * 
     * @version 2019年8月6日下午4:49:05
     * @author Ly
     * @param mobile
     * @param verifyCode
     * @return
     */
    @Override
    public Object verifyMessage(String mobile, String verifyCode) {
        return this.verifyMessage(mobile, verifyCode);
    }
}
