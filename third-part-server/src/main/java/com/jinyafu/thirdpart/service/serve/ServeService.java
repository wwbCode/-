package com.jinyafu.thirdpart.service.serve;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 13:48
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinyafu.jmall.entity.third.serve.Serve;
import com.jinyafu.jmall.mapper.third.serve.ServeMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.PageOutput;
import com.jinyafu.thirdpart.common.data.PageInfos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @program: third-part-server-ServeService
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-08-30 13:48
 **/
@Service
public class ServeService {
    @Resource
    private ServeMapper serveMapper;

    /**
     * @description: 服务 列表
     * @date: 2019/8/30 15:28
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public PageOutput listAll(PageInfo pageInfo) {
        Page<Object> page = PageHelper.offsetPage(pageInfo.getStartRow(), pageInfo.getPageSize());
        List<Serve> serveList = serveMapper.listAll();
        return PageOutput.ok(page, serveList);
    }

    /**
     * @description: 添加
     * @date: 2019/8/30 17:04
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput addServe(Serve serve) {
        List<Serve> serveIntable = serveMapper.selectByAllname(serve.getName());
        if (serveIntable.isEmpty()) {
            if (serve.getName() != null || !serve.getName().equals("")) {
                if (serve.getPid() != null) {
                    if (serve.getOperator() != null) {
                        serve.setId(UUID.randomUUID().toString().replace("-", ""));
                        serve.setIsDelete(1);
                        serveMapper.addServe(serve);
                    } else {
                        return MessageOutput.get("101", "服务所对接人不能为空！");
                    }

                } else {
                    return MessageOutput.get("100", "服务所属供应商名字不能为空！");
                }
            } else {
                return MessageOutput.get("400", "服务名字不能为空！");
            }
        } else {
            return MessageOutput.get("300", "服务名字重名！");
        }
        return MessageOutput.ok();
    }

    /**
     * @description: 删除服务
     * @date: 2019/8/30 17:04
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput deleteServe(Serve serve) {
        if (!serve.getId().equals("")) {
            serveMapper.updateServeById(serve.getId());
        } else {
            return MessageOutput.ex();
        }
        return MessageOutput.ok();
    }

    /**
     * @description: 编辑服务
     * @date: 2019/9/2 9:12
     * @author: wwb
     * @param:
     * @return:
     */

    @Transactional
    public MessageOutput editServe(Serve serve) {
        if (null != serve) {
            serveMapper.uodateServe(serve);
        } else {
            return MessageOutput.ex();
        }

        return MessageOutput.ok();
    }

    /**
     * @description:
     * @date: 2019/9/2 9:41
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public PageOutput selectByName(PageInfos pageInfos, Serve serve) {
        Page<Object> page = PageHelper.offsetPage(pageInfos.getPageNum(), pageInfos.getPageSize());
        List<Serve> serveList = serveMapper.selectByName(serve);
        return PageOutput.ok(page, serveList);
    }

}
