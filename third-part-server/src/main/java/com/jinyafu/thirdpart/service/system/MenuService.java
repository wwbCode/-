package com.jinyafu.thirdpart.service.system;

import com.jinyafu.jmall.entity.data.system.MenuQuery;
import com.jinyafu.jmall.entity.third.system.Menu;
import com.jinyafu.jmall.mapper.third.system.MenuMapper;
import com.jinyafu.thirdpart.common.annotation.PermissionMapping;
import com.jinyafu.thirdpart.common.menu.MenuBox;
import com.jinyafu.thirdpart.common.util.common.ClassScaner;
import com.jinyafu.thirdpart.common.util.common.MapTools;
import com.jinyafu.thirdpart.common.util.common.MenuUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Description:
 * @CreateDate: 2019/8/29 10:03
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    public List<Menu> getAllList(){
        return menuMapper.getAllMenuList();
    }

    /**
     * 刷新系统菜单 date 2018-07-05 09:18:37<br>
     * description
     *
     * @author XiaHui<br>
     * @since
     */
    @Transactional
    public void refresh() {
        List<Menu> allDBList = getAllList();
        List<Menu> allSystemList = getSystemMenuList();
        Map<String, Menu> dbMap = getMenuMap(allDBList);

        List<Menu> deleteList = new ArrayList<Menu>();
        List<Menu> updateList = new ArrayList<Menu>();
        List<Menu> addList = new ArrayList<Menu>();

        for (Menu m : allSystemList) {
            String id = m.getId();
            Menu menu = dbMap.remove(id);
            if (null == menu) {
                addList.add(m);
            } else {
                menu.setSuperId(m.getSuperId());
                menu.setName(m.getName());
                menu.setType(m.getType());
                updateList.add(menu);
            }
        }

        deleteList.addAll(dbMap.values());

        if (!addList.isEmpty()) {
            for (Menu menu : addList) {
                menuMapper.insertMenu(menu);
            }
        }

        if (!updateList.isEmpty()) {
            for (Menu menu : updateList) {
                menuMapper.update(menu);
            }
        }

        if (!deleteList.isEmpty()) {
            for (Menu menu : deleteList) {
                menuMapper.delete(menu.getId());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Menu> getSystemMenuList() {
        List<Menu> list = MenuBox.getRootMenuList(); //new ArrayList<Menu>();
        Set<Class<?>> classSet = ClassScaner.scan("com.jinyafu.thirdpart.controller", RequestMapping.class);// 扫描com.hk包下面的所有被注解ActionMapping的类
        for (Class<?> classType : classSet) {
            Annotation[] as = classType.getAnnotations();
            for (Annotation annotation : as) {
                if (annotation instanceof RequestMapping) {
                    RequestMapping rm = (RequestMapping) annotation;
                    handleMethod(list, classType, rm);
                    break;
                }
            }
        }
        return list;
    }

    private void handleMethod(List<Menu> menuList, Class<?> classType, RequestMapping brm) {
        String[] paths = brm.value() == null ? brm.path() : brm.value();
        String basePath = (null != paths && paths.length > 0) ? paths[0] : "";

        Method[] methods = classType.getMethods();
        if (null != methods && methods.length > 0) {
            for (Method method : methods) {
                PermissionMapping pm = method.getAnnotation(PermissionMapping.class);
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                if (null != pm && null != rm) {
                    Menu menu = create(basePath, rm, pm, ".");
                    menuList.add(menu);
                }
            }
        }
    }

    private Menu create(String basePath, RequestMapping rm, PermissionMapping pm, String space) {

        String key = pm.key();
        PermissionMapping.Type pt = pm.type();

        String[] paths = rm.value() == null ? rm.path() : rm.value();
        String path = (null != paths && paths.length > 0) ? paths[0] : "";
        String id = (key == null || key.isEmpty()) ? basePath + path : key;
        String superId = pm.superKey();
        String name = pm.name();
        int type = (pt == PermissionMapping.Type.menu) ? Menu.type_menu : Menu.type_function;

        Menu menu = new Menu();
        id = MenuUtil.getKey(id, space);
        superId = MenuUtil.getKey(superId, space);

        menu.setId(id);
        menu.setSuperId(superId);
        menu.setName(name);
        menu.setFlag(Menu.flag_enable);
        menu.setType(type);
        return menu;
    }

    private Map<String, Menu> getMenuMap(List<Menu> list) {
        Map<String, Menu> map = new HashMap<String, Menu>();
        for (Menu menu : list) {
            map.put(menu.getId(), menu);
        }
        return map;
    }

    public void updateFlag(String id, String flag) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("flag", flag);
        menuMapper.updateMapBySelective(map);
    }

    public List<Menu> list(MenuQuery menuQuery) {
        Map<String,Object> map = MapTools.beanToMap(menuQuery);
        List<Menu> list = menuMapper.queryList(map);
        return list;
    }
}
