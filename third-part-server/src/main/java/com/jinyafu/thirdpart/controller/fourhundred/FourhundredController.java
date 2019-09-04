package com.jinyafu.thirdpart.controller.fourhundred;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 8:58
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.entity.third.fourhundred.Fourhundred;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import com.jinyafu.thirdpart.service.fourhundred.FourhundredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: third-part-server-FourhundredController
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-09-04 08:58
 **/

@RequestMapping("third/fourhundred")
@Controller
public class FourhundredController {

        @Autowired
        FourhundredService fourhundredService;

        /**
         * @description: 添加400记录
         * @date: 2019/8/29 19:58
         * @author: wwb
         * @param:
         * @return:
         */
        @ResponseBody
        @RequestMapping(value = "/add",method = RequestMethod.POST)
        public MessageOutput add(@RequestBody Fourhundred fourhundred) {
            return fourhundredService.addFourhundred(fourhundred);
        }

        /**
         * @description: 400列表
         * @date: 2019/9/4 9:09
         * @author: wwb
         * @param:
         * @return:
         */
    @ResponseBody
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    public PageOutput listAll(@RequestBody  Map<String,Object> map){
        PageInfos pageInfos = new PageInfos();
        if(null!=map.get("pageSize")||null!=map.get("pageNum")){
            pageInfos.setPageSize((int)map.get("pageSize"));
            pageInfos.setPageNum((int)map.get("pageNum"));
            return fourhundredService.listAll(pageInfos);
        } else {
            return PageOutput.ex();
        }


    }

    /**
     * @description: 编辑400记录
     * @date: 2019/9/4 9:14
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public MessageOutput edit(@RequestBody Fourhundred fourhundred){
        //不能同名，不能
        return fourhundredService.editFourhundred(fourhundred);
    }
    /**
     * @description: 删除400记录
     * @date: 2019/9/4 9:16
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public MessageOutput delete(@RequestBody Fourhundred fourhundred){
        return fourhundredService.deleteFourhundred(fourhundred.getId());
    }

    /**
     * @description: 通过id查找400（详情，通过id查找所有字段）
     * @date: 2019/9/4 9:17
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/getById",method = RequestMethod.POST)
    public MessageOutput getById(@RequestBody Fourhundred  fourhundred){
        return fourhundredService.getById(fourhundred.getId());
    }

//    /**
//     * @description: 通过模糊查询 查找记录
//     * @date: 2019/9/4 9:19
//     * @author: wwb
//     * @param:
//     * @return:
//     */
//    @ResponseBody
//    @RequestMapping(value = "/selectByTel",method = RequestMethod.POST)
//    public PageOutput selectByTel(@RequestBody Fourhundred fourhundred){
//        return fourhundredService.selectByTel(fourhundred.getTel());
//    }

    /**
     * @description:多条件查询400
     * @date: 2019/9/4 15:09
     * @author: wwb
     * @param:
     * @return:
     */
    @ResponseBody
    @RequestMapping(value = "/selectByConditions",method = RequestMethod.POST)
    public PageOutput selectByConditions (@RequestBody Fourhundred fourhundred){
        return fourhundredService.selectByConditions(fourhundred);
    }



}
