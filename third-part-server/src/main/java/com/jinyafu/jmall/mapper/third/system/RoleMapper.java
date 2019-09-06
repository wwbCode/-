package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.system.RoleDTO;
import com.jinyafu.jmall.entity.third.system.Menu;
import com.jinyafu.jmall.entity.third.system.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @CreateDate: 2019/8/29 16:53
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface RoleMapper extends BaseMapper<Menu>{

    List<Role> queryList(Page<?> page, @Param("data") RoleDTO data);

    void insertRole(Role role);

    void update(Role role);

    void delete(String id);

    Role get(String id);


    List<Role> list();
}
