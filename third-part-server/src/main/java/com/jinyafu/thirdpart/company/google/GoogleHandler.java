/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: GoogleHandler.java@author: lean.yang@date: 19-7-31 下午4:12@version: 1.0
 */

package com.jinyafu.thirdpart.company.google;

import com.jinyafu.thirdpart.handler.HandlerAdapter;

/**
 * @author lean.yang
 * @title: GoogleHandler
 * @projectName third-part-server
 * @description: TODO
 * @date 2019/7/31
 */
public class GoogleHandler implements HandlerAdapter {
    @Override
    public Object handleDemo(Object reqDTO) {
        return null;
    }

    @Override
    public boolean supports(Object handler) {
        return false;
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
}
