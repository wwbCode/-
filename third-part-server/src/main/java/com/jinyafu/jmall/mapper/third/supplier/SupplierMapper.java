package com.jinyafu.jmall.mapper.third.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 19:31
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.supplier.Supplier;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import feign.Param;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * @program: third-part-server-SupplierMapper
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-29 19:31
 **/
public interface SupplierMapper extends BaseMapper<Supplier> {

    void addSupplier(@Param("data") Supplier supplier);

    // public void editSupplier(@Param("data") Supplier supplier);
    void updateSupplier(@Param("data") Supplier supplier);

    void deleteSupplier(@Param("status") String status);

    List<Supplier> listAll();

    List<Supplier> selectSupplierByName(@Param("name") String name);

    void deleteSupplierById(String id);

    Supplier selectById(String supplierId);

    Supplier selectByName(String name);

}

