package com.jinyafu.thirdpart.controller.system;


import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.data.system.RoleDTO;
import com.jinyafu.jmall.entity.third.system.Role;
import com.jinyafu.jmall.entity.third.system.RoleMenu;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/29 16:55
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@RestController
@RequestMapping("/third/system")
public class RoleController {

    @Autowired
    RoleService roleService;

    @ResponseBody
    @PermissionMapping(name = "角色列表(分页)", key = "/third/system/role/pageList", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/role/pageList")
    public ResponseDTO<?> pageList(@RequestBody RoleDTO data) {
    	return roleService.queryList(data);
    }

//    @ResponseBody
//    @PermissionMapping(name = "角色列表", key = "/third/system/role/list", superKey = "system", type = PermissionMapping.Type.menu)
//    @RequestMapping(method = RequestMethod.POST, value = "/role/list")
//    public Output<?> list(@RequestBody RoleQuery roleQuery) {
//        try {
//            List<Role> list = roleService.queryList(roleQuery);
//            return Output.ok(list);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Output.ex();
//        }
//    }

    @ResponseBody
    @PermissionMapping(name = "编辑角色", key = "/third/system/role/addOrUpdate", superKey = "/third/system/role/list")
    @RequestMapping(method = RequestMethod.POST, value = "/role/addOrUpdate")
    public MessageOutput<?> addOrUpdate(@RequestBody Map<String, Object> map) {
        try {
            Role role;
            if(map.get("role")!=null&&map.get("role")!=""){
                role= (Role) JsonUtil.convertMap(Role.class,(Map) map.get("role"));
            }else{
                role=new Role();
            }
            List<String> menuIds = (List<String>) map.get("menuIds");
            roleService.addOrUpdate(role, menuIds);
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "获取单个角色详情", key = "/third/system/role/get", superKey = "/third/system/role/list")
    @RequestMapping(method = RequestMethod.POST, value = "/role/get")
    public Output<?> get(@RequestBody Map<String, Object> map) {
        try {
            Role role = roleService.getRole(map.get("id").toString());
            return Output.ok(role);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "删除角色", key = "/third/system/role/delete", superKey = "/third/system/role/list")
    @RequestMapping(method = RequestMethod.POST, value = "/role/delete")
    public MessageOutput<?> delete(@RequestBody Map<String, Object> map) {
        try {
            roleService.delete(map.get("id").toString());
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "角色权限", key = "/third/system/role/roleMenuList", superKey = "/third/system/role/list")
    @RequestMapping(method = RequestMethod.POST, value = "/role/roleMenuList")
    public Output<?> roleMenuList(@RequestBody Map<String, Object> map) {
        try {
            List<RoleMenu> list = roleService.getRoleMenuListByRoleId(map.get("id").toString());
            return Output.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "所有角色列表", key = "/third/system/role/allList", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/role/allList")
    public Output<?> allList() {

        try {
            List<Role> list = roleService.allList();
            return Output.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

}
