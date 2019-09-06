package com.jinyafu.jmall.entity.data.service;

import lombok.Data;

/**
 * @Description:
 * @CreateDate: 2019/9/5 9:15
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class ContactQuery {

    private String supplier; //供应商
    private String name; //姓名
    private String position; //岗位
    private String phone; //手机
    private String fixedLine; //固话
    private String mail; //邮箱
    private String weChat; //微信
    private String qq; //QQ
    private String address; //办公地址
    private String remark; //备注

}
