package com.jinyafu.thirdpart.common.pojo.sun;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinyafu.thirdpart.common.code.Out;
import com.jinyafu.thirdpart.common.code.OutputCode;
import lombok.Data;

/**
* @Description: 发票发送邮件请求实体类
* @CreateDate: 2019/8/1 18:28
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailInfoReq {

    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 网站地址
     */
    private String netUrl;
    /**
     * 发票数量
     */
    private Integer num;
    /**
     * 发票下载地址
     */
    public String ticketUrl;
    /**
     * 用户邮件地址
     */
    public String userEmailAddress;
    /**
     * 平台邮件发件人地址
     */
    public String platformEmailAddress;

}
