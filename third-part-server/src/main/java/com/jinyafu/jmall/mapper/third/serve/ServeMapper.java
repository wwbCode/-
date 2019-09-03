package com.jinyafu.jmall.mapper.third.serve;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 15:43
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.serve.Serve;
import feign.Param;

import java.util.List;

/**
 * @program: third-part-server-ServeMapper
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 15:43
 **/
public interface ServeMapper extends BaseMapper<Serve> {

    /**
     * 服务列表
     */
    List<Serve> listAll();

    void updateServeById(String id);

    void addServe(Serve serve);

    List<Serve> selectByAllname(String name);

    List<Serve> selectByName(@Param("name") Serve serve);

    void deleteServeByFid(@Param("fid") String fid);

    void uodateServe(Serve serve);

}
