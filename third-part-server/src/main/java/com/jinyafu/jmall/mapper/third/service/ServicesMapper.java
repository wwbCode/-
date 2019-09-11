package com.jinyafu.jmall.mapper.third.service;
/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 15:43
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.service.ServicesDTO;
import com.jinyafu.jmall.entity.data.service.ServicesData;
import com.jinyafu.jmall.entity.data.service.ServicesQuery;
import com.jinyafu.jmall.entity.third.service.Services;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: third-part-server-ServicesMapper
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 15:43
 **/
public interface ServicesMapper extends BaseMapper<Services> {

    /**
     * 所有服务列表
     */
    List<ServicesData> listAll();

    void updateServeById(String id);

    void add(Services services);

    List<Services> selectByAllname(String name);

    List<Services> selectByName(@Param("name") Services services);

    void deleteServeByFid(@Param("fid") String fid);

    void update(Services services);

    List<Services> queryList(Page<?> page, @Param("data")ServicesDTO data);

    ServicesData get(String id);

    List<ServicesData> list(ServicesQuery servicesQuery);

    void updateSuperIdBySuperId(@Param("id") String id, @Param("superId") String superId);
}
