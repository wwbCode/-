/*
 * Copyright © 2011-2015 Kinghood Group All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳金雅福控股集团有限公司 www.jinyafu.com.
 */
package com.jinyafu.thirdpart.common.util.huaweiyun.obs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jinyafu.thirdpart.common.code.OutputCode;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.obs.services.model.ObsObject;

/**
 * TODO:Obs管理类
 * @version 2019年2月14日上午11:45:40
 * @author Mike
 */
public class ObsManger {
	
	private static ObsClient obsClient;
	
	static {
		 ObsConfiguration config = new ObsConfiguration();
		 config.setSocketTimeout(30000);
	     config.setConnectionTimeout(10000);
	     config.setEndPoint(OBSConfig.ENDPOINT);
		obsClient = new ObsClient(OBSConfig.ACCESS_KEY,OBSConfig.SECRET_KEY,config);
	}
	
	/**
	 * 
	 * TODO:字符串上传
	 * @version 2019年2月14日下午4:35:56
	 * @author Mike
	 * @param content
	 * @param objectKey
	 * @return
	 */
	public static synchronized Map<String,Object> byteUpload(byte[] content,String objectKey ) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			obsClient.putObject(OBSConfig.BUCKETNAME, objectKey, new ByteArrayInputStream(content));
			map.put("Message", OutputCode.OK.getMessage());
			map.put("Code", OutputCode.OK.getCode());
		} catch (ObsException e) {
			map.put("ResponseCode", e.getResponseCode());
			map.put("Message", e.getErrorMessage());
			map.put("Code", e.getErrorCode());
			map.put("RequestID", e.getErrorRequestId());
			map.put("HostID", e.getErrorHostId());
		}
		return map;
	}
	
	/**
	 * 
	 * TODO:根据url地址上传
	 * @version 2019年2月14日下午4:36:11
	 * @author Mike
	 * @param url
	 * @param objectKey
	 * @return
	 */
	public static synchronized Map<String,Object> netWorkUpload(String url,String objectKey ) {
		Map<String,Object> map = new HashMap<String,Object>();
			InputStream inputStream;
			try {
				inputStream = new URL(url).openStream();
				obsClient.putObject(OBSConfig.BUCKETNAME, objectKey, inputStream);
				map.put("Message", OutputCode.OK.getMessage());
				map.put("Code", OutputCode.OK.getCode());
			} catch (MalformedURLException e) {
				map.put("Message", e.getMessage());
				map.put("Code", "500");
			} catch (IOException e) {
				map.put("Message", e.getMessage());
				map.put("Code", "500");
			} catch (ObsException e) {
				map.put("Message", e.getErrorMessage());
				map.put("Code", e.getErrorCode());
			}
		
		return map;
	}
	
	/**
	 * 
	 * TODO:文件上传
	 * @version 2019年2月14日下午4:36:27
	 * @author Mike
	 * @param file
	 * @param objectKey
	 * @return
	 */
	public static synchronized Map<String,Object> fileUpload(MultipartFile file,String objectKey) {
		Map<String,Object> map = new HashMap<String,Object>();
		InputStream fis;
			try {
			    fis = file.getInputStream();
				obsClient.putObject(OBSConfig.BUCKETNAME, objectKey, fis);
				map.put("Message", OutputCode.OK.getMessage());
				map.put("Code", OutputCode.OK.getCode());
			} catch (IOException e) {
				map.put("Message", e.getMessage());
				map.put("Code", "500");
			} catch (ObsException e) {
				map.put("Message", e.getErrorMessage());
				map.put("Code", e.getErrorCode());
			}
		
		return map;
	}
	
	/**
	 * 
	 * TODO:本地文件上传
	 * @version 2019年2月20日下午4:51:28
	 * @author Mike
	 * @param file
	 * @param objectKey
	 * @return
	 */
	public static synchronized Map<String,Object> fileUpload(File file,String objectKey ) {
		Map<String,Object> map = new HashMap<String,Object>();
		FileInputStream fis;
			try {
			    fis = new FileInputStream(file);
				obsClient.putObject(OBSConfig.BUCKETNAME, objectKey, fis);
				map.put("Message", OutputCode.OK.getMessage());
				map.put("Code", OutputCode.OK.getCode());
			} catch (IOException e) {
				map.put("Message", e.getMessage());
				map.put("Code", "500");
			} catch (ObsException e) {
				map.put("Message", e.getErrorMessage());
				map.put("Code", e.getErrorCode());
			}
		
		return map;
	}
	
	/**
	 * 
	 * TODO:创建文件夹
	 * @version 2019年2月19日下午6:42:06
	 * @author Mike
	 * @param folderName
	 * @return
	 */
	public static synchronized Map<String,Object> createFolder(String folderName ) {
		Map<String,Object> map = new HashMap<String,Object>();
			try {
				obsClient.putObject(OBSConfig.BUCKETNAME, folderName,new ByteArrayInputStream(new byte[0]));
				map.put("Message", OutputCode.OK.getMessage());
				map.put("Code", OutputCode.OK.getCode());
			}  catch (ObsException e) {
				map.put("Message", e.getErrorMessage());
				map.put("Code", e.getErrorCode());
			}
		return map;
	}
	
	/**
	 * 
	 * TODO:文件下载
	 * @version 2019年2月21日上午11:10:51
	 * @author Mike
	 * @param objectkey
	 * @return
	 */
	public static synchronized InputStream downFile(String objectkey ) {
		ObsObject obsObject = obsClient.getObject(OBSConfig.BUCKETNAME, objectkey);
		if(obsObject == null) return null;
		return  obsObject.getObjectContent();
	}
	
	


}
