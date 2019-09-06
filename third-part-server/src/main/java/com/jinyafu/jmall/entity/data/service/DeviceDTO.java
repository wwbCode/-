package com.jinyafu.jmall.entity.data.service;

import com.jinyafu.jmall.common.dto.RequestDTO;
import lombok.Data;

/**
 * @Description:
 * @CreateDate: 2019/9/5 20:29
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@Data
public class DeviceDTO extends RequestDTO{

    private static final long serialVersionUID = 1L;

    private String machineryRoom; //机房
    private String deviceAddress; //设备地址
    private String managementAddress; //管理地址
    private String deviceType; //设备类型
    private String type; //型号
    private String contact; //联系人
    private String contactType; //联系方式
    private String specificAddress; //具体地址
    private String remark; //备注
}
