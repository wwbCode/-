package com.jinyafu.jmall.entity.third.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/11 9:35
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinyafu.jmall.common.dto.RequestDTO;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @program: third-part-server-Monitor
 * @description: 监控实体
 * @author: Mr.Wwb
 * @create: 2019-09-11 09:35
 **/
@Data
public class Monitor extends RequestDTO{
    /** id */
    private String id ;
    /**  归属公司*/
    private String company ;
    /**  场地简称*/
    private String  place;
    /** 地址 */
    private String placeFullName ;
    /** 负责人姓名 */
    private String leader ;
    /**  联系方式*/
    private String  tel;
    /**  型号*/
    private String  monitorType;
    /**  登录ip*/
    private String loginIp ;
    /**  存储空间*/
    private String  memory;
    /**  标准存储天数*/
    private String  storageDays;
    /**  账号*/
    private String  acc;
    /**  密码*/
    private String  password;
    /**  摄像头ip*/
    private String cameraIp ;
    /** 密码 */
    private String  cameraPassword;
    /**  摄像头型号*/
    private String  cameraType;
    /**  数量*/
    private String cameraNum ;
    /**  供应商*/
    private String purchasingSupplier ;
    /**  供应商tel*/
    private String  purchasingTel;
    /**  到货日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String deliveryDate ;
    /**  保修时长*/
    private String  warrantyPeriod;
    /**  实施供应商*/
    private String  implementationSupplier;
    /**  实施tel*/
    private String  implementationTel;
    /**安装人员tel*/
    private String  installTel;
    /**  保修时长*/
    private String  implementationWarrantyPeriod;
    /**  服务id*/
    private String  serviceId ;
    /**  是否删除*/
    private Integer  isDelete;
    /**  服务类型*/
    private Integer  serviceType;

}
