package com.jinyafu.thirdpart.controller.serve;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 13:45
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.github.pagehelper.PageInfo;
import com.jinyafu.jmall.entity.third.serve.Serve;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.serve.ServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: third-part-server-ServeController
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 13:45
 **/
@RequestMapping("third/serve")
@Controller
class ServeController {

    @Autowired
    ServeService serveService;

    /**
     * @description: 服务列表
     * @date: 2019/8/30 13:47
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/listServeAll", method = RequestMethod.POST)
    @PermissionMapping(name = "菜单列表", key = "/third/serve/listServeAll", superKey = "third", type = PermissionMapping.Type.menu)
    @ResponseBody
    public PageOutput serveLiist(@RequestBody Map<String,Object> map) {
        PageInfo pageInfo = new PageInfo();
        if(null!=map.get("pageSize")||null!=map.get("pageNum")){
            pageInfo.setPageSize((Integer) map.get("pageSize"));
            pageInfo.setPageNum((Integer)map.get("pageNum"));
            return serveService.listAll(pageInfo);

        } else {
            return PageOutput.ex();
        }

    }

    /**
     * @description: 添加服务
     * @date: 2019/8/30 14:14
     * @author: wwb
     * @param: []
     * @return: com.jinyafu.thirdpart.common.code.PageOutput
     */
    @RequestMapping(value = "/addServe", method = RequestMethod.POST)
    @PermissionMapping(name = "添加服务", key = "/third/serve/addServe", superKey = "third", type = PermissionMapping.Type.menu)
    @ResponseBody
    public MessageOutput addServe(@RequestBody Serve serve) {
        return serveService.addServe(serve);

    }

    /**
     * @description: 删除服务
     * @date: 2019/8/30 14:21
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/deleteServe", method = RequestMethod.POST)
    @PermissionMapping(name = "删除服务", key = "/third/serve/deleteServe", superKey = "third", type = PermissionMapping.Type.menu)
    @ResponseBody
    public MessageOutput delete(@RequestBody Serve serve) {
        return serveService.deleteServe(serve);
    }

    /**
     * @description: 编辑
     * @date: 2019/8/30 14:21
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/editServe", method = RequestMethod.POST)
    @PermissionMapping(name = "编辑服务", key = "/third/serve/editServe", superKey = "third", type = PermissionMapping.Type.menu)
    @ResponseBody
    public MessageOutput edit(@RequestBody Serve serve){
        return serveService.editServe(serve);
    }

    /**
     * @description: 通过名字查找服务
     * @date: 2019/8/30 14:23
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/selectServe", method = RequestMethod.POST)
    @PermissionMapping(name = "通过名字查找服务", key = "/third/serve/selectServe", superKey = "third", type = PermissionMapping.Type.menu)
    @ResponseBody
    public PageOutput select(@RequestBody Map<String,Object> map) {
        Serve serve;
        if (map.get("serve") != null && map.get("serve") != "") {
             serve = (Serve) JsonUtil.convertMap(Serve.class, (Map) map.get("serve"));
        } else {
             serve = new Serve();
        }
        PageInfos pageInfos =new PageInfos();
        pageInfos.setPageSize((Integer) map.get("pageSize"));
        pageInfos.setPageNum((Integer)map.get("pageNum"));
        return serveService.selectByName(pageInfos,serve);
    }


    @RequestMapping(value = "/getByType", method = RequestMethod.POST)
    @ResponseBody
    public PageOutput getByType(@RequestBody Serve serve) {
            return serveService.getByType(serve);
    }

}
