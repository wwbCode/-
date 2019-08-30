package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.system.RoleMenu;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/29 16:53
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    void insertRoleMenu(RoleMenu roleMenu);

    void delete(String id);

    List<RoleMenu> getRoleMenuListByRoleId(String roleId);

}
