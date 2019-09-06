package com.jinyafu.jmall.common.dto;

import java.io.Serializable;

import lombok.*;


/**
 * 请求信息
 * 
 * @version 2019年9月4日上午11:45:00
 * @author Ly
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class RequestDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    RequestPageData page = new RequestPageData(1L, 10L); // 分页信息
}




