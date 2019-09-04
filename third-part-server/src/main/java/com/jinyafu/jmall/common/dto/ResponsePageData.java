/*
 *   Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 *   未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *   版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */

package com.jinyafu.jmall.common.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共响应数据报文体
 * 
 * @version 2019年9月4日上午11:45:21
 * @author Ly
 */
@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ResponsePageData<T> {
    private long pageNum; // 页码
    private long total; // 总条数
    private long totalPage; // 总页数
   // private long pageSize;//当前页条数
    @Singular("list")
    private List<T> list = new ArrayList<>();
    @Singular("results")
    private Map<String,Object> results = new HashMap<String,Object>();//附加的结果集，可能包含多个
}

