package com.jinyafu.jmall.entity.data.system;

import com.jinyafu.jmall.common.dto.RequestDTO;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @CreateDate: 2019/9/4 14:42
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */
@Data
public class UserDTO extends RequestDTO {

    private static final long serialVersionUID = 1L;

    private String queryText;
    private String account;// 帐号
    private String name;// 姓名
    private String gender;// 性别
    private Date startCreateTime;// 建立时间
    private Date endCreateTime;//
    private String flag;// 有效标志 1：启用 0：停用
    private String employeeNumber;
    private String mobile;// 手机
    private String email;// 电子信箱
    private String qq;// qq
    private String identityCard;// 身份证号码
    private String remark;// 备注
    private String introduce;// 个人简介
    private int type = -1;// 用户类型 0：超级管理员 1：普通管理员
}
