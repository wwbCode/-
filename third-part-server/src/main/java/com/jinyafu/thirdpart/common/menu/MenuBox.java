package com.jinyafu.thirdpart.common.menu;

import com.jinyafu.jmall.entity.third.system.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/29 11:47
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public class MenuBox {

    public static List<Menu> getRootMenuList() {
        List<Menu> list = new ArrayList<Menu>();

        createMenu(list, "system", "系统权限");
        createMenu(list, "index", "首页导航");

        return list;
    }

    static void createMenu(List<Menu> list, String id, String name) {
        createMenu(list, id, name, "0");
    }

    static void createMenu(List<Menu> list, String id, String name, String superId) {

        Menu menu = new Menu();
        menu.setId(id);
        menu.setName(name);
        menu.setFlag("1");
        menu.setSuperId(superId);
        menu.setType(Menu.type_menu);
        menu.setCreateTime(new Date());
        list.add(menu);
        menu.setRank(list.indexOf(menu));
    }
}