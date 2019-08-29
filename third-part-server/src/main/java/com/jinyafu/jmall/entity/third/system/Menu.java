package com.jinyafu.jmall.entity.third.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/8/29 9:26
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "Menu")
@Data
public class Menu {

    @TableId("id")
    private String id;// 菜单id

    @TableField("superId")
    private String superId;// 上级菜单id

    @TableField("name")
    private String name;// 菜单名称

    @TableField("flag")
    private String flag;// 是否启用 1：启用 0：停用

    @TableField("introduce")
    private String introduce;// 功能描述

    @TableField("createTime")
    private Date createTime;// 创建时间

    @TableField("rank")
    private int rank;// 菜单排序

    @TableField("type")
    private int type;// 菜单类别 0：菜单 1：按钮

    public static final int type_menu = 0;
    public static final int type_function = 1;

    // ////非持久化字段
    public static final String flag_disable = "0";
    public static final String flag_enable = "1";

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", superId='" + superId + '\'' +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                ", introduce='" + introduce + '\'' +
                ", createTime=" + createTime +
                ", rank=" + rank +
                ", type=" + type +
                '}';
    }
}
