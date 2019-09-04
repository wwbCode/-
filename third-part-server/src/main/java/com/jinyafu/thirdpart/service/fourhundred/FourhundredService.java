package com.jinyafu.thirdpart.service.fourhundred;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 9:43
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.jinyafu.jmall.entity.third.fourhundred.Fourhundred;
import com.jinyafu.jmall.mapper.third.fourhundred.FourhundredMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @program: third-part-server-FourhundredService
 * @description: 400 service层
 * @author: Mr.Wwb
 * @create: 2019-09-04 09:43
 **/
@Service
public class FourhundredService {
    @Resource
    FourhundredMapper fourhundredMapper;

    /**
     * @description: 添加400 记录
     * @date: 2019/9/4 9:44
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput addFourhundred(Fourhundred fourhundred){
        if(fourhundred.get)
    }

}
