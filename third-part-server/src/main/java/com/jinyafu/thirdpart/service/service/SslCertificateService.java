package com.jinyafu.thirdpart.service.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/12 13:48
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.third.service.SslCertificate;
import com.jinyafu.jmall.mapper.third.service.ServicesMapper;
import com.jinyafu.jmall.mapper.third.service.SslCertificateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;


/**
 * @program: third-part-server-SslCertificateService
 * @description: ssl证书service
 * @author: Mr.Wwb
 * @create: 2019-09-12 13:48
 **/
@Service
public class SslCertificateService {
    @Resource
    private SslCertificateMapper sslCertificateMapper;
    @Resource
    private ServicesMapper servicesMapper;

    /**
     * @description: listAll
     * @date: 2019/9/12 13:53
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO listAll(SslCertificate sslCertificate) {
        Page page = new Page<>(sslCertificate.getPage().getPageNum(), sslCertificate.getPage().getPageSize());
        List<SslCertificate> sslCertificateList = sslCertificateMapper.list(page, sslCertificate);
        return ResponseDTO.success(new ResponsePageData<SslCertificate>(page.getCurrent(), page.getTotal(), page.getSize(), sslCertificateList, null));
    }

    /**
     * @description: 添加或者修改记录
     * @date: 2019/9/12 14:07
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO addOrUpdate(SslCertificate sslCertificate) {
        Boolean bool = sslCertificate.getId() != null && !sslCertificate.getId().equals("");
        if (!bool) {//添加操作
            sslCertificate.setId(UUID.randomUUID().toString().replace("-", ""));
            sslCertificate.setIsDelete(1);
            if (sslCertificate.getDominName() != null && !sslCertificate.getDominName().equals("")) {
                if (sslCertificate.getCompany() != null && !sslCertificate.getCompany().equals("")) {
                    sslCertificateMapper.insertSsl(sslCertificate);
                    return ResponseDTO.success();
                } else {
                    return ResponseDTO.fail("01", "归属公司不为空！");
                }
            } else {
                return ResponseDTO.fail("00", "域名不能为空！");
            }

        } else {//修改操作
            if (sslCertificate.getDominName() != null && !sslCertificate.getDominName().equals("")) {
                if (sslCertificate.getCompany() != null && !sslCertificate.getCompany().equals("")) {
                    sslCertificateMapper.updateSsl(sslCertificate);
                    return ResponseDTO.success();
                } else {
                    return ResponseDTO.fail("01", "归属公司不为空！");
                }
            } else {
                return ResponseDTO.fail("00", "域名不能为空！");
            }

        }

    }

    /**
     * @description: 删除记录
     * @date: 2019/9/12 14:14
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO delete(SslCertificate sslCertificate) {
        sslCertificateMapper.deleteSsl(sslCertificate.getId());
        return ResponseDTO.success();
    }

    /**
     * @description: getById
     * @date: 2019/9/12 14:15
     * @author: wwb
     * @param: String
     * @return: ResponseDTO.success()
     */
    @Transactional
    public ResponseDTO getById(SslCertificate sslCertificate1) {
        SslCertificate sslCertificate = sslCertificateMapper.getById(sslCertificate1.getId());
        if (null != sslCertificate) {
            return ResponseDTO.success(sslCertificate);
        } else {
            return ResponseDTO.fail("003", "所传参数错误！");
        }
    }

    /**
     * @description: 多条件查询记录
     * @date: 2019/9/11 10:22
     * @author: wwb
     * @param:
     * @return:
     */
    @Transactional
    public ResponseDTO getByConditions(SslCertificate sslCertificate) {
        Page page = new Page<>(sslCertificate.getPage().getPageNum(), sslCertificate.getPage().getPageSize());
        List<SslCertificate> monitorList = sslCertificateMapper.getByConditions(sslCertificate);
        return ResponseDTO.success(new ResponsePageData<SslCertificate>(page.getCurrent(), page.getTotal(), page.getSize(), monitorList, null));
    }
}
