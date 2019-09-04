package com.jinyafu.thirdpart.controller.system;

import com.jinyafu.jmall.entity.data.system.RoleQuery;
import com.jinyafu.jmall.entity.third.system.Role;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.common.util.common.JsonUtil;
import com.jinyafu.thirdpart.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public PageOutput pageList(@RequestBody Map<String,Object> map) {
        try {
            RoleQuery roleQuery;
            if(map.get("roleQuery")!=null&&map.get("roleQuery")!=""){
                roleQuery= (RoleQuery) JsonUtil.convertMap(RoleQuery.class,(Map) map.get("roleQuery"));
            }else{
                roleQuery=new RoleQuery();
            }
            PageInfos pageInfos = new PageInfos();
            pageInfos.setPageNum((Integer) map.get("pageNum"));
            pageInfos.setPageSize((Integer) map.get("pageSize"));
            return roleService.queryList(roleQuery, pageInfos);

        } catch (Exception e) {
            e.printStackTrace();
            return PageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "角色列表", key = "/third/system/role/list", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/role/list")
    public Output<?> list(@RequestBody RoleQuery roleQuery) {
        try {
            List<Role> list = roleService.queryList(roleQuery);
            return Output.ok(list);

        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "编辑角色", key = "/third/system/role/addOrUpdate", superKey = "/third/system/role/list")
    @RequestMapping(method = RequestMethod.POST, value = "/role/addOrUpdate")
    public MessageOutput<?> addOrUpdate(HttpServletRequest request,
                                     @RequestBody Map<String, Object> map) {
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

}