package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.data.system.UserData;
import com.jinyafu.jmall.entity.data.system.UserQuery;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/2 9:25
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface UserDataMapper extends BaseMapper<UserData>{

    List<UserData> queryList(UserQuery userQuery);
}
