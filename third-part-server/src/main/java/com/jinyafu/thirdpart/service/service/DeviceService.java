package com.jinyafu.thirdpart.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.data.service.DeviceDTO;
import com.jinyafu.jmall.entity.third.service.Device;
import com.jinyafu.jmall.mapper.third.service.DeviceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/5 20:42
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Service
public class DeviceService {

    @Resource
    DeviceMapper deviceMapper;

    public ResponseDTO<?> queryList(DeviceDTO data){
        Page<?> page = new Page<>(data.getPage().getPageNum(), data.getPage().getPageSize());
        List<Device> list = deviceMapper.queryList(page, data);
        return ResponseDTO.success(new ResponsePageData<Device>(page.getCurrent(), page.getTotal(), page.getSize(), list, null));
    }

    public List<Device> list() {
        return deviceMapper.list();
    }

    public void add(Device device){
        deviceMapper.insertDevice(device);
    }

    public void update(Device device){
        deviceMapper.update(device);
    }

    public void delete(String id){
        deviceMapper.delete(id);
    }

    public Device get(String id) {
        return deviceMapper.get(id);
    }


}
