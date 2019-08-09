package com.jinyafu.thirdpart.common.util.aliyun;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Producer;

/**
 * date 2018-06-13 10:27:23<br>
 * description
 * 
 * @author Ly<br>
 * @since
 */
@Service
public class VerifyManager {

	Map<String, Map<String, VerifyInfo>> codeMap = new java.util.concurrent.ConcurrentHashMap<String, Map<String, VerifyInfo>>();
	private Lock lock = new ReentrantLock();
	@Autowired
	Producer captchaProducer;

	/**
	 * date 2018-07-20 13:43:36<br>
	 * description 根据业务编码生成验证码,默认有效时间2分钟
	 * 
	 * @author Ly<br>
	 * @param businessCode
	 * @return
	 * @since
	 */
	public BufferedImage createImage(String businessCode) {
		long time = 1000L * 60 * 2;
		return createImage(businessCode, time);
	}

	public BufferedImage createImage(String businessCode, long time) {
		String capText = captchaProducer.createText();
		VerifyInfo vi = new VerifyInfo();
		vi.createTime = System.currentTimeMillis();
		vi.expireDuration = time;
		Map<String, VerifyInfo> objectMap = getMap(businessCode);
		objectMap.put(capText, vi);
		BufferedImage bi = captchaProducer.createImage(capText);
		return bi;
	}

	public boolean has(String businessCode, String verifyCode) {
		businessCode = (null != businessCode) ? businessCode : "";
		verifyCode = (null != verifyCode) ? verifyCode : "";
		boolean verify = false;
		Map<String, VerifyInfo> map = codeMap.get(businessCode);
		if (map != null) {
			verify = map.containsKey(verifyCode);
		}
		return verify;
	}

	public boolean verify(String businessCode, String verifyCode) {
		businessCode = (null != businessCode) ? businessCode : "";
		verifyCode = (null != verifyCode) ? verifyCode : "";
		boolean verify = false;
		Map<String, VerifyInfo> map = codeMap.remove(businessCode);
		if (map != null) {
			verify = map.containsKey(verifyCode);
			if (verify) {
				map.remove(verifyCode);
			}
		}
		return verify;
	}

	private Map<String, VerifyInfo> getMap(String businessCode) {
		lock.lock();
		Map<String, VerifyInfo> objectMap = codeMap.get(businessCode);
		try {
			if (null == objectMap) {
				objectMap = new ConcurrentHashMap<String, VerifyInfo>();
				codeMap.put(businessCode, objectMap);
			}
		} finally {
			lock.unlock();
		}
		return objectMap;
	}

	/**
	 * 清除超时的验证码
	 * date 2018-07-20 13:57:50<br>
	 * description 
	 * @author Ly<br>
	 * @since
	 */
	public void clearTimeout() {
		Set<String> keySet = codeMap.keySet();
		for (String key : keySet) {
			Map<String, VerifyInfo> objectMap = codeMap.get(key);
			if (null != objectMap) {
				Set<String> codeSet = objectMap.keySet();
				for (String code : codeSet) {
					VerifyInfo vi = objectMap.get(code);
					if (System.currentTimeMillis() - vi.createTime >= vi.expireDuration) {
						objectMap.remove(code);
					}
				}
			}
		}
	}

	class VerifyInfo {
		long expireDuration;
		long createTime;
	}
}
