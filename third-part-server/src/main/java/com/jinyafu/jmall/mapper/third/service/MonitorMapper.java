package com.jinyafu.jmall.mapper.third.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/11 11:09
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.service.Monitor;

import java.util.List;

/**
 * @program: third-part-server-MonitorMapper
 * @description: 监控
 * @author: Mr.wwb
 * @create: 2019-09-11 11:09
 **/
public interface MonitorMapper extends BaseMapper<Monitor> {
    List<Monitor> listAll();

    void insertMonitor(Monitor monitor);

    void updateMonitor(Monitor monitor);

    Monitor getById(String id);

    void deleteMonitor(String id);

    List<Monitor> selectByConditions(Monitor monitor);


}
