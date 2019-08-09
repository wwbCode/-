/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.jinyafu.thirdpart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinyafu.thirdpart.company.obs.ObsHandler;

/**
 * TODO:类功能介绍
 * @version 2019年8月8日下午3:37:42
 * @author Ly
 */
@RestController
@RequestMapping("/third/obs")
public class ObsController {
    @Autowired
    ObsHandler obsHandler;
    
    /**
     * 单文件上传
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value ="/upload")
    public Object upload(HttpServletRequest request,@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        return  obsHandler.uploadByIO(request,file, userId);
    }
    
}
