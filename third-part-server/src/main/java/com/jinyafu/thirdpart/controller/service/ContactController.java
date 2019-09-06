package com.jinyafu.thirdpart.controller.service;

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.service.ContactDTO;
import com.jinyafu.jmall.entity.third.service.Contact;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/9/5 9:41
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@RestController
@RequestMapping("/third/service")
public class ContactController {

    @Autowired
    ContactService contactService;

    @ResponseBody
    @PermissionMapping(name = "联络人列表（分页）", key ="/third/service/contact/pageList", superKey = "service", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/pageList")
    public ResponseDTO<?> pageList(@RequestBody ContactDTO data){
        return contactService.queryList(data);
    }

    @ResponseBody
    @PermissionMapping(name = "联络人列表", key ="/third/service/contact/list", superKey = "/third/service/contact/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/list")
    public Output<?> list(){
        try{
            List<Contact> list = contactService.list();
            return Output.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "新增联络人", key ="/third/server/contact/add", superKey = "/third/server/contact/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/add")
    public MessageOutput<?> add(@RequestBody Map<String, Object> map){

        try{
            Contact contact;
            if (map.get("contact")!= null && map.get("contact")!= ""){
                contact = (Contact) JsonUtil.convertMap(Contact.class, (Map) map.get("contact"));
            }else{
                contact = new Contact();
            }
            contactService.add(contact);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "修改联络人", key ="/third/server/contact/update", superKey = "/third/server/contact/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/update")
    public MessageOutput<?> update(@RequestBody Map<String, Object> map){
        try{
            Contact contact;
            if (map.get("contact")!= null && map.get("contact")!= ""){
                contact = (Contact) JsonUtil.convertMap(Contact.class, (Map) map.get("contact"));
            }else{
                contact = new Contact();
            }
            contactService.update(contact);
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取单个联络人详情", key ="/third/server/contact/get", superKey = "/third/server/contact/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/get")
    public Output<?> get(@RequestBody Map<String, Object> map){
        try{
            Contact contact = contactService.get(map.get("id").toString());
            return Output.ok(contact);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }


    @ResponseBody
    @PermissionMapping(name = "删除联络人", key ="/third/server/contact/delete", superKey = "/third/server/contact/pageList", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "contact/delete")
    public MessageOutput<?> delete(@RequestBody Map<String, Object> map){
        try{
            contactService.delete(map.get("id").toString());
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }
}
