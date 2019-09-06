package com.jinyafu.jmall.mapper.third.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.data.service.ContactDTO;
import com.jinyafu.jmall.entity.third.service.Contact;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/5 9:19
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

public interface ContactMapper extends BaseMapper<Contact>{

    List<Contact> queryList(Page<?> page, @Param("data")ContactDTO data);

    void insertContact(Contact contact);

    void update(Contact contact);

    void delete(String id);

    Contact get(String id);

    List<Contact> list();
}
