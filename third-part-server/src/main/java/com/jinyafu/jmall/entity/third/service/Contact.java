package com.jinyafu.jmall.entity.third.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @CreateDate: 2019/9/4 20:01
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "s_contact")
@Data
public class Contact {

    @TableId("id")
    private String id; //主键id

    @TableField("supplier")
    private String supplier; //供应商

    @TableField("name")
    private String name; //姓名

    @TableField("position")
    private String position; //岗位

    @TableField("phone")
    private String phone; //手机

    @TableField("fixedLine")
    private String fixedLine; //固话

    @TableField("mail")
    private String mail; //邮箱

    @TableField("weChat")
    private String weChat; //微信

    @TableField("qq")
    private String qq; //QQ

    @TableField("address")
    private String address; //办公地址

    @TableField("remark")
    private String remark; //备注

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", supplier='" + supplier + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", fixedLine='" + fixedLine + '\'' +
                ", mail='" + mail + '\'' +
                ", weChat='" + weChat + '\'' +
                ", qq='" + qq + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
