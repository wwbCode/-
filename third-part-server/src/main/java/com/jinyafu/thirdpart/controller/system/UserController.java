package com.jinyafu.thirdpart.controller.system;

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.system.UserDTO;
import com.jinyafu.jmall.entity.data.system.UserQuery;
import com.jinyafu.jmall.entity.data.system.UserRoleInfo;
import com.jinyafu.jmall.entity.third.system.User;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/30 15:04
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@RestController
@RequestMapping("/third/system")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @PermissionMapping(name = "系统用户列表（分页）", key = "/third/system/user/pagelist", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/user/pagelist")
    public ResponseDTO<?> list(@RequestBody UserDTO data){
            return userService.queryUserList(data);
    }

    @ResponseBody
    @PermissionMapping(name = "获取系统用户", key = "/third/system/user/get", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/get")
    public Output<?> get(@RequestBody Map<String, Object> map) {
        try {
            User user = userService.getById(map.get("id").toString());
            return Output.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "编辑用户", key = "/third/system/user/addOrUpdate", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/addOrUpdate")
    public MessageOutput<?> addOrUpdate(@RequestBody Map<String, Object> map) {
        try {
            User user;
            if(map.get("user")!=null&&map.get("user")!=""){
                user= (User) JsonUtil.convertMap(User.class,(Map) map.get("user"));
            }else{
                user=new User();
            }
            List<String> menuIds = (List<String>) map.get("roleIds");
            userService.addOrUpdate(user, menuIds);
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "删除系统用户", key = "/third/system/user/delete", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/delete")
    public MessageOutput<?> delete(@RequestBody Map<String, Object> map) {
        try {
            userService.delete(map.get("id").toString());
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取系统用户详情", key = "/third/system/user/getInfo", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/getInfo")
    public Output<?> loadInfo(@RequestBody Map<String, Object> map) {
        try {
            Map<String, Object> userMap = new HashMap<String, Object>();
            User user = userService.getById(map.get("id").toString());
            List<UserRoleInfo> userRoleInfoList = userService.getUserRoleInfoListByUserId(map.get("id").toString());
            userMap.put("user", user);
            userMap.put("roleInfoList", userRoleInfoList);
            return Output.ok(userMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "修改密码", key = "/third/system/user/updatePassword", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/updatePassword")
    public MessageOutput<?> saveUpdatePassword(@RequestBody Map<String, Object> map) {
        try {
            String password = map.get("password").toString();
            String id= map.get("id").toString();
            userService.updatePassword(id, password);
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "账号是否存在", key = "/third/system/user/isExist", superKey = "/third/system/user/pagelist")
    @RequestMapping(method = RequestMethod.POST, value = "/user/isExist")
    public Output<?> isExistAccount(@RequestBody Map<String, Object> map) {
        String account = map.get("account").toString();
        String id = map.get("id").toString();
        try{
            boolean isExist = userService.isExistAccount(account, id);
            return Output.ok(isExist);
        }catch (Exception e){
            return Output.ex();
        }
    }



    @RequestMapping(method = RequestMethod.POST, value = "/index/login")
    public Object login(
            HttpServletRequest request,
            @RequestBody Map<String,Object> map) {
        return userService.login(map.get("account").toString(), map.get("password").toString());
    }



}
