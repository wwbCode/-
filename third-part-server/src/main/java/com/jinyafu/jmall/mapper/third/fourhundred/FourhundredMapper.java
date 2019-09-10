package com.jinyafu.jmall.mapper.third.fourhundred;/**
 * @Description: 类功能介绍
 * @CreateDate: 2019/9/4 10:41
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: wwb
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinyafu.jmall.entity.third.fourhundred.Fourhundred;
import feign.Param;

import java.util.List;

/**
 * @program: third-part-server-FourhundredMapper
 * @description:
 * @author: Mr.Wwb
 * @create: 2019-09-04 10:41
 **/
public interface FourhundredMapper extends BaseMapper<Fourhundred> {
    void addFourhundred(Fourhundred fourhundred);

    List<Fourhundred> listAll();

    void editFourhundred(Fourhundred fourhundred);

    void deleteFourhundred(String fourhundredId);

    Fourhundred getById(String fourhundredId);

    List<Fourhundred> selectByConditions(@Param("fourhundred")Fourhundred fourhundred);


}
