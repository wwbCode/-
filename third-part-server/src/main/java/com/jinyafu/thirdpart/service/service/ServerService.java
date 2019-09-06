package com.jinyafu.thirdpart.service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.common.dto.ResponseDTO;
import com.jinyafu.jmall.common.dto.ResponsePageData;
import com.jinyafu.jmall.entity.data.service.ServerDTO;
import com.jinyafu.jmall.entity.third.service.Server;
import com.jinyafu.jmall.mapper.third.service.ServerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/9/4 17:42
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Service
public class ServerService {

    @Resource
    ServerMapper serverMapper;

    public ResponseDTO<?> queryList(ServerDTO data){
        Page<?> page = new Page<>(data.getPage().getPageNum(), data.getPage().getPageSize());
        List<Server> list = serverMapper.queryList(page, data);
        return ResponseDTO.success(new ResponsePageData<Server>(page.getCurrent(), page.getTotal(), page.getSize(), list, null));
    }

    public List<Server> list() {
        return serverMapper.list();
    }

    public void add(Server server){
        serverMapper.insertServers(server);
    }

    public void update(Server server){
        serverMapper.update(server);
    }

    public void delete(String id){
        serverMapper.delete(id);
    }

    public Server get(String id) {
        return serverMapper.get(id);
    }
}
