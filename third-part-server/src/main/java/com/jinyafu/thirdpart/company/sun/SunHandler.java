/*
 * Copyright (c) 2019-2029 深圳金雅福控股集团有限公司 All Rights Reserved.FileName: GoogleHandler.java@author: lean.yang@date: 19-7-31 下午4:12@version: 1.0
 */

package com.jinyafu.thirdpart.company.sun;

import com.jinyafu.thirdpart.common.enums.*;
import com.jinyafu.thirdpart.handler.HandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Description: sun公司，邮件发送处理实现类
 * @CreateDate: 2019/8/1 14:47
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
@Slf4j
public class SunHandler implements HandlerAdapter {
    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public Object handleDemo(Object reqDTO) {
        return null;
    }

    @Override
    public boolean supports(Object handler) {
        return ThirdTypeEnum.EAMIL_SUN.getThirdType().equals(handler);
    }

    /**
     * 发送文本邮件
     *
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String from, String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("simple mail had send。");
        } catch (Exception e) {
            log.error("send mail error", e);
        }
    }

    /**
     * 发送模板邮件
     * @param from
     * @param to
     * @param subject
     * @param content
     */
    public void sendTemplateMail(String from, String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("send template success");
        } catch (Exception e) {
            log.error("send template eror", e);
        }
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
    public void sendAttachmentsMail(String from, String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            log.info("send mail with attach success。");
        } catch (Exception e) {
            log.error("send mail with attach success", e);
        }
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
    public void sendInlineResourceMail(String from, String to, String subject, String content, String imgPath, String imgId) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource res = new FileSystemResource(new File(imgPath));
            helper.addInline(imgId, res);
            mailSender.send(message);
            log.info("send inner resources success。");
        } catch (Exception e) {
            log.error("send inner resources fail", e);
        }
    }
}
