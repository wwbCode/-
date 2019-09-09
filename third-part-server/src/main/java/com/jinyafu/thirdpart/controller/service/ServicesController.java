package com.jinyafu.thirdpart.controller.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 13:45
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.service.ServicesDTO;
import com.jinyafu.jmall.entity.third.service.Services;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: third-part-server-ServeController
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 13:45
 **/
@RequestMapping("third/service")
@RestController
class ServicesController {

    @Autowired
    ServicesService servicesService;


    @ResponseBody
    @PermissionMapping(name = "服务列表（分页）", key = "/third/service/pageList", superKey = "service", type = PermissionMapping.Type.menu)
    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    public ResponseDTO<?> pageList(@RequestBody ServicesDTO data){
        return servicesService.queryList(data);
    }

    @ResponseBody
    @PermissionMapping(name = "增加服务", key = "/third/service/add", superKey = "/third/service/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MessageOutput<?> add(@RequestBody Services services){

        try{
//            Services services;
//            if (map.get("services")!=null && map.get("services")!=""){
//                services = (Services) JsonUtil.convertMap(Services.class, (Map) map.get("services"));
//            }else{
//                services = new Services();
//            }
            servicesService.add(services);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "修改服务", key = "/third/service/update", superKey = "/third/service/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MessageOutput<?> update(@RequestBody Services services){

        try{
//            Services services;
//            if (map.get("services")!=null && map.get("services")!=""){
//                services = (Services) JsonUtil.convertMap(Services.class, (Map) map.get("services"));
//            }else{
//                services = new Services();
//            }
            servicesService.update(services);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取服务详情", key = "/third/service/get", superKey = "/third/service/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public Output<?> get(@RequestBody Map<String, Object> map){

        try{
            Services services = new Services();
            String servicesId = map.get("id").toString();
            services = servicesService.get(servicesId);
            return Output.ok(services);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }




    /**
     * @description: 服务列表
     * @date: 2019/8/30 13:47
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @PermissionMapping(name = "菜单列表", key = "/third/service/list", superKey = "/third/service/pageList", type = PermissionMapping.Type.menu)
    @ResponseBody
    public Output<?> list() {
        return Output.ok(servicesService.listAll());
    }

//    /**
//     * @description: 添加服务
//     * @date: 2019/8/30 14:14
//     * @author: wwb
//     * @param: []
//     * @return: com.jinyafu.thirdpart.common.code.PageOutput
//     */
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    @PermissionMapping(name = "添加服务", key = "/third/services/add", superKey = "/third/services/pageList", type = PermissionMapping.Type.menu)
//    @ResponseBody
//    public MessageOutput addServe(@RequestBody Services services) {
//        return servicesService.addServe(services);
//
//    }

    /**
     * @description: 删除服务
     * @date: 2019/8/30 14:21
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @PermissionMapping(name = "删除服务", key = "/third/services/delete", superKey = "/third/services/pageList", type = PermissionMapping.Type.menu)
    @ResponseBody
    public MessageOutput delete(@RequestBody Services services) {
        return servicesService.deleteServe(services);
    }

    /**
     * @description: 编辑
     * @date: 2019/8/30 14:21
     * @author: wwb
     * @param:
     * @return:
     */
//    @RequestMapping(value = "/editServe", method = RequestMethod.POST)
//    @PermissionMapping(name = "编辑服务", key = "/third/services/editServe", superKey = "/third/services/pageList", type = PermissionMapping.Type.menu)
//    @ResponseBody
//    public MessageOutput edit(@RequestBody Services services){
//        return servicesService.editServe(services);
//    }

    /**
     * @description: 通过名字查找服务
     * @date: 2019/8/30 14:23
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/selectByName", method = RequestMethod.POST)
    @PermissionMapping(name = "通过名字查找服务", key = "/third/service/selectByName", superKey = "/third/service/pageList", type = PermissionMapping.Type.menu)
    @ResponseBody
    public Output<?> select(@RequestBody Map<String,Object> map) {
        Services services;
        if (map.get("services") != null && map.get("services") != "") {
             services = (Services) JsonUtil.convertMap(Services.class, (Map) map.get("services"));
        } else {
             services = new Services();
        }
        return Output.ok(servicesService.selectByName(services));
    }

}
