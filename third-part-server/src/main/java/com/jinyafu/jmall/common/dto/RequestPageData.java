/*
 *   Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 *   未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *   版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */

package com.jinyafu.jmall.common.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.Locale;

/**
 * 分页请求信息
 * 
 * @version 2019年9月4日上午11:44:12
 * @author Ly
 */
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class RequestPageData {
    public long pageNum = 1; // 页码
    public long pageSize = 1; // 每页记录数

    public String orderBy; // 排序字段
    public int sort; // 排序类型：1升序；2降序

    public RequestPageData(long pageNum, long pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    @Transient
    public String getSortOrder() {
        return this.sort == 1 ? SortOrder.ASC.toString() : SortOrder.DESC.toString();
    }

    /**
     * 分页排序枚举，限分页类内部使用
     */
    private enum SortOrder {
        ASC {
            public String toString() {
                return "asc";
            }
        },
        DESC {
            public String toString() {
                return "desc";
            }
        };

        private SortOrder() {
        }

        @SuppressWarnings("unused")
		public static SortOrder fromString(String op) {
            return valueOf(op.toUpperCase(Locale.ROOT));
        }
    }
}