package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.data.system.UserRoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/2 9:37
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface UserRoleInfoMapper extends BaseMapper<UserRoleInfo> {

    List<UserRoleInfo> getUserRoleInfoListByUserIdList(@Param("userIdList") List<String> userIdList);
}
