package com.jinyafu.thirdpart.common.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import lombok.Data;

/**
* @Description: 类功能介绍
* @CreateDate: 2018/12/20 14:24
* @Copyright版权所有  深圳金雅福控股集团有限公司
* @Author: mjt
* @Version: 1.0
*/
@SuppressWarnings("rawtypes")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageOutput implements Out {

    /**
     * 状态码
     */
    private String code;
    /**
     * 消息
     */
    private String message;

    /**
     * 默认当前页
     */
    private Integer page = 1;

    /**
     * 每页默认的结果数
     */
    private Object rows = 10;

    /**
     * 返回总记录数
     */
    private Long total;

    /**
     * 返回总页数
     */
    private Integer totalPage;

    /**
     * 构造方法
     */
    public PageOutput() {
    }

    /**
     * 构造方法
     *
     * @param code 状态码
     */
    public PageOutput(String code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param code 状态码
     * @param rows 行
     */
    public PageOutput(String code, Object rows) {
        this.code = code;
        this.rows = rows;
    }

    /**
     * 处理成功
     *
     * @param page 分页
     * @return 输出
     */
    public static PageOutput ok(Page page,Object list) {
        PageOutput output = new PageOutput();
        output.setCode(OutputCode.OK.getCode());
        output.setPage(page.getPageNum());
        output.setRows(list);
        output.setTotalPage(page.getPages());
        output.setTotal(page.getTotal());
        return output;
    }

    /**
     * 处理成功（消息）
     *
     * @param page 分页
     * @return 输出
     */
    public static PageOutput okMessage(Page page) {
        PageOutput output = new PageOutput();
        output.setCode(OutputCode.OK.getCode());
        output.setMessage(OutputCode.OK.getMessage());
        output.setPage(page.getPageNum());
        output.setRows(page);
        output.setTotalPage(page.getPages());
        output.setTotal(page.getTotal());
        return output;
    }

    /**
     * 分页
     *
     * @param page 分页
     * @return 输出
     */
    public static PageOutput page(Page page) {
        PageOutput output = new PageOutput();
        output.setPage(page.getPageNum());
        output.setRows(page);
        output.setTotalPage(page.getPages());
        output.setTotal(page.getTotal());
        return output;
    }

    /**
     * 获取
     *
     * @param code 状态码
     * @return 输出
     */
    public static PageOutput ex() {
        return new PageOutput(OutputCode.EX.getCode());
    }

}
