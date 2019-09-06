package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.system.UserDTO;
import com.jinyafu.jmall.entity.third.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/30 14:55
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface UserMapper extends BaseMapper<User>{

//    List<UserData> queryList(UserQuery adminQuery);

    List<User> queryList(Page<?> page, @Param("data") UserDTO data);

    User getByAccount(@Param("account") String account);

    User get(String id);

    void delete(String id);

    void insertUser(User user);

    void updateBySelective(User user);

    User getAdminByAccount(String account);
}
