package com.jinyafu.thirdpart.common.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
* @Description: 消息
* @CreateDate: 2018/12/18 16:16
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageOutput<T> implements Out {

    /**
     * 状态码
     */
    private String code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private T data;

    /**
     * 构造方法
     */
    public MessageOutput() {
    }

    /**
     * 构造方法
     *
     * @param code 状态码
     */
    public MessageOutput(String code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 消息
     */
    public MessageOutput(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 消息
     * @param data    数据
     */
    public MessageOutput(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 处理成功
     *
     * @param data 数据
     * @return 输出
     */
    public static MessageOutput<Object> ok(Object data) {
        return new MessageOutput<>(OutputCode.OK.getCode(), OutputCode.OK.getMessage(), data);
    }

    /**
     * 处理成功
     *
     * @return 输出
     */
    public static MessageOutput<Object> ok() {
        return new MessageOutput<Object>(OutputCode.OK.getCode(), OutputCode.OK.getMessage());
    }

    /**
     * 处理成功 代码数据
     *
     * @param data 数据
     * @return 输出
     */
    public static MessageOutput<Object> okCode(Object data) {
        return new MessageOutput<Object>(OutputCode.OK.getCode(), null, data);
    }

    /**
     * 处理成功 代码
     *
     * @return 输出
     */
    public static MessageOutput<Object> okCode() {
        return new MessageOutput<Object>(OutputCode.OK.getCode());
    }

    /**
     * 代码
     *
     * @param code 状态码
     * @return 输出
     */
    public static MessageOutput<Object> code(String code) {
        return new MessageOutput<Object>(code);
    }

    /**
     * 获取
     *
     * @param code    状态码
     * @param message 消息
     * @return 输出
     */
    public static MessageOutput<Object> get(String code, String message) {
        return new MessageOutput<Object>(code, message);
    }

    /**
     * 获取
     *
     * @param code    状态码
     * @param message 消息
     * @return 输出
     */
    public static MessageOutput<Object> get(String code, String message, Object data) {
        return new MessageOutput<Object>(code, message, data);
    }

    /**
     * 异常
     *
     * @param data 数据
     * @return 输出
     */
    public static MessageOutput<Object> ex(Object data) {
        return new MessageOutput<Object>(OutputCode.EX.getCode(), OutputCode.EX.getMessage(), data);
    }

    /**
     * 异常
     *
     * @return 输出
     */
    public static MessageOutput<Object> ex() {
        return new MessageOutput<Object>(OutputCode.EX.getCode(), OutputCode.EX.getMessage());
    }

    /**
     * 异常 代码数据
     *
     * @param data 数据
     * @return 输出
     */
    public static MessageOutput<Object> exCode(Object data) {
        return new MessageOutput<Object>(OutputCode.EX.getCode(), null, data);
    }

    /**
     * 异常 代码
     *
     * @return 输出
     */
    public static MessageOutput<Object> exCode() {
        return new MessageOutput<Object>(OutputCode.EX.getCode());
    }

    /**
     * 参数不对 代码数据
     *
     * @param data 数据
     * @return 输出
     */
    public static MessageOutput<Object> pmCode(Object data) {
        return new MessageOutput<Object>(OutputCode.EX.getCode(), null, data);
    }

    /**
     * 参数不对 代码
     *
     * @return 输出
     */
    public static MessageOutput<Object> pmCode() {
        return new MessageOutput<Object>(OutputCode.PM.getCode());
    }

    /**
     * 参数不对
     *
     * @return 输出
     */
    public static MessageOutput<Object> pm() {
        return new MessageOutput<Object>(OutputCode.PM.getCode(), OutputCode.PM.getMessage());
    }

}
