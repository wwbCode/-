package com.jinyafu.jmall.entity.third.fourhundred;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 9:47
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: third-part-server-Fourhundred
 * @description: 400字段
 * @author: Mr.Wwb
 * @create: 2019-09-04 09:47
 **/
@Data
public class Fourhundred {
    /** */
    private String id;
    /** 号码*/
    private String tel;
    /** 运营商*/
    private String operator;
    /** 服务商*/
    private String serviceProviders;
    /**开通时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date OpeningTime;
    /** 到期时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /** 费用*/
    private String cost;
    /** 分钟数*/
    private String minutes ;
    /**绑定固话列表 */
    private String bondList;
    /** 管理邮箱*/
    private String emil;
    /** 管理手机号*/
    private String mobile;
    /** 管理地址*/
    private String address;
    /** 管理账号*/
    private String acc;
    /** 管理密码*/
    private String password;
    /** 增值服务*/
    private String addService;
    /** 经办人*/
    private String agent;
    /** 付款方式（现金 ， 托收，公对公）*/
    private String paymentMethods;
    /** 托手开户行名*/
    private String collectionBank;
    /** 托收开户行账号*/
    private String collectionBankAcc;
    /** 公司名字*/
    private String company;
    /** 营业执照编号*/
    private String businessLicense;
    /** 业务状态*/
    private Integer status;
    /**最新用途*/
    private String latestUse;

}
