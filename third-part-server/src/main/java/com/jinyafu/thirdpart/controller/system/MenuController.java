package com.jinyafu.thirdpart.controller.system;

import com.jinyafu.jmall.entity.data.system.MenuQuery;
import com.jinyafu.jmall.entity.third.system.Menu;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.service.system.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/29 10:08
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@RestController
@RequestMapping("/third/system")
public class MenuController {

    @Autowired
    MenuService menuService;

    @ResponseBody
    @PermissionMapping(name = "菜单列表", key = "/third/system/menu/allList", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/menu/allList")
    public Output<?> allList(HttpServletRequest request){
        try{
            List<Menu> list = menuService.getAllList();
            return Output.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return Output.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "刷新菜单", key = "/third/system/menu/refresh", superKey = "/third/system/menu/allList")
    @RequestMapping(method = RequestMethod.POST, value = "/menu/refresh")
    public MessageOutput<?> refresh(HttpServletRequest request) {
        try{
            menuService.refresh();
            return MessageOutput.ok();
        }catch (Exception e){
            e.printStackTrace();
            return MessageOutput.ex();
        }

    }

    @ResponseBody
    @PermissionMapping(name = "修改菜单状态", key = "/third/system/menu/updateFlag", superKey = "/third/system/menu/allList")
    @RequestMapping(method = RequestMethod.POST, value = "/menu/updateFlag")
    public MessageOutput<?> update(HttpServletRequest request,
                          @RequestBody Map<String,Object> map) {
        try {
            menuService.updateFlag(map.get("id").toString(), map.get("flag").toString());
            return MessageOutput.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageOutput.ex();
        }
    }

    @ResponseBody
    @PermissionMapping(name = "条件获取菜单", key = "/third/system/menu/list", superKey = "system", type = PermissionMapping.Type.menu)
    @RequestMapping(method = RequestMethod.POST, value = "/menu/list")
    public Output<?> list(HttpServletRequest request,
                        @RequestBody MenuQuery menuQuery) {
        try {
            List<Menu> list = menuService.list(menuQuery);
            return Output.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return Output.ex();
        }
    }

}
