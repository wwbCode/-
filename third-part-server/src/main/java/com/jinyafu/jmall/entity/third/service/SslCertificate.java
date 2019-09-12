package com.jinyafu.jmall.entity.third.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/12 11:41
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinyafu.jmall.common.dto.RequestDTO;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: third-part-server-sslCertificate
 * @description: ssl证书
 * @author: Mr.Wwb
 * @create: 2019-09-12 11:41
 **/
@Data
public class SslCertificate extends RequestDTO {
    /** id */
    private String  id;
    /** 服务id */
    private String  serviceId;
    /**  供应商id*/
    private String  supplierId;
    /**  域名*/
    private String  dominName;
    /**  所属公司*/
    private String  company;
    /**  用途*/
    private String  purpose;
    /**  到期时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expirationTime;
    /**  证书品牌*/
    private String  certificateBand;
    /**  备注*/
    private String  remark;
    /**  是否删除* 0 删除 1不删除/
     *
     */
    private Integer  isDelete;
    /**  服务类型*/
    private Integer  serviceType;

    private String serviceName; //供应商名字


}
