package com.jinyafu.jmall.mapper.third.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.service.ServerDTO;
import com.jinyafu.jmall.entity.third.service.Server;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/4 17:38
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface ServerMapper extends BaseMapper<Server>{

    void insertServers(Server server);

    void update(Server server);

    void delete(String id);

    List<Server> queryList(Page<?> page, @Param("data")ServerDTO data);

    List<Server> list();

    Server get(String id);
}
