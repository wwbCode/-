package com.jinyafu.jmall.entity.data.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinyafu.jmall.common.dto.RequestDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/9/6 11:04
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class ServicesDTO extends RequestDTO{

    private static final long serialVersionUID = 1L;

    private String name; //服务名
    private String superId; //父服务id
    private String supplierId; //供应商Id
    private String supplierName; //供应商名字
    private String user; //使用者
    private String operator; //服务经办人
    private int status; //服务状态
    private int type;    //类型
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime; //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;  //结束时间
    private Integer isDelete;

}
