package com.jinyafu.thirdpart.controller.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/12 11:48
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.third.service.SslCertificate;
import com.jinyafu.thirdpart.service.service.SslCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: third-part-server-SslCertificateController
 * @description: ssl证书接口
 * @author: Mr.Wwb
 * @create: 2019-09-12 11:48
 **/
@Controller
@RequestMapping("/third/service")
public class SslCertificateController {
    @Autowired
    private SslCertificateService sslCertificateService;

    /**
     * @description: 方法描述
     * @date: 2019/9/12 11:53
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/sslCertificate/listAll")
    public ResponseDTO listAll(@RequestBody SslCertificate sslCertificate){
        return sslCertificateService.listAll(sslCertificate);
    }
    /**
     * @description: 添加或者修改
     * @date: 2019/9/12 11:53
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/sslCertificate/addOrUpdate")
    public ResponseDTO addOrUpdate (@RequestBody SslCertificate sslCertificate){
        return sslCertificateService.addOrUpdate(sslCertificate);
    }
    /**
     * @description: 删除
     * @date: 2019/9/12 11:53
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/sslCertificate/delete")
    public ResponseDTO delete(@RequestBody SslCertificate sslCertificate){
        return sslCertificateService.delete(sslCertificate);
    }
    /**
     * @description: getById
     * @date: 2019/9/12 11:53
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/sslCertificate/getById")
    public ResponseDTO getById(@RequestBody SslCertificate sslCertificate){
        return  sslCertificateService.getById(sslCertificate);
    }
    /**
     * @description: 多条件查询
     * @date: 2019/9/12 11:53
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping("/sslCertificate/getByConditions")
    public ResponseDTO getByConditions(@RequestBody SslCertificate sslCertificate){
        return  sslCertificateService.getByConditions(sslCertificate);
    }

}
