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
 * @CreateDate: 2019/9/5 20:24
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "s_device")
@Data
public class Device {

    @TableId("id")
    private String id; //主键id

    @TableField("machineryRoom")
    private String machineryRoom; //机房

    @TableField("deviceAddress")
    private String deviceAddress; //设备地址

    @TableField("managementAddress")
    private String managementAddress; //管理地址

    @TableField("deviceType")
    private String deviceType; //设备类型

    @TableField("type")
    private String type; //型号

    @TableField("contact")
    private String contact; //联系人

    @TableField("contactType")
    private String contactType; //联系方式

    @TableField("specificAddress")
    private String specificAddress; //具体地址

    @TableField("remark")
    private String remark; //备注
}
