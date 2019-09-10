package com.jinyafu.thirdpart.controller.supplier;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/29 17:01
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */


import com.jinyafu.jmall.entity.third.supplier.Supplier;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.service.supplier.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @program: third-part-server-SupplierController
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-29 17:01
 **/

@RequestMapping("third/supplier")
@Controller
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    /**
     * @description: 添加供应商
     * @date: 2019/8/29 19:58
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public MessageOutput addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);

    }

    /**
     * @description: 供应商列表
     * @date: 2019/8/29 19:58
     * @author: wwb
     * @param:
     * @return:
     */

    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    public PageOutput listAll(@RequestBody Map<String,Object> map){
        PageInfos pageInfos = new PageInfos();
//        if(null!=map.get("pageSize")||null!=map.get("pageNum")){
//            pageInfos.setPageSize((Integer) map.get("pageSize"));
//            pageInfos.setPageNum((Integer)map.get("pageNum"));
            return supplierService.listAll(pageInfos);
//        } else {
//            return PageOutput.ex();
//        }



    }

    /**
     * @description: 编辑供应商
     * @date: 2019/8/29 20:15
     * @author: wwb
     * @param:
     * @return:
     */

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public MessageOutput editSupplier (@RequestBody Supplier supplier){
        return supplierService.editSupplier(supplier);

    }

    /**
     * @description: 删除
     * @date: 2019/8/29 20:20
     * @author: wwb
     * @param:
     * @return:
     */

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public MessageOutput deleteSupplier(@RequestBody  Supplier supplier){
        if(null!=supplier.getId()) {
            return supplierService.deleteSupplier(supplier.getId());
        } else {
            return MessageOutput.ex();
        }
    }

    /**
     * @description: 查找供应商
     * @date: 2019/8/29 20:24
     * @author: wwb
     * @param:
     * @return:
     */
   /* @RequestMapping(value = "/findSupplier",method = RequestMethod.POST)
    public List<Supplier> findSupplier(@RequestBody Supplier supplier){
        if (null!=supplier){

        } else {throw new MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(),OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }
    }*/
    @ResponseBody
    @RequestMapping(value = "getById",method = RequestMethod.POST)
    public MessageOutput getById(@RequestBody Supplier supplier){
        if(null!=supplier.getId()) {
            return supplierService.selectById(supplier.getId());
        } else {
            return MessageOutput.get(OutputCode.EX.getCode(),OutputCode.EX.getMessage());
        }
    }
    /**
     * @description: getByName
     * @date: 2019/9/6 8:57
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "getByName",method = RequestMethod.POST)
    public MessageOutput getByName(@RequestBody Supplier supplier){
        if(null!=supplier.getName()) {
            return supplierService.selectByName(supplier.getName());
        } else {
            return MessageOutput.get(OutputCode.EX.getCode(),OutputCode.EX.getMessage());
        }
    }


}
