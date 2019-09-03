package com.jinyafu.thirdpart.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinyafu.jmall.entity.data.system.RoleQuery;
import com.jinyafu.jmall.entity.third.system.Role;
import com.jinyafu.jmall.entity.third.system.RoleMenu;
import com.jinyafu.jmall.mapper.third.system.RoleMapper;
import com.jinyafu.jmall.mapper.third.system.RoleMenuMapper;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description:
 * @CreateDate: 2019/8/29 16:54
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Service
@Transactional
public class RoleService {

    @Resource
    RoleMapper roleMapper;
    @Resource
    RoleMenuMapper roleMenuMapper;

    public PageOutput queryList(RoleQuery roleQuery, PageInfos pageInfos){
        Page<Role> page = PageHelper.offsetPage(pageInfos.getStartResult(), pageInfos.getPageSize());
        List<Role> roleList = roleMapper.queryList(roleQuery);
        return PageOutput.ok(page, roleList);
    }

    public  List<Role> queryList(RoleQuery roleQuery){
        List<Role> roleList = roleMapper.queryList(roleQuery);
        return roleList;
    }

    public void addOrUpdate(Role role, List<String> menuIds) {
        if (null == role.getId() || "".equals(role.getId())) {
            roleMapper.insertRole(role);
        } else {
            roleMapper.update(role);
        }
        String roleId = role.getId();
        addOrUpdate(roleId, menuIds);
    }

    public void addOrUpdate(String roleId, List<String> menuIds) {
        List<RoleMenu> rmList = roleMenuMapper.getRoleMenuListByRoleId(roleId);

        List<RoleMenu> deleteList = new ArrayList<RoleMenu>();
        Map<String, RoleMenu> map = new HashMap<String, RoleMenu>();
        for (RoleMenu rm : rmList) {
            if (map.containsKey(rm.getMenuId())) {
                deleteList.add(rm);
            }
            map.put(rm.getMenuId(), rm);
        }

        List<RoleMenu> addList = new ArrayList<RoleMenu>();
        if (null != menuIds) {
            for (String menuId : menuIds) {
                RoleMenu rm = map.remove(menuId);
                if (null == rm) {
                    rm = new RoleMenu();
                    rm.setRoleId(roleId);
                    rm.setMenuId(menuId);
                    addList.add(rm);
                }
            }
        }
        deleteList.addAll(map.values());
        if (!addList.isEmpty()) {
            for (RoleMenu rm : addList) {
                roleMenuMapper.insertRoleMenu(rm);
            }
        }
        if (!deleteList.isEmpty()) {
            for (RoleMenu rm : deleteList) {
                roleMenuMapper.delete(rm.getId());
            }
        }
    }

    public Role getRole(String id) {
        id = (null == id) ? "" : id;
        return roleMapper.get(id);
    }

    public void delete(String id) {
        roleMapper.delete(id);
        roleMenuMapper.deleteByRoleId(id);
        //adminRoleDAO.deleteByRoleId(id);
    }

    public List<RoleMenu> getRoleMenuListByRoleId(String roleId) {
        List<RoleMenu> rmList = roleMenuMapper.getRoleMenuListByRoleId(roleId);
        return rmList;
    }

    public List<Role> allList() {
        List<Role> list = roleMapper.list();
        return list;
    }
}
