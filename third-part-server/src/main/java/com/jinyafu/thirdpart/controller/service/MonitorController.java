package com.jinyafu.thirdpart.controller.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/11 9:31
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.third.service.Monitor;
import com.jinyafu.thirdpart.service.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: third-part-server-monitor
 * @description: 监控信息
 * @author: Mr.Wwb
 * @create: 2019-09-11 09:31
 **/
@RequestMapping("/third/service")
@Controller
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * @description: 列表
     * @date: 2019/9/11 9:32
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping("/monitor/listAll")
    @ResponseBody
    public ResponseDTO listAll(@RequestBody Monitor monitor) {
        return monitorService.listAll(monitor);
    }

    /**
     * @description: 添加或者修改记录
     * @date: 2019/9/11 9:32
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping("/monitor/addOrUpdate")
    @ResponseBody
    public ResponseDTO addOrUpdate(@RequestBody Monitor monitor) {
        return monitorService.addOrUpdate(monitor);
    }

    /**
     * @description: getById
     * @date: 2019/9/11 9:33
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping("/monitor/getById")
    @ResponseBody
    public ResponseDTO getById(@RequestBody Monitor monitor) {
        return monitorService.getById(monitor);
    }

    /**
     * @description: 删除记录
     * @date: 2019/9/11 9:33
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping("/monitor/delete")
    @ResponseBody
    public ResponseDTO delete(@RequestBody Monitor monitor) {
        return monitorService.delete(monitor);
    }

    /**
     * @description: 多条件查询记录
     * @date: 2019/9/11 9:34
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping("/monitor/selectByConditions")
    @ResponseBody
    public ResponseDTO selectByConditions(@RequestBody Monitor monitor) {
        return monitorService.selectByConditions(monitor);
    }
//
//    /**
//     * @description: getByName
//     * @date: 2019/9/11 10:16
//     * @author: wwb
//     * @param:
//     * @return:
//     */
//    @RequestMapping("/monitor/getByName")
//    @ResponseBody
//    public ResponseDTO selectByConditions(@RequestBody Monitor monitor) {
//        return monitorService.getByName(monitor);
//    }

}
