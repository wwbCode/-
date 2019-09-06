package com.jinyafu.jmall.mapper.third.broadband;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/5 11:26
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinyafu.jmall.entity.third.broadband.Broadband;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: third-part-server-BroadbandMapper
 * @description: 宽带mapper
 * @author: Mr.Wwb
 * @create: 2019-09-05 11:26
 **/
public interface BroadbandMapper extends BaseMapper<Broadband> {
    void addBroadband(Broadband broadband);

    List<Broadband> listAll(Page<?> page, @Param("broadband") Broadband broadband);

    void editBroadband(Broadband broadband);

    void deleteBroadband(String broadbandId);

    List<Broadband> selectByConditions(Page<?> page, Broadband broadband);

    Broadband getById(String id);

}
