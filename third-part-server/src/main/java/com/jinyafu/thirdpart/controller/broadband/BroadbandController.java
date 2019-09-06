package com.jinyafu.thirdpart.controller.broadband;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/5 9:31
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.entity.third.broadband.Broadband;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.service.broadband.BroadbandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: third-part-server-BroadbandController
 * @description: 宽带业务
 * @author: Mr.Wwb
 * @create: 2019-09-05 09:31
 **/
@Controller
@RequestMapping("/third/broadband")
public class BroadbandController {

    @Autowired
    BroadbandService broadbandService;
    /**
     * @description: 添加宽带记录
     * @date: 2019/9/5 9:32
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public MessageOutput add(@RequestBody Broadband broadband) {
        if (null != broadband.getOperator() && !broadband.getOperator().equals("")) {
            if (null != broadband.getTel() && !broadband.getTel().equals("")) {
                if (null != broadband.getSystem() && !broadband.getSystem().equals("")) {
                    return broadbandService.addBroadband(broadband);
                } else {
                    MessageOutput.get("402", "系统不为空！");
                }
            } else {
                MessageOutput.get("401", "号码不为空！");
            }

        } else {
            MessageOutput.get("400", "运营商不为空！");
        }
        return null;
    }

    /**
     * @description: listAll
     * @date: 2019/9/5 9:45
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO<?> listAll(@RequestBody Broadband broadband) {
        return broadbandService.listAll(broadband);
    }

    /**
     * @description: 编辑管带
     * @date: 2019/9/5 9:51
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public MessageOutput edit(@RequestBody Broadband broadband) {
        if (null != broadband.getOperator() && !broadband.getOperator().equals("")) {
            if (null != broadband.getTel() && !broadband.getTel().equals("")) {
                if (null != broadband.getSystem() && !broadband.getSystem().equals("")) {
                    return broadbandService.editBroaband(broadband);
                } else {
                    MessageOutput.get("402", "系统不为空！");
                }
            } else {
                MessageOutput.get("401", "号码不为空！");
            }

        } else {
            MessageOutput.get("400", "运营商不为空！");
        }
        return MessageOutput.ok();
    }

    /**
     * @description: delete
     * @date: 2019/9/5 9:54
     * @author: wwb
     * @param:
     * @return:
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public MessageOutput delete(@RequestBody Broadband broadband) {
        broadbandService.deleteBroadband(broadband.getId());
        return MessageOutput.ok();
    }

    /**
     * @description: 多种条件查询
     * @date: 2019/9/5 9:57
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/selectByConditions",method = RequestMethod.POST)
    public ResponseDTO<?> selectByConditions (@RequestBody Broadband broadband){
        return broadbandService.selectByConditions(broadband);
    }

    /**
     * @description: 通过id查找宽带
     * @date: 2019/9/5 9:57
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/getById",method = RequestMethod.POST)
    public MessageOutput getById(@RequestBody Broadband  broadband){
        return broadbandService.getById(broadband.getId());
    }

}
