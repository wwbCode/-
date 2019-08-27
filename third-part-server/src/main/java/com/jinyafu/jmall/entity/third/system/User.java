/*
 *   Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 *   未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *   版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */

package com.jinyafu.jmall.entity.third.system;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @version 2019年8月27日下午5:38:08
 * @author Ly
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "user")
@Data
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    /**
     * 用户账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 有效标志 1：启用 0：停用
     */
    @TableField("flag")
    private String flag;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;
    
    /**
     * 工号
     */
    @TableField("employeeNumber")
    private String employeeNumber;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 用户类型 0：超级管理员 1：普通管理员
     */
    @TableField("type")
    private String type;
    
    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否删除(0：未删除；1：已删除；)
     */
    @TableField("isDelete")
    private Integer isDelete;

    @Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + ", flag="
				+ flag + ", gender=" + gender + ", mobile=" + mobile + ", employeeNumber=" + employeeNumber
				+ ", avatar=" + avatar + ", createTime=" + createTime + ", updateTime=" + updateTime + ", type=" + type
				+ ", email=" + email + ", remark=" + remark + ", isDelete=" + isDelete + "]";
	}
    
}
