package com.jinyafu.thirdpart.service.broadband;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/5 10:25
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.third.broadband.Broadband;
import com.jinyafu.jmall.mapper.third.broadband.BroadbandMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: third-part-server-BroadbandService
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-09-05 10:25
 **/
@Service
public class BroadbandService {
    @Resource
    BroadbandMapper broadbandMapper;

    /**
     * @description: 添加宽带记录
     * @date: 2019/9/5 10:26
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput addBroadband(Broadband broadband){
        Date date =new Date();
        // broadband.setOpenTime(date);
        //broadband.setExpirationTime(date);
        broadband.setId(UUID.randomUUID().toString().replace("-",""));
        broadband.setIsDelete(1);
        broadbandMapper.addBroadband(broadband);
        return MessageOutput.ok();
    }

    /**
     * @description: 列表展示
     * @date: 2019/9/5 10:30
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO<?> listAll(Broadband broadband){
        Page<?> page = new Page<>(broadband.getPage().getPageNum(),broadband.getPage().getPageSize());
        List<Broadband> broadbandList =broadbandMapper.listAll(page, broadband);
        return ResponseDTO.success(new ResponsePageData<Broadband>(page.getCurrent(),page.getTotal(),page.getSize(),broadbandList,null));
    }

    /**
     * @description: 编辑
     * @date: 2019/9/5 11:03
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput editBroaband(Broadband broadband){
        broadbandMapper.editBroadband(broadband);
        System.out.print("");
        return MessageOutput.ok();
    }

    /**
     * @description: 删除
     * @date: 2019/9/5 11:07
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public void deleteBroadband(String broadbandId){
        broadbandMapper.deleteBroadband(broadbandId);
    }

    /**
     * @description: 多种条件查询
     * @date: 2019/9/5 11:09
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO<?>selectByConditions(Broadband broadband){
        Page<?> page = new Page<>(broadband.getPage().getPageNum(),broadband.getPage().getPageSize());
        List<Broadband> broadbandList = broadbandMapper.selectByConditions(page,broadband);
        return ResponseDTO.success(new ResponsePageData<Broadband>( page.getCurrent(),page.getTotal(),page.getSize(),broadbandList,null));
    }

    /**
     * @description: getById
     * @date: 2019/9/5 11:13
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput getById(String broadbandId){
        Broadband broadband = broadbandMapper.getById(broadbandId);
        return MessageOutput.ok(broadband);
    }
}
