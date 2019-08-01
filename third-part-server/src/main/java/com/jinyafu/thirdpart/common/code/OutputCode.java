package com.jinyafu.thirdpart.common.code;

import lombok.Getter;

/**
 * @Description: 类功能介绍
 * @CreateDate: 2018/12/18 16:13
 * @Copyright版权所有  深圳金雅福控股集团有限公司
 * @Author: mjt
 * @Version: 1.0
 */
public enum OutputCode {

    OK("THIRD_000000", "处理成功"),
    PM("THIRD_000001", "参数不对"),
    EX("THIRD_000002", "系统错误"),
    TX("THIRD_000003","登录已过期，请重新登录"),
    DX("THIRD_000004","数据格式不对"),
    NX("THIRD_000005","没有数据"),
    AX("THIRD_000006", "权限不足"),
    EMPTY("THIRD_000007", "参数为空"),
    SEND_EMAIL_SUCCESS("THIRD_000008", "发送成功");

    @Getter
    private String code;

    @Getter
    private String message;

    OutputCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
