package com.jinyafu.jmall.entity.third.billinginformation;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/5 9:20
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: third-part-server-Billinginformation
 * @description: 开票信息票
 * @author: Mr.Wwb
 * @create: 2019-09-05 09:20
 **/
@Data
public class Billinginformation {

    //#录入开票信息后，自动将“系统”字段的“最新开票 信息”字段修改为“已确认”#
    /** id*/
    private String id;
    /** 系统*/
    private String SystemId;
    /** 公司*/
    private String company;
    /**金额*/
    private String amount;
    /** 类型*/
    private String type;
    /** 驱动 （自动，找客户经理）*/
    private String drive;
    /** 接受渠道邮寄 邮箱*/
    private String receivingChannels;
    /** 接受日*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date receivingDay;
    /** 账期 （年月）*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentDays;
    /** 转交人*/
    private String passedToPeople;
    /** 备注*/
    private String note;
    /** 删除状态 0 删除 1*/
    private Integer isDelete;

}
