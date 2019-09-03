package com.jinyafu.jmall.entity.data.system;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @CreateDate: 2019/8/30 9:11
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class RoleQuery {

    private String queryText;
    private String name;// 名
    private Date startCreateTime;// 建立时间
    private Date endCreateTime;//
    private String flag;// 有效标志 1：启用 0：停用
    private List<String> idList;
    private List<String> outIdList;

    @Override
    public String toString() {
        return "RoleQuery{" +
                "queryText='" + queryText + '\'' +
                ", name='" + name + '\'' +
                ", startCreateTime=" + startCreateTime +
                ", endCreateTime=" + endCreateTime +
                ", flag='" + flag + '\'' +
                ", idList=" + idList +
                ", outIdList=" + outIdList +
                '}';
    }
}
