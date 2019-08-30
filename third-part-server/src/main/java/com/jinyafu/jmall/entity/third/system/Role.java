package com.jinyafu.jmall.entity.third.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/8/29 9:28
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "Role")
@Data
public class Role {

    @TableField("id")
    private String id;// 角色Id

    @TableField("name")
    private String name;// 角色名字

    @TableField("introduce")
    private String introduce;// 角色描述

    @TableField("createTime")
    private Date createTime;// 建立时间

    @TableField("flag")
    private String flag;// 有效标志 1：启用 0：停用

    @TableField("grade")
    private int grade = 1;// 角色级别0:系统角色1：普通角色

    @TableField("rank")
    private int rank;// 排序

    // ////非持久化字段
    public static final String status_disable = "0";
    public static final String status_enable = "1";

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createTime=" + createTime +
                ", flag='" + flag + '\'' +
                ", grade=" + grade +
                ", rank=" + rank +
                '}';
    }
}
