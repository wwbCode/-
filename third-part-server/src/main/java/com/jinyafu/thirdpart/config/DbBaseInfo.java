package com.jinyafu.thirdpart.config;

import lombok.Data;

/**
 * @author 000062064
 * @title: DBBaseInfo
 * @projectName jmall_ms
 * @description: 数据库基本连接信息
 * @date 2019/5/15 19:10
 */
@Data
public class DbBaseInfo {
    private String url;
    private String username;
    private String password;
}
