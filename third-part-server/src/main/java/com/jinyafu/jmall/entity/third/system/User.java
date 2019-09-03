package com.jinyafu.jmall.entity.third.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/8/29 9:30
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "User")
@Data
public class User {

    @TableId("id")
    private String id;// 用户Id

    @TableField("account")
    private String account;// 帐号

    @TableField("password")
    private String password;// 密码

    @TableField("createTime")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;// 建立时间

    @TableField("updateTime")
    private Date updateTime;//

    @TableField("flag")
    private String flag;// 有效标志 1：启用 0：停用

    @TableField("employeeNumber")
    private String employeeNumber;

    @TableField("name")
    private String name;// 姓名

    @TableField("gender")
    private String gender;// 性别,1:男 2:女 3:保密

    // /基本信息
    @TableField("avatar")
    private String avatar;// 自定义照片

    @TableField("mobile")
    private String mobile;// 手机

    @TableField("email")
    private String email;// 电子信箱

    @TableField("qq")
    private String qq;// qq

    @TableField("identityCard")
    private String identityCard;// 身份证号码

    @TableField("remark")
    private String remark;// 备注

    @TableField("introduce")
    private String introduce;// 个人简介

    @TableField("type")
    private int type = 1;// 用户类型 0：超级管理员 1：普通管理员

    // ////非持久化字段
    public static final String status_disable = "0";
    public static final String status_enable = "1";
    /**
     * 用户类型
     */
    public static final int type_system = 0;
    public static final int type_general = 1;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", flag='" + flag + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", remark='" + remark + '\'' +
                ", introduce='" + introduce + '\'' +
                ", type=" + type +
                '}';
    }
}
