package com.jinyafu.jmall.entity.third.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 19:07
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: third-part-server-Supplier
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-29 19:07
 **/
@Data
public class Supplier {
    private String id;
    /**公司名*/
    private String name;
    /**地址信息*/
    private String address;
    /**联系方式*/
    private String tel;
    /**备注*/
    private String remark;
    /**对接人*/
    private String operator;
    /**使用人*/
    private String user;
    /**使用开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    /**合作状态*/
    private Integer status;
    /**是否删除*/
    private Integer isDelete;
}

