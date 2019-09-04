package com.jinyafu.jmall.entity.third.serve;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 14:18
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: third-part-server-Serve
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 14:18
 **/
@Data
public class Serve {
    /***/
    private  String id;
    /***/
    private String name;
    /***/
    private String gid;
    /***/
    private String pid;
    /***/
    private String user;
    /***/
    private String operator;
    /**业务状态*/
    private int status;
    /***/
    private int type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private  Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /*是否被删除*/
    private Integer isDelete;

}
