package com.jinyafu.jmall.entity.third.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 14:18
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: third-part-server-Services
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 14:18
 **/
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "service")
@Data
public class Services {

    @TableId("id")
    private  String id; //主键id

    @TableField("name")
    private String name; //服务名称

    @TableField("superId")
    private String superId; //父服务id

    @TableField("supplierId")
    private String supplierId; //供应商Id

    @TableField("user")
    private String user; //使用者

    @TableField("operator")
    private String operator; //经办人

    @TableField("status")
    private int status; //服务状态

    @TableField("type")
    private int type; //服务类型

    @TableField("startTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private  Date startTime; //服务开始使用时间

    @TableField("endTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime; //服务截至时间

    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime; //创建时间

    @TableField("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime; //更新时间

    @TableField("isDelete")
    private Integer isDelete; //是否被删除

    @Override
    public String toString() {
        return "Services{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", superId='" + superId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", user='" + user + '\'' +
                ", operator='" + operator + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
