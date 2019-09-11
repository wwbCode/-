package com.jinyafu.thirdpart.service.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/11 10:21
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.third.service.Monitor;
import com.jinyafu.jmall.mapper.third.service.MonitorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @program: third-part-server-MonitorService
 * @description: 监控信息
 * @author: Mr.Wwb
 * @create: 2019-09-11 10:21
 **/
@Service
public class MonitorService {
    @Resource
    private MonitorMapper monitorMapper;

    /**
     * @description: 列表
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO listAll(Monitor monitor) {
        Page page = new Page<>(monitor.getPage().getPageNum(), monitor.getPage().getPageSize());
        List<Monitor> monitorList = monitorMapper.listAll();
        return ResponseDTO.success((new ResponsePageData<Monitor>(page.getCurrent(), page.getTotal(), page.getSize(), monitorList, null)));
    }

    /**
     * @description: 添加或者修改记录
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param: s
     * @return: Re
     */
    @Transactional
    public ResponseDTO addOrUpdate(Monitor monitor) {
        Boolean bool = monitor.getId() != null && !monitor.getId().equals("");
        if (!bool) {//添加操作
            monitor.setId(UUID.randomUUID().toString().replace("-", ""));
            monitor.setIsDelete(1);
            if (monitor.getTel() != null && !monitor.getTel().equals("")) {
                if (monitor.getCompany() != null && !monitor.getCompany().equals("")) {
                    monitorMapper.insertMonitor(monitor);
                    return ResponseDTO.success();
                } else {
                    return ResponseDTO.fail("01", "归属公司不为空！");
                }
            } else {
                return ResponseDTO.fail("00", "联系方式不能为空");
            }

        } else {//修改操作
            if (monitor.getTel() != null && !monitor.getTel().equals("")) {
                if (monitor.getCompany() != null && !monitor.getCompany().equals("")) {
                    monitorMapper.updateMonitor(monitor);
                    return ResponseDTO.success();
                } else {
                    return ResponseDTO.fail("01", "归属公司不为空！");
                }
            } else {
                return ResponseDTO.fail("00", "联系方式不能为空");
            }

        }
    }

    /**
     * @description: getById
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO getById(Monitor monitor) {
        Monitor monitor1 = monitorMapper.getById(monitor.getId());
        if (null != monitor1) {
            return ResponseDTO.success(monitor1);
        }else {
            return ResponseDTO.fail("003","所传参数错误");
        }
    }
    /**
     * @description: 删除记录
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public  ResponseDTO delete(Monitor monitor){
        monitorMapper.deleteMonitor(monitor.getId());
        return ResponseDTO.success();
    }
    /**
     * @description: 多条件查询记录
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public  ResponseDTO selectByConditions(Monitor monitor){
        Page page = new Page<>(monitor.getPage().getPageNum(),monitor.getPage().getPageSize());
        List<Monitor> monitorList=monitorMapper.selectByConditions(monitor);
        return ResponseDTO.success(new ResponsePageData<Monitor>(page.getCurrent(),page.getTotal(),page.getSize(),monitorList,null));
    }


}
