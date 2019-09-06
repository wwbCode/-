package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.system.UserRole;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/30 14:55
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface UserRoleMapper extends BaseMapper<UserRole>{

//    List<UserRoleInfo> getAdminRoleInfoListByAdminIdList(@Param("adminIdList") List<String> adminIdList);

    void deleteByUserId(String userId);


    List<UserRole> getListByUserId(String userId);

    void insertUserRole(UserRole userRole);

    void delete(String id);
}
