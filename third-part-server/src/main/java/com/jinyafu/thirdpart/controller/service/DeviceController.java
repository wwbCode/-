package com.jinyafu.thirdpart.controller.service;

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.service.DeviceDTO;
import com.jinyafu.jmall.entity.third.service.Device;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/9/5 20:45
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@RestController
@RequestMapping("/third/service")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @ResponseBody
    @PermissionMapping(name = "设备列表（分页）", key ="/third/service/device/pageList", superKey = "service", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/pageList")
    public ResponseDTO<?> pageList(@RequestBody DeviceDTO data){
        return deviceService.queryList(data);
    }

    @ResponseBody
    @PermissionMapping(name = "服务器列表", key ="/third/service/device/list", superKey = "/third/service/device/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/list")
    public Output<?> list(){
        try{
            List<Device> list = deviceService.list();
            return Output.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "新增服务器", key ="/third/service/device/add", superKey = "/third/service/device/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/add")
    public MessageOutput<?> add(@RequestBody Map<String, Object> map){

        try{
            Device device;
            if (map.get("device")!= null && map.get("device")!= ""){
                device = (Device) JsonUtil.convertMap(Device.class, (Map) map.get("device"));
            }else{
                device = new Device();
            }
            deviceService.add(device);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "修改服务器", key ="/third/service/device/update", superKey = "/third/service/device/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/update")
    public MessageOutput<?> update(@RequestBody Map<String, Object> map){
        try{
            Device device;
            if (map.get("device")!= null && map.get("device")!= ""){
                device = (Device) JsonUtil.convertMap(Device.class, (Map) map.get("device"));
            }else{
                device = new Device();
            }
            deviceService.update(device);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取单个服务器详情", key ="/third/service/device/get", superKey = "/third/service/device/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/get")
    public Output<?> get(@RequestBody Map<String, Object> map){
        try{
            Device device = deviceService.get(map.get("id").toString());
            return Output.ok(device);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }


    @ResponseBody
    @PermissionMapping(name = "删除服务器", key ="/third/service/device/delete", superKey = "/third/service/device/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "device/delete")
    public MessageOutput<?> delete(@RequestBody Map<String, Object> map){
        try{
            deviceService.delete(map.get("id").toString());
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }
}
