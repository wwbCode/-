package com.jinyafu.thirdpart.controller.service;

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.service.ServerDTO;
import com.jinyafu.jmall.entity.third.service.Server;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/9/4 17:49
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@RestController
@RequestMapping("/third/service")
public class ServerController {

    @Autowired
    ServerService serverService;

    @ResponseBody
    @PermissionMapping(name = "服务器列表（分页）", key ="/third/service/server/pageList", superKey = "service", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/pageList")
    public ResponseDTO<?> pageList(@RequestBody ServerDTO data){
        return serverService.queryList(data);
    }

    @ResponseBody
    @PermissionMapping(name = "服务器列表", key ="/third/service/server/list", superKey = "/third/service/server/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/list")
    public Output<?> list(){
        try{
            List<Server> list = serverService.list();
            return Output.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "新增服务器", key ="/third/service/server/add", superKey = "/third/service/server/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/add")
    public MessageOutput<?> add(@RequestBody Map<String, Object> map){
        try{
            Server server;
            if (map.get("server")!= null && map.get("server")!= ""){
                server = (Server) JsonUtil.convertMap(Server.class, (Map) map.get("server"));
            }else{
                server = new Server();
            }
            serverService.add(server);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "修改服务器", key ="/third/service/server/update", superKey = "/third/service/server/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/update")
    public MessageOutput<?> update(@RequestBody Map<String, Object> map){
        try{
            Server server;
            if (map.get("server")!= null && map.get("server")!= ""){
                server = (Server) JsonUtil.convertMap(Server.class, (Map) map.get("server"));
            }else{
                server = new Server();
            }
            serverService.update(server);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取单个服务器详情", key ="/third/service/server/get", superKey = "/third/service/server/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/get")
    public Output<?> get(@RequestBody Map<String, Object> map){
        try{
            Server server = serverService.get(map.get("id").toString());
            return Output.ok(server);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }


    @ResponseBody
    @PermissionMapping(name = "删除服务器", key ="/third/service/server/delete", superKey = "/third/service/server/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "server/delete")
    public MessageOutput<?> delete(@RequestBody Map<String, Object> map){
        try{
            serverService.delete(map.get("id").toString());
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }


}
