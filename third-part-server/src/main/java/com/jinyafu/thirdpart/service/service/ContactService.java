package com.jinyafu.thirdpart.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.data.service.ContactDTO;
import com.jinyafu.jmall.entity.third.service.Contact;
import com.jinyafu.jmall.mapper.third.service.ContactMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/5 9:33
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Service
public class ContactService {

    @Resource
    ContactMapper contactMapper;

    public ResponseDTO<?> queryList(ContactDTO data){
        Page<?> page = new Page<>(data.getPage().getPageNum(), data.getPage().getPageSize());
        List<Contact> list = contactMapper.queryList(page, data);
        return ResponseDTO.success(new ResponsePageData<Contact>(page.getCurrent(), page.getTotal(), page.getSize(), list, null));
    }

    public List<Contact> list(){
        return contactMapper.list();
    }

    public void add(Contact contact){
        contactMapper.insertContact(contact);
    }

    public void update(Contact contact){
        contactMapper.update(contact);
    }

    public void delete(String id){
        contactMapper.delete(id);
    }

    public Contact get(String id){
        return contactMapper.get(id);
    }
}
