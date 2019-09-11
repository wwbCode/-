package com.jinyafu.jmall.entity.data.service;

import com.jinyafu.jmall.entity.third.service.Services;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/9 19:36
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class ServicesData extends Services{

    private List<ServicesData> children = new ArrayList<>();
    private String superName; //父服务名字
    private String supplierName; //供应商名字
}
