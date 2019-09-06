package com.jinyafu.jmall.entity.data.system;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/8/30 14:43
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class UserQuery {

    private String queryText;
    private String account;// 帐号
    private String name;// 姓名
    private String gender;// 性别
    private Date startCreateTime;// 建立时间
    private Date endCreateTime;//
    private String flag;// 有效标志 1：启用 0：停用
    private String employeeNumber;
    private String mobile;// 手机
    private String email;// 电子信箱
    private String qq;// qq
    private String identityCard;// 身份证号码
    private String remark;// 备注
    private String introduce;// 个人简介
    private int type = -1;// 用户类型 0：超级管理员 1：普通管理员

    @Override
    public String toString() {
        return "UserQuery{" +
                "queryText='" + queryText + '\'' +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                ", flag='" + flag + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
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
