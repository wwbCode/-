package com.jinyafu.jmall.mapper.third.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.system.Menu;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @CreateDate: 2019/8/29 9:51
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenuList();

    void insertMenu(Menu menu);

    int update(Menu menu);

    void delete(String id);

    void updateMapBySelective(Map<String,Object> map);

    List<Menu> queryList(Map<String,Object> map);

}
