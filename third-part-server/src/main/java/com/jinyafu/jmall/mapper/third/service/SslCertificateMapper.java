package com.jinyafu.jmall.mapper.third.service;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/12 13:52
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.third.service.SslCertificate;

import java.util.List;

/**
 * @program: third-part-server-SslCertificateMapper
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-09-12 13:52
 **/
public interface SslCertificateMapper extends BaseMapper<SslCertificate> {

    List<SslCertificate> list(Page<?> page, SslCertificate sslCertificate);

    void insertSsl(SslCertificate sslCertificate);

    void updateSsl(SslCertificate sslCertificate);

    void deleteSsl(String id);

    SslCertificate getById(String id);

    List<SslCertificate> getByConditions(SslCertificate sslCertificate);


}
