package com.jinyafu.thirdpart.service.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 19:57
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import com.jinyafu.jmall.entity.third.supplier.Supplier;
import com.jinyafu.jmall.mapper.third.service.ServicesMapper;
import com.jinyafu.jmall.mapper.third.supplier.SupplierMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
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
    @Resource
    ServicesMapper servicesMapper;


    /**
     * 添加供应商
     */
    @Transactional
    public MessageOutput addSupplier(Supplier supplier) {
        //if() 不能重名
        if (null != supplier.getName()) {
            supplier.setId(UUID.randomUUID().toString().replace("-", ""));
            supplier.setStartTime(new Date());
            supplier.setStatus(1);//未删除
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
    public PageOutput listAll(PageInfos pageInfos) {
        Page<Supplier> page = PageHelper.offsetPage(pageInfos.getStartResult(), pageInfos.getPageSize());
        List<Supplier> supplierList = supplierMapper.listAll();
        return PageOutput.ok(page,supplierList);
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

    /**
     * @description: 删除供应商
     * @date: 2019/9/2 10:45
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput deleteSupplier (String supplierId){
        //删除供应商以下所有的服务，把所有的服务状态置为0就可以
        supplierMapper.deleteSupplierById(supplierId);

        servicesMapper.deleteServeByFid(supplierId);
        return MessageOutput.ok();

    }
/**
 * @description: 方法描述
 * @date: 2019/9/6 8:59
 * @author: wwb
 * @param:
 * @return:
 */
    @Transactional
    public MessageOutput selectById(String supplierId){

        return MessageOutput.ok(supplierMapper.selectById(supplierId));
    }

    /**
     * @description: getByName
     * @date: 2019/9/6 8:59
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput selectByName(String supplierName){

        return MessageOutput.ok(supplierMapper.selectByName(supplierName));
    }


}
