package com.jinyafu.jmall.entity.data.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/29 9:48
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class MenuQuery {

    private String queryText;
    private String id;// 菜单id
    private String superId;// 上级菜单id
    private String name;// 菜单名称
    private String flag;// 是否启用 1：启用 0：停用
    private int type;// 菜单类别 0：菜单 1：按钮
    private List<String> idList;// 条件包含的id
    private List<String> outIdList;// 条件排除的id

    @Override
    public String toString() {
        return "MenuQuery{" +
                "queryText='" + queryText + '\'' +
                ", id='" + id + '\'' +
                ", superId='" + superId + '\'' +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                ", type=" + type +
                ", idList=" + idList +
                ", outIdList=" + outIdList +
                '}';
    }
}
