package com.jinyafu.jmall.entity.data.service;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/9/10 19:13
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class ServicesQuery {

    private String name; //服务名称
    private String superId; //父服务id
    private String supplierId; //供应商Id
    private String user; //使用者
    private String operator; //经办人
    private int status; //服务状态
    private int type; //服务类型
    private Date startTime; //服务开始使用时间
    private Date endTime; //服务截至时间
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

}
