package com.jinyafu.thirdpart.common.util.common;

import java.util.Calendar;
import org.apache.commons.lang.StringUtils;
import com.jinyafu.thirdpart.common.util.huaweiyun.obs.OBSConfig;

/**
 * @author XiaHui
 * @date 2017-11-24 22:52:39
 */
public class FilePathUtil {

	/**
	 * 获取根目录
	 * 
	 * @param rootPath
	 * @return
	 */
	public static StringBuilder getRootPath(String rootPath) {
		StringBuilder root = new StringBuilder();
		return root.append((StringUtils.isNotBlank(rootPath)) ? rootPath : "");
	}
	
	public static String getfolder(String suffixName) {
		String folderName = OBSConfig.PRODUCT_FILE_FOLDER;
		if(OBSConfig.IMAGE_FORMAT.contains(suffixName.toLowerCase())) {
			folderName = OBSConfig.PRODUCT_IMAGE_FOLDER;
		}else if(OBSConfig.VIDEO_FORMAT.contains(suffixName.toLowerCase())) {
			folderName = OBSConfig.PRODUCT_VIDEO_FOLDER;
		}
		return folderName;
	}

	public static String getNodePath() {
		return getNodePath("");
	}

	public static String getNodePath(String basePath) {
		StringBuilder nodePath = new StringBuilder(basePath);
		nodePath.append(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
		nodePath.append("/");
		nodePath.append(String.valueOf(Calendar.getInstance().get(Calendar.MONTH)));
		nodePath.append("/");
		nodePath.append(String.valueOf(Calendar.getInstance().get(Calendar.DATE)));
		nodePath.append("/");
		return nodePath.toString();
	}
}
