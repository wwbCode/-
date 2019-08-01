package com.jinyafu.thirdpart.common.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
* @Description: 输出
* @CreateDate: 2018/12/18 16:16
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Output<T> implements Out {

    /**
     * 状态码
     */
    private String code;
    /**
     * 数据
     */
    private T data;

    /**
     * 构造方法
     */
    public Output() {
    }

    /**
     * 构造方法
     *
     * @param code 状态码
     */
    public Output(String code) {
        this.code = code;
    }


    /**
     * 构造方法
     *
     * @param code 状态码
     * @param data 数据
     */
    public Output(String code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 处理成功
     *
     * @param data 数据
     * @return 输出
     */
    public static Output<Object> ok(Object data) {
        return new Output<>(OutputCode.OK.getCode(), data);
    }

    /**
     * 处理成功
     *
     * @return 输出
     */
    public static Output<Object> ok() {
        return new Output<>(OutputCode.OK.getCode());
    }

    /**
     * 获取
     *
     * @param code 状态码
     * @return 输出
     */
    public static Output<Object> get(String code) {
        return new Output<>(code);
    }

    /**
     * 获取
     *
     * @param code 状态码
     * @return 输出
     */
    public static Output<Object> get(String code, Object data) {
        return new Output<>(code, data);
    }

    /**
     * 获取
     *
     * @param code 状态码
     * @return 输出
     */
    public static Output<Object> ex(String code) {
        return new Output<>(code);
    }

    /**
     * 处理失败
     *
     * @param data 数据
     * @return 输出
     */
    public static Output<Object> ex(Object data) {
        return new Output<>(OutputCode.EX.getCode(), data);
    }

    /**
     * 处理失败
     *
     * @return 输出
     */
    public static Output<Object> ex() {
        return new Output<>(OutputCode.EX.getCode());
    }

    /**
     * 参数不对
     *
     * @return 输出
     */
    public static Output<Object> pm() {
        return new Output<>(OutputCode.PM.getCode());
    }

    /**
     * 设置 数据
     *
     * @param data 数据
     * @return 输出
     */
    public Output<T> setData(T data) {
        this.data = data;
        return this;
    }

}
