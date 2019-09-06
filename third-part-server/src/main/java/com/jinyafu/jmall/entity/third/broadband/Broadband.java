package com.jinyafu.jmall.entity.third.broadband;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 19:11
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
 * @program: third-part-server-Broadband
 * @description: 宽带
 * @author: Mr.Wwb
 * @create: 2019-09-04 19:11
 **/
@Data
public class Broadband extends RequestDTO {
    /**id*/
    private String id;
    /** 号码*/
    private String tel;
    /** 运营商（电信、联通、移动）*/
    private String operator;
    /** 系统（如：201808李朗联通业务）*/
    private String system;
    /**计费方式（合同周期、按月） */
    private String chargeMode;
    /** 月费*/
    private String monthlyFee;
    /** 开通时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date openTime;
    /** 到期时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expirationTime;
    /** 费用*/
    private String fee;
    /** 安装地址*/
    private String address;
    /**经办人*/
    private String agent;
    /** 客户经理*/
    private String manager;
    /** 客户经理联系方式*/
    private String managerTel;
    /** 付款方式*/
    private String paymentMethod;
    /** 托收开户行名*/
    private String collectionBank;
    /** 托收开户行账号*/
    private String collectionbankAcc;
    /**公司名 */
    private String company;
    /** 营业执照编号*/
    private String businessLicense;
    /**业务状态（正常、已注销） */
    private Integer status;
    /**最新用途 */
    private String latestUse;
    /** 最新开票状态（已确认、待确认）*///#录入开票信息后，自动将“系统”字段的“最新开票信息”字段修改为“已确认”#

    private String latestBillingStatus;
    /** 删除标志 0 删除 1 未删除*/
    private Integer isDelete;
    /** 是否为最新账单（已确认、待确认）*/
    private String isLatestBill;

}
