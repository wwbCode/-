/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.jinyafu.jmall.mapper.third.system;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.system.User;

/**
 * TODO:类功能介绍
 * @version 2019年8月27日下午6:34:17
 * @author Ly
 */
public interface UserMapper extends BaseMapper<User>{
	public User getByAccount(@Param("account") String account);
}
