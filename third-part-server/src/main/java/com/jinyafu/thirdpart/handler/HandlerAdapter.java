/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: PayHandlerAdapter.java@author: lean.yang@date: 19-7-31 下午4:00@version: 1.0
 */

package com.jinyafu.thirdpart.handler;


/**
 * @author lean.yang
 * @title: HandlerAdapter
 * @projectName jmall_ms
 * @description: 处理适配器
 * @date 2019/7/11
 */
public interface HandlerAdapter {
    /**
     * @return java.lang.Object
     * @throws
     * @description: 业务处理接口
     * @params [reqDTO]
     * @author lean.yang
     * @date 2019/7/11
     */
    Object handle(Object reqDTO);

    /**
     * @description: 业务处理接口
     * @params [type, reqDTO]
     * @return java.lang.Object
     * @throws Exception
     * @author lean.yang
     * @date 2019/8/27
     */
    Object handle(Object type, Object reqDTO) throws Exception;

    /**
     * @return boolean
     * @throws
     * @description: 是否支持
     * @params [handler]
     * @author lean.yang
     * @date 2019/7/11
     */
    boolean supports(Object handler);

    /**
     * 发送文本邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String from, String to, String subject, String content);

    /**
     * 发送模板邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    void sendTemplateMail(String from, String to, String subject, String content);

    /**
     * 发送附件邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String from, String to, String subject, String content, String filePath);

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
    void sendInlineResourceMail(String from, String to, String subject, String content, String imgPath, String imgId);
    
    /**
     * 发送验证码短信
     * 
     * @version 2019年8月6日下午3:48:27
     * @author Ly
     * @param payType
     * @param mobile
     * @return
     */
    Object sendMessage(String payType, String mobile);
    
    /**
     * 验证短信验证码
     * 
     * @version 2019年8月6日下午4:49:05
     * @author Ly
     * @param mobile
     * @param verifyCode
     * @return
     */
    Object verifyMessage(String mobile, String verifyCode);
}
