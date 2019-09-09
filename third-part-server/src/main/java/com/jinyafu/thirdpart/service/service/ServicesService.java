package com.jinyafu.thirdpart.service.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/8/30 13:48
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.data.service.ServicesDTO;
import com.jinyafu.jmall.entity.third.service.Services;
import com.jinyafu.jmall.mapper.third.service.ServicesMapper;
import com.jinyafu.thirdpart.common.code.MessageOutput;
import com.jinyafu.thirdpart.common.code.Output;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
public class ServicesService {
    @Resource
    private ServicesMapper servicesMapper;

    /**
     * @description: 服务列表分页
     * @date: 2019/9/6 11:08
     * @author: xzq
     * @param:
     * @return:
     */
    public ResponseDTO<?> queryList(ServicesDTO data) {
        Page<?> page = new Page<>(data.getPage().getPageNum(), data.getPage().getPageSize());
        List<Services> list = servicesMapper.queryList(page, data);
        return ResponseDTO.success(new ResponsePageData<Services>(page.getCurrent(), page.getTotal(), page.getSize(), list, null));
    }

    /**
     * @description: 服务 列表
     * @date: 2019/8/30 15:28
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public List<Services> listAll() {
        List<Services> servicesList = servicesMapper.listAll();
        return servicesList;
    }

    /**
     * @description: 添加
     * @date: 2019/8/30 17:04
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public MessageOutput addServe(Services services) {
        List<Services> servicesIntable = servicesMapper.selectByAllname(services.getName());
        if (servicesIntable.isEmpty()) {
            if (services.getName() != null || !services.getName().equals("")) {
                if (services.getSupplierId() != null) {
                    if (services.getOperator() != null) {
                        services.setId(UUID.randomUUID().toString().replace("-", ""));
                        services.setIsDelete(1);
                        servicesMapper.add(services);
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
    public MessageOutput deleteServe(Services services) {
        if (!services.getId().equals("")) {
            servicesMapper.updateServeById(services.getId());
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

//    @Transactional
//    public MessageOutput editServe(Services services) {
//        if (null != services) {
//            servicesMapper.update(services);
//        } else {
//            return MessageOutput.ex();
//        }
//
//        return MessageOutput.ok();
//    }

    /**
     * @description:
     * @date: 2019/9/2 9:41
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public Output selectByName(Services services) {
        List<Services> servicesList = servicesMapper.selectByName(services);
        return Output.ok(servicesList);
    }

    public void add(Services services) {
        servicesMapper.add(services);
    }

    public void update(Services services) {
        servicesMapper.update(services);
    }

    public Services get(String servicesId) {
        return servicesMapper.get(servicesId);
    }
}
