package com.jinyafu.thirdpart.common.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @CreateDate: 2019/8/30 9:20
 * @Copyright版权所有 深圳金雅福控股集团有限公司
 * @Author: xzq
 * @Version: 1.0
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class PageData {

    private int pageSize = 30;// 页码大小
    private int totalCount = 0;
    private int pageNumber = 1;
    private int totalPage;
}
