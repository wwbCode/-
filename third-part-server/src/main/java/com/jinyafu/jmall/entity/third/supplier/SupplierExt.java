package com.jinyafu.jmall.entity.third.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 11:00
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import lombok.Data;

import java.util.List;

/**
 * @program: third-part-server-SupplierExt
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 11:00
 **/
@Data
public class SupplierExt extends Supplier {
    private  List<Supplier> suppliers;

}
