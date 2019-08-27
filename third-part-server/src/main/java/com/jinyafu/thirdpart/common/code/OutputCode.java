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
    SEND_EMAIL_SUCCESS("THIRD_000008", "发送成功"),
    /**参数效验失败*/
    PARAMS_INVALID_EMPTY("THIRD_000009", "参数不能为空"),
    /**参数效验失败*/
    PARAMS_INVALID_FAIL("THIRD_000010", "参数效验失败"),
    /**短信发送异常*/
    MESSAGE_SEND_FAIL("THIRD_000011", "短信发送失败，请联系客服人员！"),
    /**短信验证码错误*/
    VERIFY_CODE_ERROR("THIRD_000012", "验证码错误！"),
    /**短信验证码已失效*/
    VERIFY_CODE_INVALID("THIRD_000013", "验证码已失效！"),
	PX("THIRD_000014", "密码错误"),
	ACCOUNT_ISNOT_EXIST("THIRD_000015", "账号不存在");

    @Getter
    private String code;

    @Getter
    private String message;

    OutputCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
