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
 * @CreateDate: 2019/8/29 9:29
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "RoleMenu")
@Data
public class RoleMenu {

    @TableId("id")
    private String id;

    @TableField("roleId")
    private String roleId;// 角色Id

    @TableField("menuId")
    private String menuId;

    @TableField("createTime")
    private Date createTime;// 输入时间

    @Override
    public String toString() {
        return "RoleMenu{" +
                "id='" + id + '\'' +
                ", roleId='" + roleId + '\'' +
                ", menuId='" + menuId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
