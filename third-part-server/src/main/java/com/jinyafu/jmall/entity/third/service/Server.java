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
 * @CreateDate: 2019/9/4 17:13
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@TableName(value = "s_servers")
@Data
public class Server {

    @TableId("id")
    private String id; //主键id

    @TableField("projectName")
    private String projectName; //项目名

    @TableField("machineryRoom")
    private String machineryRoom; //机房

    @TableField("intranetIp")
    private String intranetIp; //内网

    @TableField("otherIp")
    private String otherIp; //其他Ip

    @TableField("fixedAssetsCode")
    private  String fixedAssetsCode; //固定资产编号

    @TableField("purpose")
    private String purpose; //用途

    @TableField("equipmentType")
    private String equipmentType; //设备类型

    @TableField("account")
    private String account; //账户

    @TableField("osType")
    private String osType; //OS类型

    @TableField("loginType")
    private String loginType; //登录方式

    @TableField("remark")
    private String remark; //备注

    @Override
    public String toString() {
        return "Server{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", machineryRoom='" + machineryRoom + '\'' +
                ", intranetIp='" + intranetIp + '\'' +
                ", otherIp='" + otherIp + '\'' +
                ", fixedAssetsCode='" + fixedAssetsCode + '\'' +
                ", purpose='" + purpose + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", account='" + account + '\'' +
                ", osType='" + osType + '\'' +
                ", loginType='" + loginType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
