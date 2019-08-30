package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.data.system.RoleQuery;
import com.jinyafu.jmall.entity.third.system.Menu;
import com.jinyafu.jmall.entity.third.system.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/29 16:53
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface RoleMapper extends BaseMapper<Menu>{

    List<Role> queryList(RoleQuery roleQuery);

    void insertRole(Role role);

    void update(Role role);

    void delete(String id);


}
