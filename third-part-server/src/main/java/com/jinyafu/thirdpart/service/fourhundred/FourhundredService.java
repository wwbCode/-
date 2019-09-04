package com.jinyafu.thirdpart.service.fourhundred;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 9:43
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jinyafu.jmall.entity.third.fourhundred.Fourhundred;
import com.jinyafu.jmall.mapper.third.fourhundred.FourhundredMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
    public MessageOutput addFourhundred(Fourhundred fourhundred) {
        //需要通过电话来判断重复
        if (null != fourhundred.getTel() && !fourhundred.getTel().equals("")) {
            fourhundred.setId(UUID.randomUUID().toString().replace("-", ""));
            fourhundred.setIsDelete(1);
            fourhundredMapper.addFourhundred(fourhundred);
            return MessageOutput.ok();
        } else {
            return MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(), OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }
    }

    /**
     * @description: 400列表
     * @date: 2019/9/4 13:36
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public PageOutput listAll(PageInfos pageInfos) {
        List<Fourhundred> fourhundredList = fourhundredMapper.listAll();
        Page<Fourhundred> page = PageHelper.offsetPage(pageInfos.getPageNum(), pageInfos.getPageSize());
        return PageOutput.ok(page, fourhundredList);
    }

    /**
     * @description: 编辑400
     * @date: 2019/9/4 13:58
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput editFourhundred(Fourhundred fourhundred) {
        if(fourhundred.getId()!=null) {
            fourhundredMapper.editFourhundred(fourhundred);
            return MessageOutput.ok();
        } else {
            return  MessageOutput.get(OutputCode.PARAMS_INVALID_EMPTY.getCode(),OutputCode.PARAMS_INVALID_EMPTY.getMessage());
        }

    }

    /**
     * @description: 删除400记录
     * @date: 2019/9/4 14:41
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput deleteFourhundred(String  fourhundredId) {
        fourhundredMapper.deleteFourhundred(fourhundredId);
        return MessageOutput.ok();
    }

    /**
     * @description: 通过id查找400
     * @date: 2019/9/4 14:42
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput getById(String fourhundredId) {

        if (null != fourhundredId) {
            Fourhundred fourhundred= fourhundredMapper.getById(fourhundredId);
                return MessageOutput.ok(fourhundred);
            }
            else {
            return MessageOutput.ex();
        }
    }
//
//    /**
//     * @description: 通过模糊查询
//     * @date: 2019/9/4 14:46
//     * @author: wwb
//     * @param:
//     * @return:
//     */
//    @Transactional
//    public PageOutput selectByTel(String Tel){
//        if(null!=Tel&&!Tel.equals("")){
//            return PageOutput.ok(fourhundredMapper.selectByTel(Tel));
//        } else {
//            return PageOutput.ex();
//        }
//    }

    /**
     * @description: 多条件查询
     * @date: 2019/9/4 15:13
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public PageOutput selectByConditions(Fourhundred fourhundred){
        Page page=new Page();//
        List<Fourhundred>fourhundredList=fourhundredMapper.selectByConditions(fourhundred);
       return PageOutput.ok(page,fourhundredList);
    }

}
