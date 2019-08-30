package com.jinyafu.thirdpart.service.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 19:57
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.entity.third.supplier.Supplier;
import com.jinyafu.jmall.mapper.third.supplier.SupplierMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @program: third-part-server-SupplierService
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-29 19:57
 **/
@Service
public class SupplierService {
    @Resource
    SupplierMapper supplierMapper;

    @Transactional
    public MessageOutput addSupplier (Supplier supplier){

    }


}
