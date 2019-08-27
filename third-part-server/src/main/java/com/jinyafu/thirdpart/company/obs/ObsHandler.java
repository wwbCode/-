package com.jinyafu.thirdpart.company.obs;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jinyafu.jmall.entity.third.FileInfo;
import com.jinyafu.jmall.mapper.third.file.FileInfoMapper;
import com.jinyafu.thirdpart.common.code.Output;
import com.jinyafu.thirdpart.common.code.OutputCode;
import com.jinyafu.thirdpart.common.util.common.AcceptFileHandler;
import com.jinyafu.thirdpart.common.util.common.FileNameUtil;
import com.jinyafu.thirdpart.common.util.common.FilePathUtil;
import com.jinyafu.thirdpart.common.util.common.KeyUtil;
import com.jinyafu.thirdpart.common.util.huaweiyun.obs.ObsManger;


@Service
@Transactional
public class ObsHandler {
	
	@Autowired
	FileInfoMapper fileInfoMapper;
	
	AcceptFileHandler acceptFileHandler = new AcceptFileHandler();

	public Output<?> uploadByIO(HttpServletRequest request, MultipartFile file, String userId) {
		String fileName = file.getOriginalFilename();// 取得当前上传文件的文件名称
		String suffixName = FileNameUtil.getSuffixName(fileName);
		String folderName = FilePathUtil.getfolder(suffixName);
		String key = KeyUtil.getKey();
		String saveName = folderName + key + "." + suffixName;
		Map<String,Object> map = ObsManger.fileUpload(file, saveName);
		if(map.get("Code").toString().equals(OutputCode.OK.getCode())) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName(FileNameUtil.getName(fileName));
			fileInfo.setFileSize(file.getSize());
			fileInfo.setFullFileName(fileName);
			fileInfo.setSuffix(suffixName);
			fileInfo.setId(KeyUtil.getKey());
			fileInfo.setPath(saveName);
			fileInfo.setThirdName(key + "." + suffixName);
			fileInfo.setUserId(userId);
			add(fileInfo);
			return Output.ok(fileInfo);
		}else {
			return Output.get(map.get("Code").toString(), map.get("Message").toString());
		}
	}
	
	public Output<?> uploadByIO(HttpServletRequest request, MultipartFile[] files, String userId) {
		List<FileInfo> fileList = new ArrayList<FileInfo>(files.length);
		if (files!=null && files.length>0) {
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();// 取得当前上传文件的文件名称
				String suffixName = FileNameUtil.getSuffixName(fileName);
				String folderName = FilePathUtil.getfolder(suffixName);
				String key = KeyUtil.getKey();
				String saveName = folderName + key + "." + suffixName;
				Map<String,Object> map = ObsManger.fileUpload(file, saveName);
				if(map.get("Code").toString().equals(OutputCode.OK.getCode())) {
					FileInfo fileInfo = new FileInfo();
					fileInfo.setFileName(FileNameUtil.getName(fileName));
					fileInfo.setFileSize(file.getSize());
					fileInfo.setFullFileName(fileName);
					fileInfo.setSuffix(suffixName);
					fileInfo.setId(KeyUtil.getKey());
					fileInfo.setPath(saveName);
					fileInfo.setThirdName(key + "." + suffixName);
					fileInfo.setUserId(userId);
					fileList.add(fileInfo);
				}
			}
		}
		return Output.ok(fileList);
	}

	private void add(FileInfo fileInfo) {
	    fileInfoMapper.insert(fileInfo);
	}

}
