package com.jinyafu.jmall.mapper.third.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.service.DeviceDTO;
import com.jinyafu.jmall.entity.third.service.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/5 20:32
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface DeviceMapper extends BaseMapper<Device> {

    void insertDevice(Device Device);

    void update(Device device);

    void delete(String id);

    List<Device> queryList(Page<?> page, @Param("data")DeviceDTO data);

    List<Device> list();

    Device get(String id);

}
