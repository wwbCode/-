/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.jinyafu.thirdpart.common.util.huaweiyun.obs;

/**
 * TODO:OBS公共参数
 * @version 2019年1月4日上午9:38:54
 * @author Mike
 */
public interface OBSConfig {
	
	/**obs endpoint*/
	public static final String ENDPOINT = "obs.cn-east-2.myhuaweicloud.com";
	/**obs ACCESS_KEY*/
	public static final String ACCESS_KEY = "II3EI8YJCP9PWUBDLFSG";
	/**obs SECRET_KEY*/
	public static final String SECRET_KEY = "qqxHkkf9LIdOBOdGvavKBTDjFVM2bR08eWifE7bN";
	/**imagebuket名称*/
	public static final String BUCKETNAME="productlib";
	/**产品图片文件夹*/
	public static final String PRODUCT_IMAGE_FOLDER="images/";
	/**产品反馈文件夹*/
	public static final String PRODUCT_PRODUCT_IMAGE_FOLDER="images/feedback/";
	/**产品文件文件夹*/
	public static final String PRODUCT_FILE_FOLDER="files/";
	/**产品视频文件夹*/
	public static final String PRODUCT_VIDEO_FOLDER="videos/";
	/**图片*/
	public static final String IMAGE_FORMAT ="jpeg|png|jpg|gif|bmp|tiff|tag|eps|psd";
	/**音乐，视频*/
    public static final String VIDEO_FORMAT ="mpeg|avi|rmvb|wmv|flv|mp4|mp3|wma|wav";
}
