package com.jinyafu.jmall.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * 响应实体基类
 * 
 * @version 2019年9月4日下午1:58:23
 * @author Ly
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private boolean success;
    private String message;
    T data; // 

    public static <T> ResponseDTO<T> success(T data) {
        return ResponseDTO.<T>builder()
                .code("200")
                .success(true)
                .message("成功")
                .data(data)
                .build();
    }
    
    public static <T> ResponseDTO<T> fail(String code,String message) {
        return ResponseDTO.<T>builder()
                .code(code)
                .success(false)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> ResponseDTO<T> fail(String code) {
        return ResponseDTO.<T>builder()
                .code(code)
                .build();
    }

    /**
     * 请求成功，无数据实体返回
     */
    public static <T> ResponseDTO<T> success() {
        return ResponseDTO.<T>builder()
                .code("200")
                .success(true)
                .message("成功")
                .build();
    }
}