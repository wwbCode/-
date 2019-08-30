package com.jinyafu.thirdpart.service.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 19:57
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.entity.third.supplier.Supplier;
import com.jinyafu.jmall.entity.third.supplier.SupplierExt;
import com.jinyafu.jmall.mapper.third.supplier.SupplierMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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


    /**
     * 添加供应商
     */
    @Transactional
    public MessageOutput addSupplier(Supplier supplier) {
        //if() 不能重名
        if (null != supplier.getName()) {
            supplier.setId(UUID.randomUUID().toString().replace("-", ""));
            supplier.setStartTime(new Date());
            supplier.setStatus("1");//未删除
            supplierMapper.addSupplier(supplier);
        } else {
            return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }

        return MessageOutput.ok();
    }

    /**
     * 供应商列表
     */
    @Transactional
    public MessageOutput listAll() {
        return MessageOutput.ok(supplierMapper.listAll());
    }

    /**
     * 编辑供应商信息
     */
    @Transactional
    public MessageOutput editSupplier(Supplier supplier) {
        List<Supplier> supplierInTable = supplierMapper.selectSupplierByName(supplier.getName());
        if ( supplierInTable.get(0).toString()==null) {
            if (null != supplier.getName() || !supplier.getName().equals("")) {

                supplierMapper.updateSupplier(supplier);

            } else {
                return MessageOutput.get(OutputCode.DX.getCode(), OutputCode.DX.getMessage());
            }

        } else {
            return MessageOutput.get(OutputCode.DX.getCode(), OutputCode.DX.getMessage());
        }
        return MessageOutput.ok();
    }

}
