package com.jinyafu.jmall.entity.data.service;

import com.jinyafu.jmall.common.dto.RequestDTO;
import lombok.Data;

/**
 * @Description:
 * @CreateDate: 2019/9/4 17:29
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class ServerDTO extends RequestDTO{

    private static final long serialVersionUID = 1L;

    private String projectName; //项目名
    private String machineryRoom; //机房
    private String intranetIp; //内网
    private String otherIp; //其他Ip
    private  String fixedAssetsCode; //固定资产编号
    private String purpose; //用途
    private String equipmentType; //设备类型
    private String account; //账户
    private String osType; //OS类型
    private String loginType; //登录方式
    private String remark; //备注
}
