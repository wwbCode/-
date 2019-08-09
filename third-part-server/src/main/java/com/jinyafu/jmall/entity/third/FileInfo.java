package com.jinyafu.jmall.entity.third;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class FileInfo {

	private String id;
	/** 文件名 */
	private String fileName;
	/** 文件后缀 */
	private String suffix;
	/** 文件全名 */
	private String fullFileName;
	/** 文件大小 */
	private Long fileSize;
	/** 第三方文件路径 */
	private String path;
	/** 第三方文件名 */
	private String thirdName;
	/**用户ID*/
	private String userId;
	/**用户名*/
	private String userName;
	/** 第三方文件id */
	private String thirdId;
	/** 创建时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	/** 是否删除（0：未删除，1：已删除） */
	private Integer isDelete;

}